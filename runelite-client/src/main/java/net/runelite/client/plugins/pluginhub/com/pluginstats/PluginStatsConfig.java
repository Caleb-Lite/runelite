package net.runelite.client.plugins.pluginhub.com.pluginstats;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("plugin-stats")
public interface PluginStatsConfig extends Config
{
	@ConfigItem(
			keyName = "showInstalled",
			name = "Show Plugins Installed",
			description = "Show total number of plugins installed"
	)
	default boolean showInstalled() { return true; }

	@ConfigItem(
			keyName = "showEnabled",
			name = "Show Plugins Enabled",
			description = "Show total number of plugins currently enabled"
	)
	default boolean showEnabled() { return true; }
}