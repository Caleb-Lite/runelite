package net.runelite.client.plugins.pluginhub.dekvall.leftclickonly;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.MenuOpened;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.MouseManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Left Click Only",
	description = "Only allow leftclicks"
)
public class LeftClickOnlyPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private RightClickConsumer rightClickConsumer;

	@Inject
	private MouseManager mouseManager;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Left Click Only started!");
		mouseManager.registerMouseListener(rightClickConsumer);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Left Click Only stopped!");
		mouseManager.unregisterMouseListener(rightClickConsumer);
	}

	@Subscribe
	public void onMenuOpened(MenuOpened event)
	{
		MenuEntry first = event.getFirstEntry();
		client.setMenuEntries(new MenuEntry[]{first});
	}
}

