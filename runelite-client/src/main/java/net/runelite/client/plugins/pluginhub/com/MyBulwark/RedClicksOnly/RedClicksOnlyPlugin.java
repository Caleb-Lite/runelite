package net.runelite.client.plugins.pluginhub.com.MyBulwark.RedClicksOnly;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.events.MenuOptionClicked;

@Slf4j
@PluginDescriptor(
	name = "Red Clicks Only"
)
public class RedClicksOnlyPlugin extends Plugin
{
	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		if (event.getMenuOption().equalsIgnoreCase("Walk here")) {
			event.consume();
		}
	}
}

