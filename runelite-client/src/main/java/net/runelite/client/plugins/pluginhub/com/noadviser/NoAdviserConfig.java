package net.runelite.client.plugins.pluginhub.com.noadviser;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("noadviser")
public interface NoAdviserConfig extends Config
{
	@ConfigItem(
		keyName = "alwaysdisabled",
		name = "Always Disabled",
		description = "By default the plugin only disables the button in combat."
	)
	default boolean alwaysDisabled() { return false; }
}

