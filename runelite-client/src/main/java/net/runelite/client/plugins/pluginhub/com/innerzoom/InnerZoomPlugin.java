package net.runelite.client.plugins.pluginhub.com.innerzoom;

import javax.inject.Inject;

import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.api.events.ScriptCallbackEvent;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@PluginDescriptor(
		name = "Inner Zoom",
		description = "Increases the inner zoom limit further"
)
public class InnerZoomPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject InnerZoomConfig config;

	@Provides
	InnerZoomConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(InnerZoomConfig.class);
	}

	@Subscribe(priority = -1) // after camera plugin
	public void onScriptCallbackEvent(ScriptCallbackEvent event)
	{
		if (event.getEventName().equals("innerZoomLimit"))
		{
			int[] intStack = client.getIntStack();
			int intStackSize = client.getIntStackSize();
			intStack[intStackSize - 1] = config.innerZoomLimit();
		}
	}
}
