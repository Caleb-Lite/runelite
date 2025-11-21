package net.runelite.client.plugins.pluginhub.com.yamareminder;

import com.google.inject.Provides;
import com.google.inject.Injector;
import javax.inject.Inject;
import javax.swing.*;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import net.runelite.client.plugins.pluginhub.com.yamareminder.YamaAttackPanel;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;

@Slf4j
@PluginDescriptor(
	name = "YamaReminder"
)
public class YamaReminderPlugin extends Plugin
{
	private static final BufferedImage ICON = createIcon();
	private static final int CHASM_OF_FIRE_REGION_ID = 6045;
	
	@Inject
	private Client client;

	@Inject
	private ClientToolbar clientToolbar;
	
	@Inject
	private Injector injector;

	private YamaAttackPanel yamaAttackPanel;
	private NavigationButton navButton;
	private boolean inChasmOfFire = false;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Yama Reminder started!");
		
		yamaAttackPanel = injector.getInstance(YamaAttackPanel.class);
		
		navButton = NavigationButton.builder()
				.panel(yamaAttackPanel)
				.icon(ICON)
				.priority(5)
				.tooltip("Yama Attack Reminder")
				.build();
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Yama Reminder stopped");
		clientToolbar.removeNavigation(navButton);
	}

	@Subscribe
	public void onGameTick(GameTick gameTick)
	{
		if (client.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}

		boolean currentlyInChasm = isInChasmOfFire();
		
		if (currentlyInChasm && !inChasmOfFire)
		{
			// Entering Chasm of Fire - show panel
			inChasmOfFire = true;
			clientToolbar.addNavigation(navButton);
			SwingUtilities.invokeLater(() -> clientToolbar.openPanel(navButton));
		}
		else if (!currentlyInChasm && inChasmOfFire)
		{
			// Leaving Chasm of Fire - hide panel
			inChasmOfFire = false;
			clientToolbar.removeNavigation(navButton);
		}
	}

	private boolean isInChasmOfFire()
	{
		int[] regions = client.getMapRegions();
		if (regions == null)
		{
			return false;
		}
		
		for (int region : regions)
		{
			if (region == CHASM_OF_FIRE_REGION_ID)
			{
				return true;
			}
		}
		return false;
	}

	private static BufferedImage createIcon()
	{
		BufferedImage icon = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = icon.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Draw a simple "Y" for Yama
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 14));
		g.drawString("Y", 3, 13);
		
		g.dispose();
		return icon;
	}
}
