package net.runelite.client.plugins.pluginhub.com.totalcost;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("total-cost")
public interface TotalCostConfig extends Config
{
	@ConfigItem(
		position = 1,
		keyName = "buyTen",
		name = "Buy 10 cost",
		description = "Enable message stating the cost of 10 of the item"
	)
	default boolean buyTen()
	{
		return false;
	}

	@ConfigItem(
		position = 2,
		keyName = "buyFifty",
		name = "Buy 50 cost",
		description = "Enable message stating the cost of 50 of the item"
	)
	default boolean buyFifty()
	{
		return false;
	}

	@ConfigItem(
		position = 3,
		keyName = "buyAll",
		name = "Buy all cost",
		description = "Enable message stating the cost of the full stack"
	)
	default boolean buyAll()
	{
		return true;
	}

	@ConfigItem(
		position = 4,
		keyName = "showAvg",
		name = "Show average cost",
		description = "Enable message stating the average cost"
	)
	default boolean showAvg()
	{
		return true;
	}

	@ConfigItem(
		position = 5,
		keyName = "highlightColor",
		name = "Highlight Color",
		description = "Set the highlight color for messages."
	)
	default Color highlightColor()
	{
		return Color.decode("#FF0000");
	}
}
