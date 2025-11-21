package net.runelite.client.plugins.pluginhub.com.afkdimmer;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup(AfkDimmerConfig.GROUP)
public interface AfkDimmerConfig extends Config
{
	String GROUP = "afkDimmer";
	@ConfigItem(
		position = 1,
		keyName = "dimmerStrength",
		name = "Dimmer Strength",
		description = "Sets the strength of the dimmer."
	)
	@Range(
			min = 0,
			max = 100
	)

	default int dimmerStrength(){
		return 50;
	}

	@ConfigItem(
			position = 2,
			keyName = "afkTime",
			name = "Afk Time",
			description = "Time(s) spent afk before dimming."
		)
	@Range(
			min = 0,
			max = 100
	)

	default int afkTime(){
		return 3;
	}

	@ConfigItem(
			position = 3,
			keyName = "idleUndim",
			name = "Undim while idle",
			description = "Undim the screen when you are idle."
	)

	default boolean idleUndim(){
		return false;
	}

}
