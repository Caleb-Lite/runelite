package net.runelite.client.plugins.pluginhub.com.localtime;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Local Time"
)
public class LocalTimePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private LocalTimeConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private LocalTimeOverlay localTimeOverlay;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(localTimeOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(localTimeOverlay);
	}

	@Subscribe
	public void onConfigChanged( ConfigChanged event )
	{
		if ( event.getGroup().equals( "rl-local-time" ) )
		{
//			overlayManager.remove(localTimeOverlay);
//			overlayManager.add(new LocalTimeOverlay(config));
		}
	}

	@Provides
	LocalTimeConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(LocalTimeConfig.class);
	}
}
