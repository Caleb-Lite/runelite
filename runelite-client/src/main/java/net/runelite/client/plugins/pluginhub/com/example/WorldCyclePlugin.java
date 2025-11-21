package net.runelite.client.plugins.pluginhub.com.example;

import com.google.inject.Provides;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.inject.Inject;
import java.awt.Color;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.widgets.ComponentID;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.WorldService;
import net.runelite.client.input.KeyManager;
import net.runelite.client.party.PartyMember;
import net.runelite.client.party.PartyService;
import net.runelite.client.party.WSClient;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.ClientUI;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.*;
import net.runelite.http.api.worlds.World;
import net.runelite.http.api.worlds.WorldResult;
import net.runelite.http.api.worlds.WorldType;

@PluginDescriptor(
	name = "World Cycle",
	description = "Lighter, custom version of world hopper used to hop between a set of worlds circularly",
	tags = {"switcher","cycle","world hopper"}
)
@Slf4j
public class WorldCyclePlugin extends Plugin
{
	private static final int MAX_PLAYER_COUNT = 1950;

	private static final int DISPLAY_SWITCHER_MAX_ATTEMPTS = 3;


	@Inject
	private Client client;

	@Inject
	private ClientUI clientUI;

	@Inject
	private ClientThread clientThread;

	@Inject
	private ConfigManager configManager;

	@Inject
	private ClientToolbar clientToolbar;

	@Inject
	private KeyManager keyManager;

	@Inject
	private ChatMessageManager chatMessageManager;

	@Inject
	private WorldCycleConfig config;

	@Inject
	private WorldService worldService;

	@Inject
	private WSClient wsClient;

	@Inject
	private PartyService partyService;

	@Inject
	private WorldCycleOverlay overlay;

	@Inject
	private OverlayManager overlayManager;

	private NavigationButton navButton_cycle;
	public WorldCyclePanel panel_cycle;

	private net.runelite.api.World quickHopTargetWorld;
	private int displaySwitcherAttempts = 0;

	//50 worlds separated by commas, prevents potential abuse of spamming large packets in party
	//this does not limit local usage, simply prevents egregious party updates
	private final int MAXIMUM_PACKET_LENGTH = 199;

	int currentWorldId, nextWorldId, previousWorldId = -1;
	GameState oneStatePrior = GameState.LOGGING_IN;
	GameState twoStatesPrior = GameState.LOGGING_IN;

	Color configWorldPanelColor,configPreviousWorldColor,configCurrentWorldColor,configNextWorldColor;
	int configFontSize;
	boolean configBoldFont,configDisplayPreviousWorld,configDisplayCurrentWorld,configDisplayNextWorld;
	boolean overlayActive;

	private final HotkeyListener previousKeyListener = new HotkeyListener(() -> config.previousKey())
	{
		@Override
		public void hotkeyPressed()
		{
			clientThread.invoke(() -> hop(true));
		}
	};
	private final HotkeyListener nextKeyListener = new HotkeyListener(() -> config.nextKey())
	{
		@Override
		public void hotkeyPressed()
		{
			clientThread.invoke(() -> hop(false));
		}
	};

	@Provides
	WorldCycleConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(WorldCycleConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayActive = false;
		CacheConfigs();
		wsClient.registerMessage(WorldCycleUpdate.class);
		keyManager.registerKeyListener(previousKeyListener);
		keyManager.registerKeyListener(nextKeyListener);

		panel_cycle = new WorldCyclePanel(client, configManager,this);

		final BufferedImage icon_cycle = ImageUtil.getResourceStreamFromClass(getClass(), "icon_cycle.png");
		navButton_cycle = NavigationButton.builder()
				.tooltip("World Cycle")
				.icon(icon_cycle)
				.priority(1)
				.panel(panel_cycle)
				.build();

		clientToolbar.addNavigation(navButton_cycle);

		CacheNearbyWorlds();
	}

	@Override
	protected void shutDown() throws Exception
	{
		wsClient.unregisterMessage(WorldCycleUpdate.class);
		keyManager.unregisterKeyListener(previousKeyListener);
		keyManager.unregisterKeyListener(nextKeyListener);

		clientToolbar.removeNavigation(navButton_cycle);
		overlayManager.remove(overlay);
	}

