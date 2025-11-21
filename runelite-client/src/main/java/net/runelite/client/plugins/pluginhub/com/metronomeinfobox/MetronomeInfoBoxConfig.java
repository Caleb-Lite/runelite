package net.runelite.client.plugins.pluginhub.com.metronomeinfobox;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(MetronomeInfoBoxConfig.GROUP)
public interface MetronomeInfoBoxConfig extends Config
{
	String GROUP = "metronomeinfobox";

	@ConfigItem(
		keyName = "ticks",
		name = "Ticks",
		description = "Number of ticks to count up to until counting repeats"
	)
	default int ticks()
	{
		return 4;
	}
}
