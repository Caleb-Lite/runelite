package net.runelite.client.plugins.pluginhub.com.ericversteeg;

import net.runelite.client.plugins.pluginhub.com.ericversteeg.model.config.Location;
import net.runelite.client.plugins.pluginhub.com.ericversteeg.model.config.Sound;
import net.runelite.client.plugins.pluginhub.com.ericversteeg.model.config.TextSize;
import net.runelite.client.plugins.pluginhub.com.ericversteeg.model.config.TimeUnit;
import net.runelite.client.plugins.pluginhub.com.ericversteeg.model.Prompt;
import net.runelite.client.plugins.pluginhub.com.ericversteeg.view.RSAnchorType;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.inject.Provides;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.client.Notifier;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.events.RuneScapeProfileChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import javax.inject.Provider;
import java.awt.*;
import java.time.*;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@PluginDescriptor(
	name = "Fun Reminders",
	description = "Create reminders."
)

public class ProximityPromptPlugin extends Plugin {

	@Inject
	private ProximityPromptOverlay overlay;
	@Inject
	private OverlayManager overlayManager;
	@Inject
	private Client client;
	@Inject
	private ProximityPromptConfig config;
	@Inject
	private ConfigManager configManager;
	@Inject
	private Gson gson;
	@Inject
	private ItemManager itemManager;
	@Inject
	private Notifier notifier;
	@Inject
	private Provider<ClientThread> clientThreadProvider;

	List<Prompt> prompts = new ArrayList<>();
	List<Prompt> activePrompts = new ArrayList<>();
	List<Prompt> inactivePrompts = new ArrayList<>();

	LocalDateTime dateTime = LocalDateTime.now(ZoneId.systemDefault());
	long startTime = 0L;

	int regionId = 0;
	int lastRegionId = 0;
	WorldPoint worldPos = new WorldPoint(0, 0, 0);