	/**
	 * Detect that a world change has occured by checking the previous state, against the prior previous state
	 * Refreshes the cached worlds for overlay display.
	 */
	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		GameState state = event.getGameState();
		if(oneStatePrior != state){
			if(oneStatePrior == GameState.LOADING && twoStatesPrior == GameState.HOPPING){
				//a world hop has occured and the world has loaded.
				CacheNearbyWorlds();
			}
			twoStatesPrior = oneStatePrior;
			oneStatePrior = state;
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged configChanged)
	{
		if (!configChanged.getGroup().equals(WorldCycleConfig.GROUP))
		{
			return;
		}

		CacheConfigs();
	}

	/**
	 * Cache configs to prevent spam calls to config in the overlay.
	 * Add or remove the overlay panel if the status has changed.
	 */
	public void CacheConfigs(){
		configWorldPanelColor = config.worldPanelColor();
		configPreviousWorldColor = config.previousWorldColor();
		configCurrentWorldColor = config.currentWorldColor();
		configNextWorldColor = config.nextWorldColor();
		configFontSize = config.fontSize();
		configBoldFont = config.boldFont();
		configDisplayPreviousWorld = config.displayPreviousWorld();
		configDisplayCurrentWorld = config.displayCurrentWorld();
		configDisplayNextWorld = config.displayNextWorld();

		RefreshOverlay();
	}

	/**
	 * Determine whether overlay should be active and adds/removes it from the overlayManager.
	 * Returns if current state is already set correctly.
	 */
	void RefreshOverlay()
	{
		boolean active = configDisplayPreviousWorld || configDisplayCurrentWorld || configDisplayNextWorld;

		if(active == overlayActive)
			return;

		if(active)
		{
			overlayManager.add(overlay);
		}
		else
		{
			overlayManager.remove(overlay);
		}

		overlayActive = active;
	}

	/**
	 * Get the next or previous world in relation to the current world in the set.
	 * In the case that the current world is not in the set it defaults to : first world is next, last world is previous
	 * In the case that no world cycle is present it returns the standard next/previous world in relation to the players current world.
	 */
	private World GetWorld(boolean previous)
	{
		WorldResult worldResult = worldService.getWorlds();
		if (worldResult == null)
		{
			return null;
		}

		World currentWorld = worldResult.findWorld(client.getWorld());

		if (currentWorld == null)
		{
			return null;
		}

		EnumSet<WorldType> currentWorldTypes = currentWorld.getTypes().clone();
		// Make it so you always hop out of PVP and high risk worlds
		currentWorldTypes.remove(WorldType.PVP);
		currentWorldTypes.remove(WorldType.HIGH_RISK);

		// Don't regard these worlds as a type that must be hopped between
		currentWorldTypes.remove(WorldType.BOUNTY);
		currentWorldTypes.remove(WorldType.SKILL_TOTAL);
		currentWorldTypes.remove(WorldType.LAST_MAN_STANDING);

		List<World> customWorldCycle = getCustomWorldCycle();
		boolean cyclePresent = !customWorldCycle.isEmpty();
		List<World> worlds = cyclePresent ? customWorldCycle : worldResult.getWorlds();
		int attemptedWorlds = 0;

		//don't limit the subscription type if the user is purposely attempting to hop between members and f2p
		if(cyclePresent){
			currentWorldTypes.remove(WorldType.MEMBERS);
		}

		int worldIdx = worlds.indexOf(currentWorld);
		int totalLevel = client.getTotalLevel();

		World world;
		do
		{
			/*
				Get the previous or next world in the list,
				starting over at the other end of the list
				if there are no more elements in the
				current direction of iteration.
			 */
			if (previous)
			{
				worldIdx--;

				if (worldIdx < 0)
				{
					worldIdx = worlds.size() - 1;
				}
			}
			else
			{
				worldIdx++;

				if (worldIdx >= worlds.size())
				{
					worldIdx = 0;
				}
			}

			world = worlds.get(worldIdx);

			EnumSet<WorldType> types = world.getTypes().clone();

			types.remove(WorldType.BOUNTY);
			// Treat LMS world like casual world
			types.remove(WorldType.LAST_MAN_STANDING);

			//don't limit the subscription type if the user is purposely attempting to hop between members and f2p
			if(cyclePresent){
				types.remove(WorldType.MEMBERS);
			}

			if (types.contains(WorldType.SKILL_TOTAL))
			{
				try
				{
					int totalRequirement = Integer.parseInt(world.getActivity().substring(0, world.getActivity().indexOf(" ")));

					if (totalLevel >= totalRequirement)
					{
						types.remove(WorldType.SKILL_TOTAL);
					}
				}
				catch (NumberFormatException ex)
				{
					log.warn("Failed to parse total level requirement for target world", ex);
				}
			}

			// Avoid switching to near-max population worlds, as it will refuse to allow the hop if the world is full
			if (world.getPlayers() >= MAX_PLAYER_COUNT)
			{
				//We've searched every world and not found a suitable world, break from loop
				if(++attemptedWorlds == worlds.size()){
					world = currentWorld;
					break;
				}
				continue;
			}

			// Break out if we've found a good world to hop to
			if (currentWorldTypes.equals(types))
			{
				break;
			}else{
				//We've searched every world and not found a suitable world, break from loop
				if(++attemptedWorlds == worlds.size()){
					world = currentWorld;
					break;
				}
			}
		}
		while (world != currentWorld);
		return world;
	}

