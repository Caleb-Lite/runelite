package net.runelite.client.plugins.pluginhub.com.ninetyninenotifier;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("ninetyninenotifier")
public interface NinetynineNotifierConfig extends Config
{
	@ConfigItem(
			keyName = "enableSystemNotifier",
			name = "Enable system notifications",
			description = "Show desktop popups when close to level 99",
			position = 1
	)
	default boolean enableSystemNotifier()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableOverlay",
			name = "Show on-screen warning",
			description = "Displays a warning box when you're close to 99",
			position = 2
	)
	default boolean enableOverlay()
	{
		return true;
	}

	@ConfigItem(
			keyName = "threshold",
			name = "XP Threshold",
			description = "Warn when this amount of XP or less remains to 99",
			position = 3
	)
	default int threshold()
	{
		return 50000;
	}

	@ConfigSection(
			name = "Skills to monitor",
			description = "Toggle which skills will trigger notifications",
			position = 99
	)
	String skillsSection = "skillsSection";

	// === Individual skill toggles ===

	@ConfigItem(
			keyName = "enableAttack",
			name = "Attack",
			description = "Enable for Attack",
			section = skillsSection,
			position = 100
	)
	default boolean enableAttack()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableDefence",
			name = "Defence",
			description = "Enable for Defence",
			section = skillsSection,
			position = 101
	)
	default boolean enableDefence()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableStrength",
			name = "Strength",
			description = "Enable for Strength",
			section = skillsSection,
			position = 102
	)
	default boolean enableStrength()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableHitpoints",
			name = "Hitpoints",
			description = "Enable for Hitpoints",
			section = skillsSection,
			position = 103
	)
	default boolean enableHitpoints()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableRanged",
			name = "Ranged",
			description = "Enable for Ranged",
			section = skillsSection,
			position = 104
	)
	default boolean enableRanged()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enablePrayer",
			name = "Prayer",
			description = "Enable for Prayer",
			section = skillsSection,
			position = 105
	)
	default boolean enablePrayer()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableMagic",
			name = "Magic",
			description = "Enable for Magic",
			section = skillsSection,
			position = 106
	)
	default boolean enableMagic()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableCooking",
			name = "Cooking",
			description = "Enable for Cooking",
			section = skillsSection,
			position = 107
	)
	default boolean enableCooking()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableWoodcutting",
			name = "Woodcutting",
			description = "Enable for Woodcutting",
			section = skillsSection,
			position = 108
	)
	default boolean enableWoodcutting()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableFletching",
			name = "Fletching",
			description = "Enable for Fletching",
			section = skillsSection,
			position = 109
	)
	default boolean enableFletching()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableFishing",
			name = "Fishing",
			description = "Enable for Fishing",
			section = skillsSection,
			position = 110
	)
	default boolean enableFishing()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableFiremaking",
			name = "Firemaking",
			description = "Enable for Firemaking",
			section = skillsSection,
			position = 111
	)
	default boolean enableFiremaking()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableCrafting",
			name = "Crafting",
			description = "Enable for Crafting",
			section = skillsSection,
			position = 112
	)
	default boolean enableCrafting()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableSmithing",
			name = "Smithing",
			description = "Enable for Smithing",
			section = skillsSection,
			position = 113
	)
	default boolean enableSmithing()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableMining",
			name = "Mining",
			description = "Enable for Mining",
			section = skillsSection,
			position = 114
	)
	default boolean enableMining()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableHerblore",
			name = "Herblore",
			description = "Enable for Herblore",
			section = skillsSection,
			position = 115
	)
	default boolean enableHerblore()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableAgility",
			name = "Agility",
			description = "Enable for Agility",
			section = skillsSection,
			position = 116
	)
	default boolean enableAgility()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableThieving",
			name = "Thieving",
			description = "Enable for Thieving",
			section = skillsSection,
			position = 117
	)
	default boolean enableThieving()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableSlayer",
			name = "Slayer",
			description = "Enable for Slayer",
			section = skillsSection,
			position = 118
	)
	default boolean enableSlayer()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableFarming",
			name = "Farming",
			description = "Enable for Farming",
			section = skillsSection,
			position = 119
	)
	default boolean enableFarming()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableRunecraft",
			name = "Runecraft",
			description = "Enable for Runecraft",
			section = skillsSection,
			position = 120
	)
	default boolean enableRunecraft()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableHunter",
			name = "Hunter",
			description = "Enable for Hunter",
			section = skillsSection,
			position = 121
	)
	default boolean enableHunter()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableConstruction",
			name = "Construction",
			description = "Enable for Construction",
			section = skillsSection,
			position = 122
	)
	default boolean enableConstruction()
	{
		return true;
	}


}
