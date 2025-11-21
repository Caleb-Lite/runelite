package net.runelite.client.plugins.pluginhub.com.ironvalue;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Iron Value",
	description = "Item Values for Iron Players",
	tags = {"iron", "item", "value", "price", "blood", "bank", "inventory", "overlay", "tooltips"}
)
public class IronValuePlugin extends Plugin
{
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private IronValueOverlay overlay;

	@Provides
	IronValueConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(IronValueConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		log.info("Iron Value starting...");
		overlayManager.add(overlay);
		log.info("Iron Value started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Iron Value stopping...");
		overlayManager.remove(overlay);
		log.info("Iron Value stopped!");
	}
}

