package net.runelite.client.plugins.pluginhub.com.cbowanimationstall;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("cbowanimationstall")
public interface CbowAnimationStallConfig extends Config
{
	@ConfigItem(
			position = 0,
			keyName = "localPlayerOnly",
			name = "Local Player Only",
			description = "Only replace your own animation, ignore other player animations"
	)
	default boolean localPlayerOnly()
	{
		return true;
	}
}

