package net.runelite.client.plugins.pluginhub.com.speshkitty.giantsfoundryitemvalues;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("giants-foundry-item-values")
public interface ItemBarValuesConfig extends Config
{
	@ConfigItem(
		keyName = "showInGF",
		name = "Show in Giant's Foundry",
		description = "Shows the bar value of items inside Giant's Foundry"
	)
	default boolean showInGF()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showEverywhere",
			name = "Show everywhere",
			description = "Shows the bar value of items everywhere"
	)
	default boolean showEverywhere()
	{
		return false;
	}

	@ConfigItem(
			keyName = "displayPosition",
			name = "Display Position",
			description = "The position the text is drawn"
	)
	default DisplayPosition priceDisplayMode()
	{
		return DisplayPosition.TOP_RIGHT;
	}
}

