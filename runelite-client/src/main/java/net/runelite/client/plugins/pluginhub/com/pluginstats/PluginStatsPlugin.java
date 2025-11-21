package net.runelite.client.plugins.pluginhub.com.pluginstats;

import com.google.inject.Provides;
import javax.inject.Inject;

import net.runelite.client.plugins.pluginhub.com.pluginstats.overlays.PluginStatsOverlay;
import net.runelite.client.plugins.pluginhub.com.pluginstats.services.PluginStatsService;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ExternalPluginsChanged;
import net.runelite.client.events.PluginChanged;
import net.runelite.client.events.ProfileChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
		name = "Plugin Stats",
		description = "Overlay showing how many plugins are installed and enabled"
)
public class PluginStatsPlugin extends Plugin
{
	@Inject private OverlayManager overlayManager;
	@Inject private PluginStatsOverlay overlay;
	@Inject private PluginStatsService service;

	@Override
	protected void startUp()
	{
		log.debug("Plugin Stats started");
		overlayManager.add(overlay);
		service.recalc();
	}

	@Override
	protected void shutDown()
	{
		log.debug("Plugin Stats stopped");
		overlayManager.remove(overlay);
	}

	// Recalc immediately on relevant changes
	@Subscribe public void onPluginChanged(PluginChanged e) { service.recalc(); }
	@Subscribe public void onExternalPluginsChanged(ExternalPluginsChanged e) { service.recalc(); }
	@Subscribe public void onProfileChanged(ProfileChanged e) { service.recalc(); }

	@Provides
	PluginStatsConfig provideConfig(ConfigManager cm)
	{
		return cm.getConfig(PluginStatsConfig.class);
	}
}
