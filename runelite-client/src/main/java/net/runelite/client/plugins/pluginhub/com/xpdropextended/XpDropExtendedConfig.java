package net.runelite.client.plugins.pluginhub.com.xpdropextended;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("xpdropextended")
public interface XpDropExtendedConfig extends Config
{
	@ConfigItem(
			keyName = "hideMaxXPIcon",
			name = "Hide Max XP Icon",
			description = "Configure if the max xp icon will be shown or not",
			position = 0
	)
	default boolean hideMaxXPIcon()
	{
		return false;
	}

}

