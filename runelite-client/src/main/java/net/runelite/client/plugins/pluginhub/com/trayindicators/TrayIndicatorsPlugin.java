package net.runelite.client.plugins.pluginhub.com.trayindicators;

import com.google.inject.Provides;
import com.trayindicators.icons.*;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.awt.*;
import java.util.*;

@Slf4j
@PluginDescriptor(
	name = "Tray Indicators",
	description = "Displays your hitpoints, prayer, absorption, special attack, cannon ammo or inventory count in the system tray.",
	tags = {"notifications"}
)
public class TrayIndicatorsPlugin extends Plugin
{
	private final Map<IconType, Icon> trayIcons = new HashMap<>();

	@Inject
	private Client client;

	@Inject
	private TrayIndicatorsConfig config;

	@Provides
	TrayIndicatorsConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TrayIndicatorsConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		if (!SystemTray.isSupported())
		{
			log.error("System tray is not supported.");
			return;
		}

		if (trayIcons.isEmpty())
		{
			trayIcons.put(IconType.Health, new HealthIcon(client, config));
			trayIcons.put(IconType.Prayer, new PrayerIcon(client, config));
			trayIcons.put(IconType.Absorption, new AbsorptionIcon(client, config));
			trayIcons.put(IconType.Cannon, new CannonIcon(client, config));
			trayIcons.put(IconType.Inventory, new InventoryIcon(client, config));
			trayIcons.put(IconType.Spec, new SpecIcon(client, config));
		}
	}

	@Override
	protected void shutDown() throws Exception
	{
		trayIcons.forEach((iconType, icon) -> icon.removeIcon());
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		trayIcons.forEach((iconType, icon) -> icon.onGameTick(event));
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		trayIcons.forEach((iconType, icon) -> icon.onGameStateChanged(event));
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		trayIcons.forEach((iconType, icon) -> icon.onItemContainerChanged(event));
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (!event.getGroup().equals("Tray Indicators"))
		{
			return;
		}

		trayIcons.forEach((iconType, icon) -> icon.onConfigChanged(event));
	}
}