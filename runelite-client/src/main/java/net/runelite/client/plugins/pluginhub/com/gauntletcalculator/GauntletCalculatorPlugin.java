package net.runelite.client.plugins.pluginhub.com.gauntletcalculator;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;

@Slf4j
@PluginDescriptor(
	name = "Gauntlet Calculator",
	description = "Calculates how many crystal shards and resources you need in the Gauntlet",
	tags = {"Corrupted", "Gauntlet", "Bwana Ian", "Resources", "Crystal Shards"}
)
public class GauntletCalculatorPlugin extends Plugin
{
	@Inject
	private ClientToolbar clientToolbar;

	private GauntletCalculatorPanel panel;
	private NavigationButton navButton;

	@Override
	protected void startUp(){
		startPanel();
	}

	@Override
	protected void shutDown(){
		clientToolbar.removeNavigation(navButton);
	}

	private void startPanel(){
		final BufferedImage icon = ImageUtil.loadImageResource(getClass(), "Crystal_shard.png");
		panel = injector.getInstance(GauntletCalculatorPanel.class);

		navButton = NavigationButton.builder()
				.tooltip("Gauntlet Calculator")
				.icon(icon)
				.priority(6)
				.panel(panel)
				.build();

		clientToolbar.addNavigation(navButton);
	}
}

