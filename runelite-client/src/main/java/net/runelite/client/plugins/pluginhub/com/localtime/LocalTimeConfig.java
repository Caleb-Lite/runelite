package net.runelite.client.plugins.pluginhub.com.localtime;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("rl-local-time")
public interface LocalTimeConfig extends Config
{
	@ConfigItem(
		keyName = "rl-local-time-showseconds",
		name = "Show Seconds",
		description = "Toggles whether or not the time should display seconds or just HH:MM"
	)
	default boolean showSeconds()
	{
		return true;
	}

	@ConfigItem(
			keyName = "rl-local-time-24hr",
			name = "Use 24hr time?",
			description = "Toggles whether or not the time should display in 24 hrs or 12hrs"
	)
	default boolean use24Hr()
	{
		return true;
	}
}

