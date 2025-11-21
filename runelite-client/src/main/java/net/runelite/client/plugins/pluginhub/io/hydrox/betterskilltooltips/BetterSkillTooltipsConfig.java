package net.runelite.client.plugins.pluginhub.io.hydrox.betterskilltooltips;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(BetterSkillTooltipsConfig.GROUP)
public interface BetterSkillTooltipsConfig extends Config
{
	String GROUP = "betterskilltooltips";

	@ConfigItem(
		keyName = "virtualLevels",
		name = "Show virtual level info",
		description = "Show the skill's virtual level in the tooltip"
	)
	default boolean virtualLevels()
	{
		return true;
	}

	@ConfigItem(
		keyName = "goalInfo",
		name = "Show goal information",
		description = "Shows the goal end and amount of xp required"
	)
	default boolean goalInfo()
	{
		return true;
	}

	@ConfigItem(
		keyName = "goalBar",
		name = "Show goal progress bar",
		description = "Shows a progress bar towards the goal, similar to RS3's"
	)
	default boolean goalBar()
	{
		return true;
	}
}