	private void hop(boolean previous)
	{
		WorldResult worldResult = worldService.getWorlds();
		if (worldResult == null || client.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}

		World currentWorld = worldResult.findWorld(client.getWorld());

		if (currentWorld == null)
		{
			return;
		}

		World world = GetWorld(previous);
		if (world == currentWorld || world == null)
		{
			String chatMessage = new ChatMessageBuilder()
				.append(ChatColorType.NORMAL)
				.append("Couldn't find a world to quick-hop to.")
				.build();

			SendGameMessage(chatMessage);
		}
		else
		{
			hop(world.getId());
		}
	}

	private void hop(int worldId)
	{
		assert client.isClientThread();

		WorldResult worldResult = worldService.getWorlds();
		// Don't try to hop if the world doesn't exist
		World world = worldResult.findWorld(worldId);
		if (world == null)
		{
			return;
		}

		final net.runelite.api.World rsWorld = client.createWorld();
		rsWorld.setActivity(world.getActivity());
		rsWorld.setAddress(world.getAddress());
		rsWorld.setId(world.getId());
		rsWorld.setPlayerCount(world.getPlayers());
		rsWorld.setLocation(world.getLocation());
		rsWorld.setTypes(WorldUtil.toWorldTypes(world.getTypes()));

		if (client.getGameState() == GameState.LOGIN_SCREEN)
		{
			// on the login screen we can just change the world by ourselves
			client.changeWorld(rsWorld);
			return;
		}

		if (config.showWorldHopMessage())
		{
			String chatMessage = new ChatMessageBuilder()
				.append(ChatColorType.NORMAL)
				.append("Quick-hopping to World ")
				.append(ChatColorType.HIGHLIGHT)
				.append(Integer.toString(world.getId()))
				.append(ChatColorType.NORMAL)
				.append("..")
				.build();

			SendGameMessage(chatMessage);
		}

		quickHopTargetWorld = rsWorld;
		displaySwitcherAttempts = 0;
	}


	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (quickHopTargetWorld == null)
		{
			return;
		}

		if (client.getWidget(ComponentID.WORLD_SWITCHER_WORLD_LIST) == null)
		{
			client.openWorldHopper();

			if (++displaySwitcherAttempts >= DISPLAY_SWITCHER_MAX_ATTEMPTS)
			{
				String chatMessage = new ChatMessageBuilder()
					.append(ChatColorType.NORMAL)
					.append("Failed to quick-hop after ")
					.append(ChatColorType.HIGHLIGHT)
					.append(Integer.toString(displaySwitcherAttempts))
					.append(ChatColorType.NORMAL)
					.append(" attempts.")
					.build();

				SendGameMessage(chatMessage);

				resetQuickHopper();
			}
		}
		else
		{
			client.hopToWorld(quickHopTargetWorld);
			resetQuickHopper();
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage event)
	{
		if (event.getType() != ChatMessageType.GAMEMESSAGE)
		{
			return;
		}

		if (event.getMessage().equals("Please finish what you're doing before using the World Switcher."))
		{
			resetQuickHopper();
		}
	}

	private void resetQuickHopper()
	{
		displaySwitcherAttempts = 0;
		quickHopTargetWorld = null;
	}

