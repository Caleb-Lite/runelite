package net.runelite.client.plugins.pluginhub.com.molopl.plugins.lifesaving;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(LifeSavingConfig.GROUP)
public interface LifeSavingConfig extends Config
{
	String GROUP = "lifeSavingJewellery";

	@ConfigItem(
		keyName = "ringOfLifeInfobox",
		name = "Ring of life infobox",
		description = "Show infobox when Ring of life is worn"
	)
	default boolean ringOfLifeInfobox()
	{
		return true;
	}

	@ConfigItem(
		keyName = "ringOfLifeNotification",
		name = "Ring of life notification",
		description = "Notify when Ring of life is destroyed"
	)
	default boolean ringOfLifeNotification()
	{
		return true;
	}

	@ConfigItem(
		keyName = "phoenixNecklaceInfobox",
		name = "Phoenix necklace infobox",
		description = "Show infobox when Phoenix necklace is worn"
	)
	default boolean phoenixNecklaceInfobox()
	{
		return true;
	}

	@ConfigItem(
		keyName = "phoenixNecklaceNotification",
		name = "Phoenix necklace notification",
		description = "Notify when Phoenix necklace is destroyed"
	)
	default boolean phoenixNecklaceNotification()
	{
		return true;
	}
}