	List<NPC> npcs = new ArrayList<>();
	List<ChatMessage> chatMessages = new ArrayList<>();
	List<ItemComposition> items = new ArrayList<>();

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);

		prompts = getAllPrompts();
		activePrompts = getActivePrompts(prompts);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}

	@Provides
	ProximityPromptConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ProximityPromptConfig.class);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged config)
	{
		if (config.getGroup().equals(ProximityPromptConfig.GROUP))
		{
			prompts = getAllPrompts();
			activePrompts = getActivePrompts(prompts);

			if (config.getKey().matches("^.*?Sound$"))
			{
				int soundId = Sound.valueOf(config.getNewValue()).getId();

				if (soundId > 0)
				{
					clientThreadProvider.get().invoke(() ->
							client.playSoundEffect(
									soundId,
									SoundEffectVolume.HIGH
							)
					);
				}
			}
		}
	}

	@Subscribe
	public void onGameTick(GameTick tick)
	{
		if (startTime == 0L)
		{
			startTime = Instant.now().toEpochMilli();
		}

		dateTime = LocalDateTime.now(ZoneId.systemDefault());

		activePrompts = getActivePrompts(prompts);

		items.clear();

		regionId = WorldPoint.fromLocalInstance(
				client,
				client.getLocalPlayer().getLocalLocation()
		).getRegionID();

		lastRegionId = regionId;

		worldPos = client.getLocalPlayer().getWorldLocation();

		final ItemContainer itemContainer = client.getItemContainer(InventoryID.INVENTORY);

		if (itemContainer == null)
		{
			return;
		}

		final Item[] inventoryItems = itemContainer.getItems();
		for (Item item : inventoryItems)
		{
			ItemComposition itemComposition = itemManager.getItemComposition(item.getId());

			if (!itemsContainsId(itemComposition.getId()))
			{
				items.add(itemComposition);
			}
		}

		final List<ItemComposition> equipmentItems = getEquipment();
		for (ItemComposition itemComposition: equipmentItems)
		{
			if (!itemsContainsId(itemComposition.getId()))
			{
				items.add(itemComposition);
			}
		}
	}

	@Subscribe(priority = -1)
	private void onNpcSpawned(NpcSpawned npcSpawned)
	{
		NPC npc = npcSpawned.getNpc();
		npcs.add(npc);
	}

	@Subscribe(priority = -1)
	public void onNpcDespawned(NpcDespawned npcDespawned)
	{
		NPC npc = npcDespawned.getNpc();
		npcs.remove(npc);
	}

	@Subscribe
	public void onChatMessage(ChatMessage chatMessage)
	{
		chatMessages.add(chatMessage);
	}

	private boolean npcsContainsName(String name)
	{
		for (NPC npc : npcs) {
			if (name.equals(npc.getName()))
			{
				return true;
			}
		}
		return false;
	}

	private List<Prompt> getAllPrompts()
	{
		long start = Instant.now().toEpochMilli();

		List<Prompt> prompts = new ArrayList<>();

		for (int i = 1; i <= 100; i++)
		{
			Prompt prompt = new Prompt();

			prompt.id = i;
			prompt.enable = isEnable(i);
			prompt.force = isForceShow(i);
			prompt.text = getText(i);
			prompt.color = getColor(i);
			prompt.duration = getDuration(i);
			prompt.cooldown = getCooldown(i);
			prompt.timeUnit = getTimeUnit(i).getType();
			prompt.notify = isNotify(i);
			prompt.location = getLocation(i).ordinal();
			prompt.anchorType = getPanelAnchorType(i).ordinal();
			prompt.anchorX = getPanelAnchorX(i);
			prompt.anchorY = getPanelAnchorY(i);
			prompt.width = getPanelWidth(i);
			prompt.hasBorder = isPanelBorder(i);
			prompt.bgColor = getPanelColor(i);
			prompt.textSize = getPanelTextSize(i).ordinal();
			prompt.sound = getSound(i).ordinal();
			prompt.imageId = getImageId(i);
			prompt.imageOffset = getImageOffset(i);
			prompt.isOffsetNegative = isImageOffsetNegative(i);
			prompt.times = getTimes(i);
			prompt.days = getDaysOfWeek(i);
			prompt.dates = getDates(i);
			prompt.coordinates = getCoordinates(i);
			prompt.radius = getRadius(i);
			prompt.geoFences = getGeofences(i);
			prompt.regionIds = getRegionIds(i);
			prompt.npcIds = getNpcIds(i);
			prompt.itemIds = getItemIds(i);
			prompt.chatPatterns = getChatPatterns(i);

			prompts.add(prompt);
		}

		try {
			JsonArray jsonArray = gson.fromJson(config.customReminders(), JsonArray.class);

			int i = 1;
			for (JsonElement jsonElement: jsonArray)
			{
				JsonObject jsonObject = jsonElement.getAsJsonObject();

				Prompt prompt = new Prompt();

				prompt.id = 100 + i;

				prompt.enable = true;

				if (jsonObject.has("force"))
				{
					prompt.force = jsonObject.get("force").getAsBoolean();
				}

				if (jsonObject.has("text"))
				{
					prompt.text = jsonObject.get("text").getAsString();
				}

				if (jsonObject.has("color"))
				{
					prompt.colorStr = jsonObject.get("color").getAsString();
					prompt.color = Color.decode(prompt.colorStr);
				}

				if (jsonObject.has("duration"))
				{
					prompt.duration = jsonObject.get("duration").getAsInt();
				}

				if (jsonObject.has("cd"))
				{
					prompt.cooldown = jsonObject.get("cd").getAsInt();
				}

				if (jsonObject.has("cooldown"))
				{
					prompt.cooldown = jsonObject.get("cooldown").getAsInt();
				}

				if (jsonObject.has("unit"))
				{
					prompt.timeUnit = jsonObject.get("unit").getAsInt();
				}

				if (jsonObject.has("notify"))
				{
					prompt.notify = jsonObject.get("notify").getAsBoolean();
				}

				if (jsonObject.has("location"))
				{
					prompt.location = jsonObject.get("location").getAsInt();
				}

				if (jsonObject.has("anchor"))
				{
					prompt.anchorType = jsonObject.get("anchor").getAsInt();
				}

				if (jsonObject.has("x"))
				{
					prompt.anchorX = jsonObject.get("x").getAsInt();
				}

				if (jsonObject.has("y"))
				{
					prompt.anchorY = jsonObject.get("y").getAsInt();
				}

				if (jsonObject.has("width"))
				{
					prompt.width = jsonObject.get("width").getAsInt();
				}

				if (jsonObject.has("border"))
				{
					prompt.hasBorder = jsonObject.get("border").getAsBoolean();
				}

				if (jsonObject.has("bg"))
				{
					prompt.bgColorStr = jsonObject.get("bg").getAsString();
					prompt.bgColor = Color.decode(prompt.bgColorStr);
				}

				if (jsonObject.has("tsize"))
				{
					prompt.textSize = jsonObject.get("tsize").getAsInt();
				}

				if (jsonObject.has("sound"))
				{
					prompt.sound = jsonObject.get("sound").getAsInt();
				}

				if (jsonObject.has("image"))
				{
					prompt.imageId = jsonObject.get("image").getAsInt();
				}

				if (jsonObject.has("offset"))
				{
					prompt.imageOffset = jsonObject.get("offset").getAsInt();
				}

				if (jsonObject.has("noffset"))
				{
					prompt.isOffsetNegative = jsonObject.get("noffset").getAsBoolean();
				}

				if (jsonObject.has("coords"))
				{
					prompt.coordinates = toCsv(jsonObject.get("coords").getAsJsonArray());
				}

				if (jsonObject.has("radius"))
				{
					prompt.radius = jsonObject.get("radius").getAsInt();
				}

				if (jsonObject.has("geofences"))
				{
					prompt.geoFences = toCsv(jsonObject.get("geofences").getAsJsonArray());
				}

				if (jsonObject.has("regions"))
				{
					prompt.regionIds = toCsv(jsonObject.get("regions").getAsJsonArray());
				}

				if (jsonObject.has("npcs"))
				{
					prompt.npcIds = toCsv(jsonObject.get("npcs").getAsJsonArray());
				}

				if (jsonObject.has("items"))
				{
					prompt.itemIds = toCsv(jsonObject.get("items").getAsJsonArray());
				}

				if (jsonObject.has("messages"))
				{
					prompt.chatPatterns = toCsv(jsonObject.get("messages").getAsJsonArray());
				}

				if (jsonObject.has("times"))
				{
					prompt.times = toCsv(jsonObject.get("times").getAsJsonArray());
				}

				if (jsonObject.has("days"))
				{
					prompt.days = toCsv(jsonObject.get("days").getAsJsonArray());
				}

				if (jsonObject.has("dates"))
				{
					prompt.dates = toCsv(jsonObject.get("dates").getAsJsonArray());
				}

				prompts.add(prompt);

				i++;
			}
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
			return prompts;
		}


		//System.out.println("Get prompts took " + (Instant.now().toEpochMilli() - start) + "ms");

		return prompts;
	}

	private List<Prompt> getActivePrompts(List<Prompt> prompts)
	{
		long start = Instant.now().toEpochMilli();

		List<Prompt> activeList = new ArrayList<>();

		activePrompts.addAll(inactivePrompts);
		inactivePrompts.clear();

		for (Prompt prompt : prompts)
		{
			if (prompt.force)
			{
				activeList.add(prompt);
			}
		}

		for (Prompt prompt : prompts)
		{
			if (prompt.enable && !prompt.force)
			{
				if (!((checkTimes(prompt.times) && !matchesTimes(prompt.times)) ||
					(checkDaysOfWeek(prompt.days) && !matchesDaysOfWeek(prompt.days)) ||
					(checkDates(prompt.dates) && !matchesDates(prompt.dates)) ||
					(checkCoordinates(prompt.coordinates) && !matchesCoordinates(prompt.coordinates, prompt.radius)) ||
					(checkGeoFences(prompt.geoFences) && !matchesGeoFences(prompt.geoFences)) ||
					(checkRegions(prompt.regionIds) && !matchesRegions(prompt.regionIds)) ||
					(checkNpcIds(prompt.npcIds) && !matchesNpcIds(prompt.npcIds)) ||
					(checkItemIds(prompt.itemIds) && !matchesItemIds(prompt.itemIds)) ||
					(checkChatPatterns(prompt.chatPatterns) && !matchesChatPatterns(prompt.chatPatterns)))
				)
				{
					Prompt currentActive = currentActivePrompt(prompt.id);
					if (currentActive != null)
					{
						prompt.posted = currentActive.posted;
						prompt.active = currentActive.active;
					}
					else
					{
						prompt.posted = Instant.now().toEpochMilli();
						prompt.active = true;

						playSound(prompt.sound);

						if (prompt.notify && prompt.lastNotified <= Instant.now().toEpochMilli() - 30000)
						{
							notifier.notify(prompt.text);
							prompt.lastNotified = Instant.now().toEpochMilli();
						}
					}

					if (Instant.now().toEpochMilli() >= prompt.posted
							+ prompt.getDurationMillis() + prompt.getCooldownMillis())
					{
						prompt.active = true;
						prompt.posted = Instant.now().toEpochMilli();

						if (prompt.cooldown > 0)
						{
							playSound(prompt.sound);
						}

						if (prompt.notify && prompt.lastNotified <= Instant.now().toEpochMilli() - 30000)
						{
							notifier.notify(prompt.text);
							prompt.lastNotified = Instant.now().toEpochMilli();
						}

						activeList.add(prompt);
						continue;
					}
				}

				if (prompt.posted > 0)
				{
					if (Instant.now().toEpochMilli() >= prompt.posted
							+ prompt.getDurationMillis() + prompt.getCooldownMillis())
					{
						continue;
					}

					prompt.active = Instant.now().toEpochMilli() < prompt.posted
							+ prompt.getDurationMillis();

					if (prompt.active)
					{
						activeList.add(prompt);
					}
					else
					{
						inactivePrompts.add(prompt);
					}
				}
			}
		}

		chatMessages.clear();

		//System.out.println("Get active took " + (Instant.now().toEpochMilli() - start) + "ms");

		return activeList;
	}

	private Prompt currentActivePrompt(int id)
	{
		for (Prompt prompt : activePrompts)
		{
			if (prompt.id == id)
			{
				return prompt;
			}
		}
		return null;
	}

	private void playSound(int sound)
	{
		if (Instant.now().toEpochMilli() - startTime < 5000
				|| sound == 0)
		{
			return;
		}

		clientThreadProvider.get().invoke(() ->
				client.playSoundEffect(
						Sound.values()[sound].getId(),
						SoundEffectVolume.HIGH
				)
		);
	}

	private List<ItemComposition> getEquipment()
	{
		ItemContainer itemContainer = client.getItemContainer(InventoryID.EQUIPMENT);

		if (itemContainer == null)
		{
			return new ArrayList<>();
		}

		Item ring = itemContainer.getItem(EquipmentInventorySlot.RING.getSlotIdx());
		Item ammo = itemContainer.getItem(EquipmentInventorySlot.AMMO.getSlotIdx());

		Player player = client.getLocalPlayer();

		int [] ids = player.getPlayerComposition().getEquipmentIds();

		List<Integer> eIds = new ArrayList<>();

		for (int id: ids)
		{
			if (id < 512)
			{
				continue;
			}

			eIds.add(id - 512);
		}

		if (ring != null)
		{
			eIds.add(ring.getId());
		}

		if (ammo != null)
		{
			eIds.add(ammo.getId());
		}

		return eIds.stream().map(itemManager::getItemComposition)
				.collect(Collectors.toList());
	}

	private boolean itemsContainsId(int id)
	{
		for (ItemComposition item : items)
		{
			if (id == item.getId())
			{
				return true;
			}
		}
		return false;
	}

	private String toCsv(JsonArray jsonArray)
	{
		StringBuilder sb = new StringBuilder();

		for (JsonElement jsonElement: jsonArray)
		{
			sb.append(jsonElement.getAsString());
			sb.append(",");
		}

		return sb.substring(0, sb.length() - 1);
	}

	private boolean checkTimes(String times)
	{
		return !times.isEmpty();
	}

	private boolean matchesTimes(String times)
	{
		try {
			String [] timeRanges = times.split(",");

			for (String timeRangeStr: timeRanges)
			{
				LocalDateTime startDateTime;
				LocalDateTime endDateTime;

				if (!timeRangeStr.contains("-"))
				{
					startDateTime = toLocalDateTime(timeRangeStr);
					endDateTime = startDateTime.plusMinutes(30);
				}
				else
				{
					String [] timeRangeParts = timeRangeStr.split("-");

					startDateTime = toLocalDateTime(timeRangeParts[0]);
					endDateTime = toLocalDateTime(timeRangeParts[1]);
				}

				if (dateTime.isAfter(startDateTime) && dateTime.isBefore(endDateTime))
				{
					return true;
				}
			}

			return false;
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
			return false;
		}
	}

	private LocalDateTime toLocalDateTime(String amPmTime)
	{
		String [] timeParts = amPmTime.split(":");

		int h = Integer.parseInt(timeParts[0].trim());

		String ma = timeParts[1]
				.toLowerCase()
				.trim();

		int m = Integer.parseInt(ma
				.replace("am", "")
				.replace("pm", ""));


		String aStr = ma
				.substring(ma.length() - 2);

		if (aStr.equals("pm"))
		{
			if (h != 12)
			{
				h += 12;
			}
		}
		else
		{
			if (h == 12)
			{
				h = 0;
			}
		}

		return LocalDate.now(ZoneId.systemDefault()).atTime(h, m);
	}

	private boolean checkDaysOfWeek(String daysOfWeek)
	{
		return !daysOfWeek.isEmpty();
	}

	private boolean matchesDaysOfWeek(String daysOfWeekStr)
	{
		try {
			String [] daysOfWeek = daysOfWeekStr.split(",");

			for (String dayOfWeek: daysOfWeek)
			{
				String lcDayOfWeek = dayOfWeek.toLowerCase();
				int d = 0;
				if (lcDayOfWeek.startsWith("mo")) d = 1;
				else if (lcDayOfWeek.startsWith("tu")) d = 2;
				else if (lcDayOfWeek.startsWith("we")) d = 3;
				else if (lcDayOfWeek.startsWith("th")) d = 4;
				else if (lcDayOfWeek.startsWith("fr")) d = 5;
				else if (lcDayOfWeek.startsWith("sa")) d = 6;
				else if (lcDayOfWeek.startsWith("su")) d = 7;

				boolean matches = dateTime.get(ChronoField.DAY_OF_WEEK) == d;

				if (matches)
				{
					return true;
				}
			}

			return false;
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
			return false;
		}
	}

	private boolean checkDates(String dates)
	{
		return !dates.isEmpty();
	}

	private boolean matchesDates(String datesStr)
	{
		try {
			String [] dateRanges = datesStr.split(",");

			for (String dateRangeOrStr: dateRanges)
			{
				LocalDate startLocalDate;
				LocalDate endLocalDate;

				if (dateRangeOrStr.contains("-"))
				{
					String [] dateRangeParts = dateRangeOrStr.split("-");

					startLocalDate = toLocalDate(dateRangeParts[0]);
					endLocalDate = toLocalDate(dateRangeParts[1]);
				}
				else
				{
					startLocalDate = toLocalDate(dateRangeOrStr);
					endLocalDate = toLocalDate(dateRangeOrStr);
				}

				if (startLocalDate.atStartOfDay().isBefore(dateTime)
						&& endLocalDate.plusDays(1).atStartOfDay().isAfter(dateTime))
				{
					return true;
				}
			}

			return false;
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
			return false;
		}
	}

	private LocalDate toLocalDate(String dateStr)
	{
		String [] dateParts = dateStr.split("/");

		int m = Integer.parseInt(dateParts[0].trim());
		int d = Integer.parseInt(dateParts[1].trim());

		if (dateParts.length > 2)
		{
			int y = Integer.parseInt(dateParts[2].trim());
			return LocalDate.of(y + 2000, m, d);
		}

		return LocalDate.of(dateTime.getYear(), m, d);
	}

	private boolean checkCoordinates(String coordinates)
	{
		return !coordinates.isEmpty();
	}

	private boolean matchesCoordinates(String coordinatesStr, int radius)
	{
		try {
			String [] coordinates = coordinatesStr.split(",");

			int x = 0;
			for (int i = 0; i < coordinates.length; i++)
			{
				String xOrY = coordinates[i]
						.replace("(", "")
						.replace(")", "")
						.trim();

				if (i % 2 == 0)
				{
					x = Integer.parseInt(xOrY);
					continue;
				}

				int y = Integer.parseInt(xOrY);

				double distance = Math.sqrt(
						Math.pow(x - worldPos.getX(), 2) +
								Math.pow(y - worldPos.getY(), 2)
				);

				boolean matches = distance <= radius;

				if (matches)
				{
					return true;
				}
			}

			return false;
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
			return false;
		}
	}

	private boolean checkGeoFences(String geoFences)
	{
		return !geoFences.isEmpty();
	}

	private boolean matchesGeoFences(String geoFences)
	{
		try {
			String [] coordinates = geoFences.split(",");

			int left = 0;
			int top = 0;
			int right = 0;
			for (int i = 0; i < coordinates.length; i++)
			{
				String side = coordinates[i]
						.replace("(", "")
						.replace(")", "")
						.trim();

				if (i % 4 == 0)
				{
					left = Integer.parseInt(side);
					continue;
				}
				else if (i % 4 == 1)
				{
					top = Integer.parseInt(side);
					continue;
				}
				else if (i % 4 == 2)
				{
					right = Integer.parseInt(side);
					continue;
				}

				int bottom = Integer.parseInt(side);

				boolean matches = worldPos.getX() >= left && worldPos.getX() <= right
						&& worldPos.getY() >= bottom && worldPos.getY() <= top;

				if (matches)
				{
					return true;
				}
			}

			return false;
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
			return false;
		}
	}

	private boolean checkRegions(String regionIds)
	{
		return !regionIds.isEmpty();
	}

	private boolean matchesRegions(String regionIds)
	{
		try {
			String [] ids = regionIds.split(",");

			for (String idStr: ids)
			{
				int id = Integer.parseInt(idStr.trim());

				boolean matches = regionId == id;

				if (matches)
				{
					return true;
				}
			}

			return false;
		}
		catch (Exception exception)
		{
			return false;
		}
	}

	private boolean checkNpcIds(String npcIds)
	{
		return !npcIds.isEmpty();
	}

	private boolean matchesNpcIds(String npcIdsStr)
	{
		try {
			String [] npcIds = npcIdsStr.split(",");

			for (String npcIdStr: npcIds)
			{
				int npcId = Integer.parseInt(npcIdStr.trim());

				boolean matches = false;
				for (NPC npc: npcs)
				{
					if (npc.getId() == npcId) {
						matches = true;
						break;
					}
				}

				if (matches)
				{
					return true;
				}
			}

			return false;
		}
		catch (Exception exception)
		{
			return false;
		}
	}

	private boolean checkItemIds(String itemIds)
	{
		return !itemIds.isEmpty();
	}

	private boolean matchesItemIds(String itemIdsStr)
	{
		try {
			String [] itemIds = itemIdsStr.split(",");

			for (String itemIdStr: itemIds)
			{
				int itemId = Integer.parseInt(itemIdStr.trim());

				boolean matches = false;
				for (ItemComposition item: items)
				{
					if (item.getId() == itemId) {
						matches = true;
						break;
					}
				}

				if (matches)
				{
					return true;
				}
			}

			return false;
		}
		catch (Exception exception)
		{
			return false;
		}
	}

	private boolean checkChatPatterns(String chatPatterns)
	{
		return !chatPatterns.isEmpty();
	}

	private boolean matchesChatPatterns(String chatPatternsStr)
	{
		try {
			String [] chatPatterns = chatPatternsStr.split(",");

			for (String chatPattern: chatPatterns)
			{
				chatPattern = chatPattern.trim();

				for (ChatMessage chatMessage: chatMessages)
				{
					Matcher matcher = Pattern.compile(chatPattern)
							.matcher(chatMessage.getMessage());

					if (matcher.find()) {
						return true;
					}
				}
			}

			return false;
		}
		catch (Exception exception)
		{
			return false;
		}
	}

	boolean isEnable(int promptId)
	{
		if (promptId == 1) return config.reminder1Enable();
		else if (promptId == 2) return config.reminder2Enable();
		else if (promptId == 3) return config.reminder3Enable();
		else if (promptId == 4) return config.reminder4Enable();
		else if (promptId == 5) return config.reminder5Enable();
		else if (promptId == 6) return config.reminder6Enable();
		else if (promptId == 7) return config.reminder7Enable();
		else if (promptId == 8) return config.reminder8Enable();
		else if (promptId == 9) return config.reminder9Enable();
		else if (promptId == 10) return config.reminder10Enable();
		else return false;
	}

	String getText(int promptId)
	{
		if (promptId == 1) return config.reminder1Text();
		else if (promptId == 2) return config.reminder2Text();
		else if (promptId == 3) return config.reminder3Text();
		else if (promptId == 4) return config.reminder4Text();
		else if (promptId == 5) return config.reminder5Text();
		else if (promptId == 6) return config.reminder6Text();
		else if (promptId == 7) return config.reminder7Text();
		else if (promptId == 8) return config.reminder8Text();
		else if (promptId == 9) return config.reminder9Text();
		else if (promptId == 10) return config.reminder10Text();
		else return "";
	}

	Color getColor(int promptId)
	{
		if (promptId == 1) return config.reminder1Color();
		else if (promptId == 2) return config.reminder2Color();
		else if (promptId == 3) return config.reminder3Color();
		else if (promptId == 4) return config.reminder4Color();
		else if (promptId == 5) return config.reminder5Color();
		else if (promptId == 6) return config.reminder6Color();
		else if (promptId == 7) return config.reminder7Color();
		else if (promptId == 8) return config.reminder8Color();
		else if (promptId == 9) return config.reminder9Color();
		else if (promptId == 10) return config.reminder10Color();
		else return Color.WHITE;
	}

	int getDuration(int promptId)
	{
		if (promptId == 1) return config.reminder1Duration();
		else if (promptId == 2) return config.reminder2Duration();
		else if (promptId == 3) return config.reminder3Duration();
		else if (promptId == 4) return config.reminder4Duration();
		else if (promptId == 5) return config.reminder5Duration();
		else if (promptId == 6) return config.reminder6Duration();
		else if (promptId == 7) return config.reminder7Duration();
		else if (promptId == 8) return config.reminder8Duration();
		else if (promptId == 9) return config.reminder9Duration();
		else if (promptId == 10) return config.reminder10Duration();
		else return 0;
	}

	int getCooldown(int promptId)
	{
		if (promptId == 1) return config.reminder1Cooldown();
		else if (promptId == 2) return config.reminder2Cooldown();
		else if (promptId == 3) return config.reminder3Cooldown();
		else if (promptId == 4) return config.reminder4Cooldown();
		else if (promptId == 5) return config.reminder5Cooldown();
		else if (promptId == 6) return config.reminder6Cooldown();
		else if (promptId == 7) return config.reminder7Cooldown();
		else if (promptId == 8) return config.reminder8Cooldown();
		else if (promptId == 9) return config.reminder9Cooldown();
		else if (promptId == 10) return config.reminder10Cooldown();
		else return 0;
	}

	TimeUnit getTimeUnit(int promptId)
	{
		if (promptId == 1) return config.reminder1TimeUnit();
		else if (promptId == 2) return config.reminder2TimeUnit();
		else if (promptId == 3) return config.reminder3TimeUnit();
		else if (promptId == 4) return config.reminder4TimeUnit();
		else if (promptId == 5) return config.reminder5TimeUnit();
		else if (promptId == 6) return config.reminder6TimeUnit();
		else if (promptId == 7) return config.reminder7TimeUnit();
		else if (promptId == 8) return config.reminder8TimeUnit();
		else if (promptId == 9) return config.reminder9TimeUnit();
		else if (promptId == 10) return config.reminder10TimeUnit();
		else return TimeUnit.SECONDS;
	}

	boolean isNotify(int promptId)
	{
		if (promptId == 1) return config.reminder1Notify();
		else if (promptId == 2) return config.reminder2Notify();
		else if (promptId == 3) return config.reminder3Notify();
		else if (promptId == 4) return config.reminder4Notify();
		else if (promptId == 5) return config.reminder5Notify();
		else if (promptId == 6) return config.reminder6Notify();
		else if (promptId == 7) return config.reminder7Notify();
		else if (promptId == 8) return config.reminder8Notify();
		else if (promptId == 9) return config.reminder9Notify();
		else if (promptId == 10) return config.reminder10Notify();
		else return false;
	}

	String getTimes(int promptId)
	{
		if (promptId == 1) return config.reminder1Times();
		else if (promptId == 2) return config.reminder2Times();
		else if (promptId == 3) return config.reminder3Times();
		else if (promptId == 4) return config.reminder4Times();
		else if (promptId == 5) return config.reminder5Times();
		else if (promptId == 6) return config.reminder6Times();
		else if (promptId == 7) return config.reminder7Times();
		else if (promptId == 8) return config.reminder8Times();
		else if (promptId == 9) return config.reminder9Times();
		else if (promptId == 10) return config.reminder10Times();
		else return "";
	}

	String getDaysOfWeek(int promptId)
	{
		if (promptId == 1) return config.reminder1DaysOfWeek();
		else if (promptId == 2) return config.reminder2DaysOfWeek();
		else if (promptId == 3) return config.reminder3DaysOfWeek();
		else if (promptId == 4) return config.reminder4DaysOfWeek();
		else if (promptId == 5) return config.reminder5DaysOfWeek();
		else if (promptId == 6) return config.reminder6DaysOfWeek();
		else if (promptId == 7) return config.reminder7DaysOfWeek();
		else if (promptId == 8) return config.reminder8DaysOfWeek();
		else if (promptId == 9) return config.reminder9DaysOfWeek();
		else if (promptId == 10) return config.reminder10DaysOfWeek();
		else return "";
	}

	String getDates(int promptId)
	{
		if (promptId == 1) return config.reminder1Dates();
		else if (promptId == 2) return config.reminder2Dates();
		else if (promptId == 3) return config.reminder3Dates();
		else if (promptId == 4) return config.reminder4Dates();
		else if (promptId == 5) return config.reminder5Dates();
		else if (promptId == 6) return config.reminder6Dates();
		else if (promptId == 7) return config.reminder7Dates();
		else if (promptId == 8) return config.reminder8Dates();
		else if (promptId == 9) return config.reminder9Dates();
		else if (promptId == 10) return config.reminder10Dates();
		else return "";
	}

	String getCoordinates(int promptId)
	{
		if (promptId == 1) return config.reminder1Coordinates();
		else if (promptId == 2) return config.reminder2Coordinates();
		else if (promptId == 3) return config.reminder3Coordinates();
		else if (promptId == 4) return config.reminder4Coordinates();
		else if (promptId == 5) return config.reminder5Coordinates();
		else if (promptId == 6) return config.reminder6Coordinates();
		else if (promptId == 7) return config.reminder7Coordinates();
		else if (promptId == 8) return config.reminder8Coordinates();
		else if (promptId == 9) return config.reminder9Coordinates();
		else if (promptId == 10) return config.reminder10Coordinates();
		else return "";
	}

	int getRadius(int promptId)
	{
		if (promptId == 1) return config.reminder1Radius();
		else if (promptId == 2) return config.reminder2Radius();
		else if (promptId == 3) return config.reminder3Radius();
		else if (promptId == 4) return config.reminder4Radius();
		else if (promptId == 5) return config.reminder5Radius();
		else if (promptId == 6) return config.reminder6Radius();
		else if (promptId == 7) return config.reminder7Radius();
		else if (promptId == 8) return config.reminder8Radius();
		else if (promptId == 9) return config.reminder9Radius();
		else if (promptId == 10) return config.reminder10Radius();
		else return 0;
	}

	String getGeofences(int promptId)
	{
		if (promptId == 1) return config.reminder1Geofences();
		else if (promptId == 2) return config.reminder2Geofences();
		else if (promptId == 3) return config.reminder3Geofences();
		else if (promptId == 4) return config.reminder4Geofences();
		else if (promptId == 5) return config.reminder5Geofences();
		else if (promptId == 6) return config.reminder6Geofences();
		else if (promptId == 7) return config.reminder7Geofences();
		else if (promptId == 8) return config.reminder8Geofences();
		else if (promptId == 9) return config.reminder9Geofences();
		else if (promptId == 10) return config.reminder10Geofences();
		else return "";
	}

	String getRegionIds(int promptId)
	{
		if (promptId == 1) return config.reminder1RegionIds();
		else if (promptId == 2) return config.reminder2RegionIds();
		else if (promptId == 3) return config.reminder3RegionIds();
		else if (promptId == 4) return config.reminder4RegionIds();
		else if (promptId == 5) return config.reminder5RegionIds();
		else if (promptId == 6) return config.reminder6RegionIds();
		else if (promptId == 7) return config.reminder7RegionIds();
		else if (promptId == 8) return config.reminder8RegionIds();
		else if (promptId == 9) return config.reminder9RegionIds();
		else if (promptId == 10) return config.reminder10RegionIds();
		else return "";
	}

	String getNpcIds(int promptId)
	{
		if (promptId == 1) return config.reminder1NpcIds();
		else if (promptId == 2) return config.reminder2NpcIds();
		else if (promptId == 3) return config.reminder3NpcIds();
		else if (promptId == 4) return config.reminder4NpcIds();
		else if (promptId == 5) return config.reminder5NpcIds();
		else if (promptId == 6) return config.reminder6NpcIds();
		else if (promptId == 7) return config.reminder7NpcIds();
		else if (promptId == 8) return config.reminder8NpcIds();
		else if (promptId == 9) return config.reminder9NpcIds();
		else if (promptId == 10) return config.reminder10NpcIds();
		else return "";
	}

	String getItemIds(int promptId)
	{
		if (promptId == 1) return config.reminder1ItemIds();
		else if (promptId == 2) return config.reminder2ItemIds();
		else if (promptId == 3) return config.reminder3ItemIds();
		else if (promptId == 4) return config.reminder4ItemIds();
		else if (promptId == 5) return config.reminder5ItemIds();
		else if (promptId == 6) return config.reminder6ItemIds();
		else if (promptId == 7) return config.reminder7ItemIds();
		else if (promptId == 8) return config.reminder8ItemIds();
		else if (promptId == 9) return config.reminder9ItemIds();
		else if (promptId == 10) return config.reminder10ItemIds();
		else return "";
	}

	String getChatPatterns(int promptId)
	{
		if (promptId == 1) return config.reminder1ChatPatterns();
		else if (promptId == 2) return config.reminder2ChatPatterns();
		else if (promptId == 3) return config.reminder3ChatPatterns();
		else if (promptId == 4) return config.reminder4ChatPatterns();
		else if (promptId == 5) return config.reminder5ChatPatterns();
		else if (promptId == 6) return config.reminder6ChatPatterns();
		else if (promptId == 7) return config.reminder7ChatPatterns();
		else if (promptId == 8) return config.reminder8ChatPatterns();
		else if (promptId == 9) return config.reminder9ChatPatterns();
		else if (promptId == 10) return config.reminder10ChatPatterns();
		else return "";
	}

	Location getLocation(int promptId)
	{
		if (promptId == 1) return config.reminder1Location();
		else if (promptId == 2) return config.reminder2Location();
		else if (promptId == 3) return config.reminder3Location();
		else if (promptId == 4) return config.reminder4Location();
		else if (promptId == 5) return config.reminder5Location();
		else if (promptId == 6) return config.reminder6Location();
		else if (promptId == 7) return config.reminder7Location();
		else if (promptId == 8) return config.reminder8Location();
		else if (promptId == 9) return config.reminder9Location();
		else if (promptId == 10) return config.reminder10Location();
		else return Location.IN_LIST;
	}

	RSAnchorType getPanelAnchorType(int promptId)
	{
		if (promptId == 1) return config.reminder1PanelAnchorType();
		else if (promptId == 2) return config.reminder2PanelAnchorType();
		else if (promptId == 3) return config.reminder3PanelAnchorType();
		else if (promptId == 4) return config.reminder4PanelAnchorType();
		else if (promptId == 5) return config.reminder5PanelAnchorType();
		else if (promptId == 6) return config.reminder6PanelAnchorType();
		else if (promptId == 7) return config.reminder7PanelAnchorType();
		else if (promptId == 8) return config.reminder8PanelAnchorType();
		else if (promptId == 9) return config.reminder9PanelAnchorType();
		else if (promptId == 10) return config.reminder10PanelAnchorType();
		else return RSAnchorType.TOP_LEFT;
	}

	int getPanelAnchorX(int promptId)
	{
		if (promptId == 1) return config.reminder1PanelAnchorX();
		else if (promptId == 2) return config.reminder2PanelAnchorX();
		else if (promptId == 3) return config.reminder3PanelAnchorX();
		else if (promptId == 4) return config.reminder4PanelAnchorX();
		else if (promptId == 5) return config.reminder5PanelAnchorX();
		else if (promptId == 6) return config.reminder6PanelAnchorX();
		else if (promptId == 7) return config.reminder7PanelAnchorX();
		else if (promptId == 8) return config.reminder8PanelAnchorX();
		else if (promptId == 9) return config.reminder9PanelAnchorX();
		else if (promptId == 10) return config.reminder10PanelAnchorX();
		else return 0;
	}

	int getPanelAnchorY(int promptId)
	{
		if (promptId == 1) return config.reminder1PanelAnchorY();
		else if (promptId == 2) return config.reminder2PanelAnchorY();
		else if (promptId == 3) return config.reminder3PanelAnchorY();
		else if (promptId == 4) return config.reminder4PanelAnchorY();
		else if (promptId == 5) return config.reminder5PanelAnchorY();
		else if (promptId == 6) return config.reminder6PanelAnchorY();
		else if (promptId == 7) return config.reminder7PanelAnchorY();
		else if (promptId == 8) return config.reminder8PanelAnchorY();
		else if (promptId == 9) return config.reminder9PanelAnchorY();
		else if (promptId == 10) return config.reminder10PanelAnchorY();
		else return 0;
	}

	int getImageId(int promptId)
	{
		if (promptId == 1) return config.reminder1ImageId();
		else if (promptId == 2) return config.reminder2ImageId();
		else if (promptId == 3) return config.reminder3ImageId();
		else if (promptId == 4) return config.reminder4ImageId();
		else if (promptId == 5) return config.reminder5ImageId();
		else if (promptId == 6) return config.reminder6ImageId();
		else if (promptId == 7) return config.reminder7ImageId();
		else if (promptId == 8) return config.reminder8ImageId();
		else if (promptId == 9) return config.reminder9ImageId();
		else if (promptId == 10) return config.reminder10ImageId();
		else return 0;
	}

	int getPanelWidth(int promptId)
	{
		if (promptId == 1) return config.reminder1PanelWidth();
		else if (promptId == 2) return config.reminder2PanelWidth();
		else if (promptId == 3) return config.reminder3PanelWidth();
		else if (promptId == 4) return config.reminder4PanelWidth();
		else if (promptId == 5) return config.reminder5PanelWidth();
		else if (promptId == 6) return config.reminder6PanelWidth();
		else if (promptId == 7) return config.reminder7PanelWidth();
		else if (promptId == 8) return config.reminder8PanelWidth();
		else if (promptId == 9) return config.reminder9PanelWidth();
		else if (promptId == 10) return config.reminder10PanelWidth();
		else return 0;
	}

	TextSize getPanelTextSize(int promptId)
	{
		if (promptId == 1) return config.reminder1PanelTextSize();
		else if (promptId == 2) return config.reminder2PanelTextSize();
		else if (promptId == 3) return config.reminder3PanelTextSize();
		else if (promptId == 4) return config.reminder4PanelTextSize();
		else if (promptId == 5) return config.reminder5PanelTextSize();
		else if (promptId == 6) return config.reminder6PanelTextSize();
		else if (promptId == 7) return config.reminder7PanelTextSize();
		else if (promptId == 8) return config.reminder8PanelTextSize();
		else if (promptId == 9) return config.reminder9PanelTextSize();
		else if (promptId == 10) return config.reminder10PanelTextSize();
		else return TextSize.SMALL;
	}

	Color getPanelColor(int promptId)
	{
		if (promptId == 1) return config.reminder1PanelColor();
		else if (promptId == 2) return config.reminder2PanelColor();
		else if (promptId == 3) return config.reminder3PanelColor();
		else if (promptId == 4) return config.reminder4PanelColor();
		else if (promptId == 5) return config.reminder5PanelColor();
		else if (promptId == 6) return config.reminder6PanelColor();
		else if (promptId == 7) return config.reminder7PanelColor();
		else if (promptId == 8) return config.reminder8PanelColor();
		else if (promptId == 9) return config.reminder9PanelColor();
		else if (promptId == 10) return config.reminder10PanelColor();
		else return Color.WHITE;
	}

	boolean isPanelBorder(int promptId)
	{
		if (promptId == 1) return config.reminder1PanelBorder();
		else if (promptId == 2) return config.reminder2PanelBorder();
		else if (promptId == 3) return config.reminder3PanelBorder();
		else if (promptId == 4) return config.reminder4PanelBorder();
		else if (promptId == 5) return config.reminder5PanelBorder();
		else if (promptId == 6) return config.reminder6PanelBorder();
		else if (promptId == 7) return config.reminder7PanelBorder();
		else if (promptId == 8) return config.reminder8PanelBorder();
		else if (promptId == 9) return config.reminder9PanelBorder();
		else if (promptId == 10) return config.reminder10PanelBorder();
		else return false;
	}

	boolean isForceShow(int promptId)
	{
		if (promptId == 1) return config.reminder1ForceShow();
		else if (promptId == 2) return config.reminder2ForceShow();
		else if (promptId == 3) return config.reminder3ForceShow();
		else if (promptId == 4) return config.reminder4ForceShow();
		else if (promptId == 5) return config.reminder5ForceShow();
		else if (promptId == 6) return config.reminder6ForceShow();
		else if (promptId == 7) return config.reminder7ForceShow();
		else if (promptId == 8) return config.reminder8ForceShow();
		else if (promptId == 9) return config.reminder9ForceShow();
		else if (promptId == 10) return config.reminder10ForceShow();
		else return false;
	}

	int getImageOffset(int promptId)
	{
		if (promptId == 1) return config.reminder1ImageOffset();
		else if (promptId == 2) return config.reminder2ImageOffset();
		else if (promptId == 3) return config.reminder3ImageOffset();
		else if (promptId == 4) return config.reminder4ImageOffset();
		else if (promptId == 5) return config.reminder5ImageOffset();
		else if (promptId == 6) return config.reminder6ImageOffset();
		else if (promptId == 7) return config.reminder7ImageOffset();
		else if (promptId == 8) return config.reminder8ImageOffset();
		else if (promptId == 9) return config.reminder9ImageOffset();
		else if (promptId == 10) return config.reminder10ImageOffset();
		else return 0;
	}

	boolean isImageOffsetNegative(int promptId)
	{
		if (promptId == 1) return config.reminder1ImageOffsetNegative();
		else if (promptId == 2) return config.reminder2ImageOffsetNegative();
		else if (promptId == 3) return config.reminder3ImageOffsetNegative();
		else if (promptId == 4) return config.reminder4ImageOffsetNegative();
		else if (promptId == 5) return config.reminder5ImageOffsetNegative();
		else if (promptId == 6) return config.reminder6ImageOffsetNegative();
		else if (promptId == 7) return config.reminder7ImageOffsetNegative();
		else if (promptId == 8) return config.reminder8ImageOffsetNegative();
		else if (promptId == 9) return config.reminder9ImageOffsetNegative();
		else if (promptId == 10) return config.reminder10ImageOffsetNegative();
		else return false;
	}

	Sound getSound(int promptId)
	{
		if (promptId == 1) return config.reminder1Sound();
		else if (promptId == 2) return config.reminder2Sound();
		else if (promptId == 3) return config.reminder3Sound();
		else if (promptId == 4) return config.reminder4Sound();
		else if (promptId == 5) return config.reminder5Sound();
		else if (promptId == 6) return config.reminder6Sound();
		else if (promptId == 7) return config.reminder7Sound();
		else if (promptId == 8) return config.reminder8Sound();
		else if (promptId == 9) return config.reminder9Sound();
		else if (promptId == 10) return config.reminder10Sound();
		else return Sound.NONE;
	}
}