	public void ChangeWorldSet(String worldset,boolean fromServer){

		//request is local, send to server
		if(!fromServer){
			if(LocalMemberIsValid()){
				if(worldset.length() > MAXIMUM_PACKET_LENGTH){
					PostMessageSetNotSent();
				}else{
					partyService.send(new WorldCycleUpdate(worldset));
				}
			}
		}

		//always handle from self, only handle from server if accept cycle is on
		if(!fromServer || config.acceptPartyCycle()){
			panel_cycle.pendingRequest = true;
			panel_cycle.uiInput.setWorldSetInput(worldset);
			CacheNearbyWorlds();
			PostMessageSetChanged(worldset,fromServer);
		}

	}

	public void PostMessageSetChanged(String worldset, boolean fromServer){
		String updateMessage = "World set updated"
				+ (fromServer ?  " by party member" : "")
				+ (worldset.length() <= 39 ? "\n                          ("+worldset+")" : "");
		String chatMessage = new ChatMessageBuilder()
				.append(ChatColorType.HIGHLIGHT)
				.append("[World Cycle] ")
				.append(ChatColorType.NORMAL)
				.append(updateMessage)
				.build();
		SendGameMessage(chatMessage);
	}

	public void PostMessageSetNotSent(){
		String chatMessage = new ChatMessageBuilder()
				.append(ChatColorType.HIGHLIGHT)
				.append("[World Cycle] ")
				.append(ChatColorType.HIGHLIGHT)
				.append("World set not sent to party, Maximum of 50 worlds in the set.")
				.build();
		SendGameMessage(chatMessage);
	}

	public void SendGameMessage(String chatMessage){
		chatMessageManager.queue(QueuedMessage.builder()
				.type(ChatMessageType.CONSOLE)
				.runeLiteFormattedMessage(chatMessage)
				.build());
	}

	public boolean LocalMemberIsValid(){
		PartyMember localMember = partyService.getLocalMember();
		return (localMember != null);
	}

	@Subscribe
	public void onWorldCycleUpdate(WorldCycleUpdate message)
	{
		//don't allow self updates, handle locally
		if(partyService.getLocalMember().getMemberId() == message.getMemberId()){
			return;
		}

		if(LocalMemberIsValid()){
			ChangeWorldSet(message.getWorldSet(),true);
		}
	}
	//Validate the world exists and isn't pvp before returning as a valid cycle world
	World ValidateWorld(int worldNum){

		WorldResult worldResult = worldService.getWorlds();
		if (worldResult == null) {
			return null;
		}

		World world = worldResult.findWorld(worldNum);
		if(world == null){
			return null;
		}

		//ensure there are zero instances of pvp worlds in the world cycle
		EnumSet<WorldType> currentWorldTypes = world.getTypes().clone();
		if(currentWorldTypes.contains(WorldType.PVP) || currentWorldTypes.contains(WorldType.HIGH_RISK)){
			return null;
		}

		return world;
	}

	List<World> getCustomWorldCycle(){

		List<World> worldCycleList = new ArrayList<>();

		String worldList = GetWorldSet();
		if (worldList.isEmpty())
		{
			return worldCycleList;
		}

		WorldResult worldResult = worldService.getWorlds();
		if (worldResult == null)
		{
			return worldCycleList;
		}

		Text.fromCSV(worldList).stream()
				.mapToInt(s ->
				{
					try
					{
						return Integer.parseInt(s);
					}
					catch (NumberFormatException e)
					{
						return 0;
					}
				})
				.filter(world -> ValidateWorld(world) != null)
				.forEach(world -> worldCycleList.add(worldResult.findWorld(world)));
		return worldCycleList;
	}

	/**
	 * Cache current, previous and next world in the set
	 */
	void CacheNearbyWorlds(){
		currentWorldId = client.getWorld();

		World previousWorld = GetWorld(true);
		if(previousWorld != null){
			previousWorldId = previousWorld.getId();
		}else{
			previousWorldId = -1;
		}

		World nextWorld = GetWorld(false);
		if(nextWorld != null){
			nextWorldId = nextWorld.getId();
		}else{
			nextWorldId = -1;
		}
	}

	public String GetWorldSet(){
		return panel_cycle.uiInput.getWorldSetInput();
	}
}
