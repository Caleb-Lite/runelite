package net.runelite.client.plugins.pluginhub.com.togcrowdsourcing.ui;

import net.runelite.client.plugins.pluginhub.com.togcrowdsourcing.CrowdsourcingManager;
import net.runelite.client.plugins.pluginhub.com.togcrowdsourcing.ToGCrowdsourcingConfig;
import net.runelite.client.plugins.pluginhub.com.togcrowdsourcing.ToGCrowdsourcingPlugin;
import net.runelite.client.plugins.pluginhub.com.togcrowdsourcing.WorldData;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.api.widgets.ComponentID;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.events.WorldsFetch;
import net.runelite.client.game.WorldService;
import net.runelite.client.input.KeyManager;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.*;
import net.runelite.http.api.worlds.World;
import net.runelite.http.api.worlds.WorldResult;
import net.runelite.api.EnumID;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;

@Slf4j
public class WorldHopper
{
	private static final int REFRESH_THROTTLE = 60_000; // ms
	private static final int MAX_PLAYER_COUNT = 1950;

	private static final int DISPLAY_SWITCHER_MAX_ATTEMPTS = 3;

	@Inject
	private Client client;

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

	private ToGCrowdsourcingConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Getter
	@Inject
	private WorldService worldService;

	@Getter
	@Inject
	private CrowdsourcingManager crowdsourcingManager;

	private ScheduledExecutorService hopperExecutorService;

	private NavigationButton navButton;
	private WorldSwitcherPanel panel;

	private net.runelite.api.World quickHopTargetWorld;
	private int displaySwitcherAttempts = 0;

	@Getter
	private int lastWorld;

	private int currentWorld;
	private Instant lastFetch;

	@Getter
	@Setter
	private boolean getError;

	@Getter
	@Setter
	private ArrayList<WorldData> worldData = new ArrayList<>();

	@Inject
	private WorldHopper()
	{

	}

	public void startUpWorldHopper(ToGCrowdsourcingConfig config)
	{
		panel = new WorldSwitcherPanel(this);
		this.config = config;

		final BufferedImage icon = ImageUtil.loadImageResource(ToGCrowdsourcingPlugin.class, "/tog-icon.png");
		navButton = NavigationButton.builder()
			.tooltip("ToG Crowdsourcing")
			.icon(icon)
			.priority(10)
			.panel(panel)
			.build();

		if (config.showSidebar())
		{
			clientToolbar.addNavigation(navButton);
		}

		// populate initial world list
		updateList();

		crowdsourcingManager.makeGetRequest(this);
	}

	public void shutDownWorldHopper()
	{
		clientToolbar.removeNavigation(navButton);

	}

	@Subscribe
	public void onConfigChanged(final ConfigChanged event)
	{
		if (event.getGroup().equals(ToGCrowdsourcingConfig.GROUP))
		{
			switch (event.getKey())
			{
				case "showSidebar":
					if (config.showSidebar())
					{
						clientToolbar.addNavigation(navButton);
					}
					else
					{
						clientToolbar.removeNavigation(navButton);
					}
					break;
			}
			if (event.getKey().equals("showOverlay")) { return; }
			else { updateList(); }
		}
	}

	int getCurrentWorld()
	{
		return client.getWorld();
	}

	void hopTo(World world)
	{
		clientThread.invoke(() -> hop(world.getId()));
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		// If the player has disabled the side bar plugin panel, do not update the UI
		if (config.showSidebar() && gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			if (lastWorld != client.getWorld())
			{
				int newWorld = client.getWorld();
				panel.switchCurrentHighlight(newWorld, lastWorld);
				lastWorld = newWorld;
			}
		}
	}

	// This is the right click refresh menu item
	void refresh()
	{
		Instant now = Instant.now();
		if (lastFetch != null && now.toEpochMilli() - lastFetch.toEpochMilli() < REFRESH_THROTTLE)
		{
			log.debug("Throttling world refresh");
			return;
		}

		lastFetch = now;
		worldService.refresh();
	}

	@Subscribe
	public void onWorldsFetch(WorldsFetch worldsFetch)
	{
//		System.out.println("onWorldFetch");
		synchronized (this)
		{
			crowdsourcingManager.makeGetRequest(this);
			updateList();
		}
	}

	/**
	 * This method ONLY updates the list's UI, not the actual world list and data it displays.
	 */
	public void updateList()
	{
//		SwingUtilities.invokeLater(() -> panel.populate(worldData, config));
		clientThread.invokeLater(() ->
		{
			EnumComposition locationEnum = client.getGameState().getState() >= GameState.LOGIN_SCREEN.getState() ? client.getEnum(EnumID.WORLD_LOCATIONS) : null;
			SwingUtilities.invokeLater(() -> panel.populate(worldData, config, locationEnum));
		});
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

			chatMessageManager
				.queue(QueuedMessage.builder()
					.type(ChatMessageType.CONSOLE)
					.runeLiteFormattedMessage(chatMessage)
					.build());
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

				chatMessageManager
					.queue(QueuedMessage.builder()
						.type(ChatMessageType.CONSOLE)
						.runeLiteFormattedMessage(chatMessage)
						.build());

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

}

