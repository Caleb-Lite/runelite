package net.runelite.client.plugins.pluginhub.com.dateOverlay;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("DateOverlay")
public interface DateOverlayConfig extends Config
{
	String CONFIG_GROUP = "dateOverlay";

	@ConfigItem(
			keyName = "showTime",
			name = "Show Time",
			description = "Show hours, minutes and seconds?"
	)
	default boolean showTime()
	{
		return false;
	}

	@ConfigItem(
			keyName = "nameMonth",
			name = "Month name",
			description = "Show month as text instead of number"
	)
	default boolean nameOfMonth()
	{
		return false;
	}

	@ConfigItem(
			keyName = "shortYear",
			name = "Short Year",
			description = "Have year as 2 characters instead of 4"
	)
	default boolean shortYear()
	{
		return false;
	}

	@ConfigItem(
			keyName = "dateFormat",
			name = "Date Format",
			description = "Which format the date should be shown in"
	)
	default DateTimeFormats dateTimeFormat()
	{
		return DateTimeFormats.ddMMyyyy;
	}

	public static enum DateTimeFormats
	{
		ddMMyyyy,
		yyyyMMdd,
		MMddyyyy
	}
}
