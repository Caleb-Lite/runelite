package net.runelite.client.plugins.pluginhub.com.clairemira;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("untrimmedSlayerCapeHelper")
public interface UntrimmedSlayerCapeHelperConfig extends Config
{
	@ConfigItem(
			keyName = "showSlayerExpRemaining",
			name = "Show Slayer XP remaining to 99",
			description = "Show the amount of slayer experience remaining to obtain 99."
	)
	default boolean showSlayerExpRemaining()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showProjectedHitpointsExp",
			name = "Show Projected Hitpoints XP",
			description = "Show your expected Hitpoints XP after reaching 99 Slayer from standard combat."
	)
	default boolean showProjectedHitpointsExp()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showSlayerOnlyExp",
		name = "Show Slayer Only XP Remaining",
		description = "Show the amount of slayer experience required (without gaining HP XP) to reach 99 Slayer at the "
					+ "same time as 99 Hitpoints. The goal is to be in the negative (green). To achieve this; cannon "
					+ "tasks, prioritise boss tasks, and utilize thralls on tasks."
	)
	default boolean showSlayerOnlyExp()
	{
		return true;
	}
}
