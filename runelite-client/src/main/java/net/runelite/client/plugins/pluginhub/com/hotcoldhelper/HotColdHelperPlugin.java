package net.runelite.client.plugins.pluginhub.com.hotcoldhelper;

import com.google.inject.Provides;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.events.PluginMessage;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginManager;
import net.runelite.client.plugins.cluescrolls.ClueScrollPlugin;
import net.runelite.client.plugins.cluescrolls.clues.HotColdClue;
import net.runelite.client.plugins.cluescrolls.clues.hotcold.HotColdLocation;
import net.runelite.client.plugins.cluescrolls.clues.hotcold.HotColdSolver;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j

@PluginDescriptor(
	name = "Hot Cold Helper",
	description = "Helps with the optimal solving of Hot/Cold Clue Steps",
	tags = {"hot", "cold", "clue", "helper"}
)
@PluginDependency(ClueScrollPlugin.class)

public class HotColdHelperPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private HotColdHelperConfig config;

	@Inject
	private ClientThread clientThread;

	@Inject
	private PluginManager pluginManager;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private HotColdHelperOverlay overlay;

	@Inject
	private ClueScrollPlugin clueScrollPlugin;

	@Inject
	private HotColdItemHighlightOverlay itemHighlightOverlay;

	@Inject
	private HotColdWidgetOverlay hotColdWidgetOverlay;

	@Inject
	private EventBus eventBus;

	private static final String HOT_COLD_DEVICE_MESSAGE = "The device is";
	private static final int CHECKED_LOCATION_RANGE = 25;

	@Getter
	private int possibleLocationsCount = -1;

	@Getter
	private HotColdLocation solvedLocation = null;

	@Getter
	private final List<WorldPoint> possibleLocations = new ArrayList<>();

	@Getter
	private final List<WorldPoint> checkedLocations = new ArrayList<>();

	@Getter
	private final Map<HotColdTeleports, Double> entropyValues = new HashMap<>();

	@Getter
	private HotColdTeleports bestTeleport = null;

	@Getter
	private Map<String, Integer> cardinalInfoGain = new HashMap<>();

	@Getter
	private HotColdCardinalHelper.BestCardinalResult bestCardinalDirection =
		new HotColdCardinalHelper.BestCardinalResult("None", 0);

	private boolean needsUpdate = false;
	private Object hotColdClue = null;
	private net.runelite.api.coords.WorldPoint lastMessageLocation;

	@Provides
	HotColdHelperConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(HotColdHelperConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
		clientThread.invokeLater(this::checkHotColdData);
		overlayManager.add(itemHighlightOverlay);
		overlayManager.add(hotColdWidgetOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		possibleLocations.clear();
		checkedLocations.clear();
		entropyValues.clear();
		possibleLocationsCount = -1;
		hotColdClue = null;
		overlayManager.remove(itemHighlightOverlay);
		overlayManager.remove(hotColdWidgetOverlay);
	}

	private void resetPluginState()
	{
		possibleLocationsCount = -1;
		checkedLocations.clear();
		lastMessageLocation = null;
		needsUpdate = false;
	}

	private void addCheckedLocation(WorldPoint location)
	{
		if (checkedLocations.stream().noneMatch(loc ->
			loc.distanceTo2D(location) <= CHECKED_LOCATION_RANGE))
		{
			checkedLocations.add(location);
		}
	}

	public boolean isHotColdClueActive()
	{
		if (clueScrollPlugin == null || hotColdClue == null)
		{
			return false;
		}
		try
		{
			Object currentClue = clueScrollPlugin.getClue();
			if (currentClue != null && hotColdClue.equals(currentClue))
			{
				return true;
			}
		}
		catch (Exception e)
		{
			log.debug("Error checking if hot-cold clue is active", e);
		}
		return false;
	}

	private boolean isLocationChecked(WorldPoint location)
	{
		return checkedLocations.stream()
			.anyMatch(checkedLoc -> checkedLoc.distanceTo2D(location) <= CHECKED_LOCATION_RANGE);
	}

	public boolean isNearLastMessageLocation()
	{
		if (lastMessageLocation == null || !isHotColdClueActive())
		{
			return false;
		}
		WorldPoint currentLocation = client.getLocalPlayer().getWorldLocation();
		int distance = currentLocation.distanceTo2D(lastMessageLocation);
		return distance <= 5;
	}

	private void checkHotColdData()
	{
		if (clueScrollPlugin == null)
		{
			log.debug("Could not find ClueScrollPlugin");
			return;
		}

		try
		{
			Object clue = clueScrollPlugin.getClue();
			if (clue != null)
			{
				if (clue.getClass().getSimpleName().equals("HotColdClue"))
				{
					hotColdClue = clue;
					updatePossibleLocations();
					updateCardinalInfoGain();
				}
				else
				{
					hotColdClue = null;
					possibleLocationsCount = 0;
					possibleLocations.clear();
					checkedLocations.clear();
					entropyValues.clear();
					bestTeleport = null;
				}
			}
			else
			{
				hotColdClue = null;
				possibleLocationsCount = 0;
				possibleLocations.clear();
				checkedLocations.clear();
				entropyValues.clear();
				bestTeleport = null;
			}
		}
		catch (Exception e)
		{
			log.error("Error getting clue", e);
		}
	}

	private void updatePossibleLocations() {
		if (hotColdClue == null) {
			return;
		}
		if (hotColdClue instanceof HotColdClue) {
			HotColdClue hotColdClueTyped = (HotColdClue) hotColdClue;

			HotColdSolver solver = hotColdClueTyped.getHotColdSolver();
			if (solver != null) {
				Set<HotColdLocation> locations = solver.getPossibleLocations();
				if (locations != null) {
					possibleLocationsCount = locations.size();
					possibleLocations.clear();
					for (HotColdLocation location : locations) {
						WorldPoint worldPoint = location.getWorldPoint();
						if (worldPoint != null) {
							possibleLocations.add(worldPoint);
						}
					}

					if (possibleLocationsCount == 1)
					{
						solvedLocation = locations.iterator().next();
						Map<String, Object> data = new HashMap<>();
						data.put("location", solvedLocation.name());
						eventBus.post(new PluginMessage("hot-cold-helper", "location-solved", data));
					}
					else
					{
						solvedLocation = null;
					}

					if (!possibleLocations.isEmpty()) {
						updateEntropyValues();
						updateCardinalInfoGain();
					}
				}
			}
		}
	}

	private void updateEntropyValues()
	{
		entropyValues.clear();
		bestTeleport = null;
		double bestEntropyValue = Double.MAX_VALUE;

		if (possibleLocations.isEmpty())
		{
			return;
		}

		for (HotColdTeleports teleport : HotColdTeleports.values())
		{
			WorldPoint teleportDestination = teleport.getLocation();
			if (teleportDestination == null)
			{
				continue;
			}
			if ("Diary Cape".equals(teleport.getTeleportType()) && !config.includeDiaryCapeTeleports())
			{
				continue;
			}
			if ("Standard".equals(teleport.getTeleportType()) && !config.includeStandardTeleports())
			{
				continue;
			}
			if ("Ancients".equals(teleport.getTeleportType()) && !config.includeAncientTeleports())
			{
				continue;
			}
			if ("Lunars".equals(teleport.getTeleportType()) && !config.includeLunarTeleports())
			{
				continue;
			}
			if ("Arceuus".equals(teleport.getTeleportType()) && !config.includeArceuusTeleports())
			{
				continue;
			}
			if (teleport.isWildernessTeleport() && !config.includeWildernessTeleports())
			{
				continue;
			}
			if (("Skills Necklace".equals(teleport.getTeleportType()) ||
				"Ring of Wealth".equals(teleport.getTeleportType()) ||
				"Ring of Dueling".equals(teleport.getTeleportType()) ||
				"Games Necklace".equals(teleport.getTeleportType()) ||
				"Digsite Pendant".equals(teleport.getTeleportType()) ||
				"Combat Bracelet".equals(teleport.getTeleportType()) ||
				"Amulet of Glory".equals(teleport.getTeleportType())) &&
				!config.includePOHJewelryTeleports())
			{
				continue;
			}
			if ("Music Cape".equals(teleport.getTeleportType()) && !config.includeMusicCapeTeleports())
			{
				continue;
			}
			if ("QP Cape".equals(teleport.getTeleportType()) && !config.includeQPCapeTeleports())
			{
				continue;
			}
			if ("Strength Cape".equals(teleport.getTeleportType()) && !config.includeStrengthCapeTeleports())
			{
				continue;
			}
			if ("Hunter Cape".equals(teleport.getTeleportType()) && !config.includeHunterCapeTeleports())
			{
				continue;
			}
			if ("Farming Cape".equals(teleport.getTeleportType()) && !config.includeFarmingCapeTeleports())
			{
				continue;
			}
			if ("Fishing Cape".equals(teleport.getTeleportType()) && !config.includeFishingCapeTeleports())
			{
				continue;
			}
			if ("Crafting Cape".equals(teleport.getTeleportType()) && !config.includeCraftingCapeTeleports())
			{
				continue;
			}
			if ("Construction Cape".equals(teleport.getTeleportType()) && !config.includeConstructionCapeTeleports())
			{
				continue;
			}
			if ("Necklace of Passage".equals(teleport.getTeleportType()) && !config.includeNecklaceOfPassageTeleports())
			{
				continue;
			}
			if ("Camulet".equals(teleport.getTeleportType()) && !config.includeCamuletTeleports())
			{
				continue;
			}
			if ("Pendant of Ates".equals(teleport.getTeleportType()) && !config.includePendantOfAtesTeleports())
			{
				continue;
			}
			if ("Pharaoh's Sceptre".equals(teleport.getTeleportType()) && !config.includePharaohsSceptreTeleports())
			{
				continue;
			}
			if ("Ring of the Elements".equals(teleport.getTeleportType()) && !config.includeRingOfTheElementsTeleports())
			{
				continue;
			}
			if ("Royal Seed Pod".equals(teleport.getTeleportType()) && !config.includeRoyalSeedPodTeleports())
			{
				continue;
			}
			if ("Slayer Ring".equals(teleport.getTeleportType()) && !config.includeSlayerRingTeleports())
			{
				continue;
			}
			if ("Master Scroll Book".equals(teleport.getTeleportType()) && !config.includeMSBTeleports())
			{
				continue;
			}
			if (isLocationChecked(teleportDestination))
			{
				continue;
			}

			double entropy = calculateEntropy(teleportDestination, possibleLocations);
			entropyValues.put(teleport, entropy);

			if (entropy < bestEntropyValue)
			{
				bestEntropyValue = entropy;
				bestTeleport = teleport;
			}
		}
	}

	private double calculateEntropy(WorldPoint teleportLocation, List<WorldPoint> possibleLocations)
	{
		if (possibleLocations.isEmpty())
		{
			return Double.MAX_VALUE;
		}
		double totalDistance = 0;
		for (WorldPoint location : possibleLocations)
		{
			double distance = teleportLocation.distanceTo2D(location);
			totalDistance += distance;
		}
		return totalDistance / possibleLocations.size();
	}

	private void updateCardinalInfoGain()
	{
		if (!isHotColdClueActive() || possibleLocations.isEmpty())
		{
			return;
		}

		WorldPoint playerLocation = client.getLocalPlayer().getWorldLocation();
		cardinalInfoGain = HotColdCardinalHelper.calculateCardinalInfoGain(possibleLocations, playerLocation);
		bestCardinalDirection = HotColdCardinalHelper.findBestCardinalDirection(cardinalInfoGain);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN && needsUpdate)
		{
			needsUpdate = false;
			clientThread.invokeLater(this::checkHotColdData);
		}
	}

	@Subscribe
	public void onGameTick(GameTick gameTick)
	{
		if (possibleLocationsCount > 0)
		{
			boolean isStillActive = isHotColdClueActive();
			if (!isStillActive)
			{
				resetPluginState();
			}
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage chatMessage)
	{
		if (chatMessage.getType() != ChatMessageType.GAMEMESSAGE)
		{
			return;
		}

		if (chatMessage.getMessage().startsWith(HOT_COLD_DEVICE_MESSAGE))
		{
			if (client.getLocalPlayer() != null)
			{
				WorldPoint playerLocation = client.getLocalPlayer().getWorldLocation();
				addCheckedLocation(playerLocation);
				lastMessageLocation = playerLocation;
			}

			needsUpdate = true;
			clientThread.invokeLater(this::checkHotColdData);
		}
	}
}
/*
 * Copyright (c) 2025, Lalochazia
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
