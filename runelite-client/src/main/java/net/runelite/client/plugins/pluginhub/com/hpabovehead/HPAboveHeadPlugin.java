package net.runelite.client.plugins.pluginhub.com.hpabovehead;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
		name = "HP Above Head"
)
public class HPAboveHeadPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private HPAboveHeadConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private HPAboveHeadOverlay overlay;

	@Override
	protected void startUp()
	{
		log.info("HP Above Head started!");
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown()
	{
		log.info("HP Above Head stopped!");
		overlayManager.remove(overlay);
	}

	@Provides
	HPAboveHeadConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(HPAboveHeadConfig.class);
	}
}