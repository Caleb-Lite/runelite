package net.runelite.client.plugins.pluginhub.com.toggleitemstats.banking;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("bankitemstatstoggle")
public interface BankItemStatsToggleConfig extends Config
{
	@ConfigItem(
			keyName = "consumableStats",
			name = "Toggle consumable stats",
			description = "Toggles tooltips for consumable items (food, boosts)"
	)
	default boolean consumableStats()
	{
		return true;
	}

	@ConfigItem(
			keyName = "equipmentStats",
			name = "Toggle equipment stats",
			description = "Toggles tooltips for equipment items (combat bonuses, weight, prayer bonuses)"
	)
	default boolean equipmentStats()
	{
		return true;
	}
}
