package net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@PluginDescriptor(
	name = "Item XP",
	description = "A plugin that adds xp potential tooltips on items",
	tags = {"item", "xp", "farming", "prayer", "firemaking"}
)
@Slf4j
public class ItemXPPlugin extends Plugin
{
	@Inject	private ItemXPOverlay overlay;
	@Inject	private OverlayManager overlayManager;
	@Inject	private Client client;
	@Inject private ItemXPVersion versionManager;

	public static final String NEW_LINE = "</br>";
	public static final DecimalFormat df = new DecimalFormat("#,##0.#");

	static
	{
		df.setRoundingMode(RoundingMode.FLOOR);
	}

	@Override
	protected void startUp()
	{

		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			versionManager.CheckForNewVersion();
		}
	}

	@Provides
	ItemXPConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ItemXPConfig.class);
	}
}

