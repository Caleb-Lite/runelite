package net.runelite.client.plugins.pluginhub.com.specinfo;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("specinfo")
public interface SpecInfoConfig extends Config
{
	@ConfigItem(
			keyName = "displayInfobox",
			name = "Display Infobox",
			description = "Show an infobox for special attack."
	)
	default boolean displayInfobox()
	{
		return true;
	}

	@ConfigItem(
			keyName = "lowSpecThreshold",
			name = "Low Spec Threshold",
			description = "Threshold for low special attack."
	)
	default int lowSpecThreshold()
	{
		return 0;
	}
}
