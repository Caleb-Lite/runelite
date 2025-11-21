package net.runelite.client.plugins.pluginhub.com.orbhider;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
	name = "Orb Hider",
	description = "Hides minimap orbs",
	tags = {"orbs", "orb", "minimap", "spec", "hide"}
)
public class OrbHiderPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private OrbHiderConfig config;

	@Override
	protected void startUp() throws Exception
	{
		updateAllOrbs();
	}

	@Override
	protected void shutDown() throws Exception
	{
		showAllOrbs();
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded event)
	{
		if (event.getGroupId() == WidgetID.MINIMAP_GROUP_ID)
		{
			updateAllOrbs();
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		updateAllOrbs();
	}

	@Provides
	OrbHiderConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(OrbHiderConfig.class);
	}

	public void updateAllOrbs()
	{
		updateOrb(client.getWidget(WidgetInfo.MINIMAP_XP_ORB), config.hideXpOrb());
		updateOrb(client.getWidget(WidgetInfo.MINIMAP_HEALTH_ORB), config.hideHealthOrb());
		updateOrb(client.getWidget(WidgetInfo.MINIMAP_PRAYER_ORB), config.hidePrayerOrb());
		updateOrb(client.getWidget(WidgetInfo.MINIMAP_RUN_ORB), config.hideRunOrb());
		updateOrb(client.getWidget(WidgetInfo.MINIMAP_SPEC_ORB), config.hideSpecOrb());
		updateOrb(client.getWidget(WidgetInfo.MINIMAP_WORLDMAP_ORB), config.hideWorldMapOrb());
	}

	public void showAllOrbs()
	{
		updateOrb(client.getWidget(WidgetInfo.MINIMAP_XP_ORB), false);
		updateOrb(client.getWidget(WidgetInfo.MINIMAP_HEALTH_ORB),false);
		updateOrb(client.getWidget(WidgetInfo.MINIMAP_PRAYER_ORB), false);
		updateOrb(client.getWidget(WidgetInfo.MINIMAP_RUN_ORB), false);
		updateOrb(client.getWidget(WidgetInfo.MINIMAP_SPEC_ORB), false);
		updateOrb(client.getWidget(WidgetInfo.MINIMAP_WORLDMAP_ORB), false);
	}

	public void updateOrb(Widget orb, boolean hidden)
	{
		if (orb != null)
		{
			orb.setHidden(hidden);
		}
	}
}
