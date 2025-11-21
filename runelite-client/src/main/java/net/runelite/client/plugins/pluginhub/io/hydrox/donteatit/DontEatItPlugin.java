package net.runelite.client.plugins.pluginhub.io.hydrox.donteatit;

import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.KeyCode;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.ClientTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.Text;
import javax.inject.Inject;

@PluginDescriptor(
	name = "Don't eat it!",
	description = "Make every item that has a Use option have it as left click",
	tags = {"swap","swapper","menu","entry","menu entry swapper","use","drink","accident"}
)
public class DontEatItPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private DontEatItOverlay overlay;

	@Inject
	private OverlayManager overlayManager;

	@Provides
	DontEatItConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(DontEatItConfig.class);
	}

	@Override
	public void startUp()
	{
		overlayManager.add(overlay);
	}

	@Override
	public void shutDown()
	{
		overlayManager.remove(overlay);
	}

	/*
		Code modified from MenuEntrySwapperPlugin, btw
	 */
	@Subscribe
	public void onClientTick(ClientTick clientTick)
	{
		// The menu is not rebuilt when it is open, so don't swap or else it will
		// repeatedly swap entries
		if (client.getGameState() != GameState.LOGGED_IN || client.isMenuOpen() || client.isKeyPressed(KeyCode.KC_SHIFT))
		{
			return;
		}

		MenuEntry[] menuEntries = client.getMenuEntries();
		int useIndex = -1;
		int topIndex = menuEntries.length - 1;

		for (int i = 0; i < topIndex; i++)
		{
			if (Text.removeTags(menuEntries[i].getOption()).equals("Use"))
			{
				useIndex = i;
				break;
			}
		}

		if (useIndex == -1)
		{
			return;
		}

		MenuEntry entry1 = menuEntries[useIndex];
		MenuEntry entry2 = menuEntries[topIndex];

		menuEntries[useIndex] = entry2;
		menuEntries[topIndex] = entry1;

		client.setMenuEntries(menuEntries);
	}
}

