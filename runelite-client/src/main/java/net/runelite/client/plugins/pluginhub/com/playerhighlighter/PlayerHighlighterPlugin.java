package net.runelite.client.plugins.pluginhub.com.playerhighlighter;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Player;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Player Highlighter"
)
public class PlayerHighlighterPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private PlayerHighlighterConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject PlayerHighlighterOverlay overlay;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
		log.info("Player Highlighter started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		log.info("Player Highlighter stopped!");
	}

	@Provides
	PlayerHighlighterConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PlayerHighlighterConfig.class);
	}
}
