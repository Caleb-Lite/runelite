package net.runelite.client.plugins.pluginhub.io.robrichardson.challengemodeindicator;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameObjectDespawned;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.util.*;

@Slf4j
@PluginDescriptor(
	name = "Challenge Mode Indicator"
)
public class ChallengeModeIndicatorPlugin extends Plugin
{
	final List<Integer> leverIds = new ArrayList<>(Arrays.asList(
			ObjectID.LEVER_13672,
			ObjectID.LEVER_13673,
			ObjectID.LEVER_13674
	));

	@Inject
	private Client client;

	@Inject
	private ChallengeModeIndicatorConfig config;

	@Inject
	private ChallengeModeIndicatorOverlay overlay;

	@Inject
	private OverlayManager overlayManager;

	@Getter
	private boolean challengeModeActivated = false;

	@Getter
	private GameObject leverObject;

	@Getter
	private Tile leverTile;

	@Override
	protected void startUp() throws Exception {
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception {
		overlayManager.remove(overlay);
		leverObject = null;
		leverTile = null;
	}

	@Provides
	ChallengeModeIndicatorConfig provideConfig(ConfigManager configManager)  {
		return configManager.getConfig(ChallengeModeIndicatorConfig.class);
	}

	@Subscribe
	public void onGameStateChanged(final GameStateChanged event) {
		if (event.getGameState() == GameState.HOPPING || event.getGameState() == GameState.LOGIN_SCREEN)
		{
			challengeModeActivated = false;
		}
	}

	@Subscribe
	public void onGameObjectSpawned(final GameObjectSpawned event) {
		final GameObject gameObject = event.getGameObject();
		final int id = gameObject.getId();
		if(leverIds.contains(id)) {
			leverObject = gameObject;
			leverTile = event.getTile();
		}
	}

	@Subscribe
	private void onGameObjectDespawned(final GameObjectDespawned event) {
		final GameObject gameObject = event.getGameObject();
		final int id = gameObject.getId();
		if(leverIds.contains(id)) {
			leverObject = null;
			leverTile = null;
		}
	}

	@Subscribe
	public void onChatMessage(final ChatMessage event) {
		if (event.getType() != ChatMessageType.SPAM && event.getType() != ChatMessageType.GAMEMESSAGE) {
			return;
		}

		String enableString = "Challenge mode is on.";
		String disableString = "Challenge mode has ended.";
		if(Objects.equals(event.getMessage(), enableString)) {
			challengeModeActivated = true;
		} else if(Objects.equals(event.getMessage(), disableString)) {
			challengeModeActivated = false;
		}
	}
}

