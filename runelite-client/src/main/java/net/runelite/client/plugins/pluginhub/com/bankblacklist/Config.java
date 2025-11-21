package net.runelite.client.plugins.pluginhub.com.bankblacklist;

import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("Bank Block List")
public interface Config extends net.runelite.client.config.Config
{
	@ConfigItem(
		keyName = "blackList",
		name = "Black List",
		description = "Add items to cause a warning notification if banked."
	)
	default String blackList()
	{
		return "";
	}

	@ConfigItem(
		keyName = "blackList",
		name = "",
		description = ""
	)
	void setBlackList(String key);

	@ConfigItem(
		keyName = "includePlaceholders",
		name = "Include Placeholders",
		description = "Determines if to trigger warnings on placeholders."
	)
	default boolean includePlaceholders()
	{
		return true;
	}

	@ConfigItem(
		keyName = "enableShiftClick",
		name = "Enable shift click to add/remove items",
		description = "Determines if to trigger add/remove option on shift click."
	)
	default boolean enableShiftClick()
	{
		return false;
	}
}

