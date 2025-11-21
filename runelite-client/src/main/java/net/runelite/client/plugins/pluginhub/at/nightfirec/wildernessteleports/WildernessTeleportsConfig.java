package net.runelite.client.plugins.pluginhub.at.nightfirec.wildernessteleports;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("wildernessteleports")
public interface WildernessTeleportsConfig extends Config
{
	@ConfigItem(
		keyName = "unchargeWarningOutsideWilderness",
		name = "Charge warnings outside wilderness",
		description = "Show the uncharged teleport warning at all locations, not only when in the wilderness"
	)
	default boolean unchargeWarningOutsideWilderness()
	{
		return false;
	}
}

