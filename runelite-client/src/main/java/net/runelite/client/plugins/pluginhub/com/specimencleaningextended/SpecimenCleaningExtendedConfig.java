package net.runelite.client.plugins.pluginhub.com.specimencleaningextended;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("Specimen Cleaning Extended")
public interface SpecimenCleaningExtendedConfig extends Config
{
	@ConfigSection(
			name = "Predicted Rates",
			description = "Configure settings relating to predicted rates",
			position = 1
	)
	String predicted_rates_settings = "predicted_rates_settings";

	@ConfigSection(
			name = "Hide Lamp Skills",
			description = "Configure settings to hide/show skills in the lamp menu",
			position = 2
	)
	String lamp_settings = "lamp_settings";

	@ConfigItem(
			keyName = "displayInfoPanel",
			name = "Display Info Panel",
			description = "(Turn off/on to reset) | Displays the predicted rates you should get based on artefacts found",
			position = 1,
			section = predicted_rates_settings
	)
	default boolean displayInfoPanel()
	{
		return true;
	}

	@ConfigItem(
			keyName = "pause",
			name = "Pause",
			description = "Pauses calculation of the predicted XP rate",
			position = 2,
			section = predicted_rates_settings
	)
	default boolean pause()
	{
		return false;
	}

	@ConfigItem(
			keyName = "reset",
			name = "Reset",
			description = "Turn On/Off to reset rates",
			position = 3,
			section = predicted_rates_settings
	)
	default boolean reset()
	{
		return false;
	}

	@ConfigItem(
			keyName = "findSpecimensPerHour",
			name = "Specimens Per Hour",
			description = "Display predicted specimens per hour",
			position = 4,
			section = predicted_rates_settings
	)
	default boolean findSpecimensPerHour()
	{
		return true;
	}

	@ConfigItem(
			keyName = "findArtefactsPerHour",
			name = "Artefacts Per Hour",
			description = "Display predicted artefacts per hour",
			position = 5,
			section = predicted_rates_settings
	)
	default boolean findArtefactsPerHour()
	{
		return true;
	}

	@ConfigItem(
			keyName = "predictXpPerHour",
			name = "XP Per Hour",
			description = "Display predicted XP per hour",
			position = 6,
			section = predicted_rates_settings
	)
	default boolean predictXpPerHour()
	{
		return true;
	}

	@ConfigItem(
		keyName = "hideLampSkills",
		name = "Hide Lamp Skills",
		description = "Hide select skills in the lamp menu",
		position = 1,
		section = lamp_settings
	)
	default boolean hideLampSkills()
	{
		return true;
	}

	@ConfigItem(
		keyName = "agility",
		name = "Agility",
		description = "Enable/disable visibility in the lamp menu",
		position = 2,
		section = lamp_settings
	)
	default boolean agility() { return true; }

	@ConfigItem(
		keyName = "attack",
		name = "Attack",
		description = "Enable/disable visibility in the lamp menu",
		position = 3,
		section = lamp_settings
	)
	default boolean attack() { return true; }

	@ConfigItem(
		keyName = "construction",
		name = "Construction",
		description = "Enable/disable visibility in the lamp menu",
		position = 4,
		section = lamp_settings
	)
	default boolean construction() { return true; }

	@ConfigItem(
		keyName = "cooking",
		name = "Cooking",
		description = "Enable/disable visibility in the lamp menu",
		position = 5,
		section = lamp_settings
	)
	default boolean cooking() { return true; }

	@ConfigItem(
		keyName = "crafting",
		name = "Crafting",
		description = "Enable/disable visibility in the lamp menu",
		position = 6,
		section = lamp_settings
	)
	default boolean crafting() { return true; }

	@ConfigItem(
		keyName = "defence",
		name = "Defence",
		description = "Enable/disable visibility in the lamp menu",
		position = 7,
		section = lamp_settings
	)
	default boolean defence() { return true; }

	@ConfigItem(
		keyName = "farming",
		name = "Farming",
		description = "Enable/disable visibility in the lamp menu",
		position = 8,
		section = lamp_settings
	)
	default boolean farming() { return true; }

	@ConfigItem(
		keyName = "firemaking",
		name = "Firemaking",
		description = "Enable/disable visibility in the lamp menu",
		position = 9,
		section = lamp_settings
	)
	default boolean firemaking() { return true; }

	@ConfigItem(
		keyName = "fishing",
		name = "Fishing",
		description = "Enable/disable visibility in the lamp menu",
		position = 10,
		section = lamp_settings
	)
	default boolean fishing() { return true; }

	@ConfigItem(
		keyName = "fletching",
		name = "Fletching",
		description = "Enable/disable visibility in the lamp menu",
		position = 11,
		section = lamp_settings
	)
	default boolean fletching() { return true; }

	@ConfigItem(
		keyName = "herblore",
		name = "Herblore",
		description = "Enable/disable visibility in the lamp menu",
		position = 12,
		section = lamp_settings
	)
	default boolean herblore() { return true; }

	@ConfigItem(
		keyName = "hitpoints",
		name = "Hitpoints",
		description = "Enable/disable visibility in the lamp menu",
		position = 13,
		section = lamp_settings
	)
	default boolean hitpoints() { return true; }

	@ConfigItem(
		keyName = "hunter",
		name = "Hunter",
		description = "Enable/disable visibility in the lamp menu",
		position = 14,
		section = lamp_settings
	)
	default boolean hunter() { return true; }

	@ConfigItem(
		keyName = "magic",
		name = "Magic",
		description = "Enable/disable visibility in the lamp menu",
		position = 15,
		section = lamp_settings
	)
	default boolean magic() { return true; }

	@ConfigItem(
		keyName = "mining",
		name = "Mining",
		description = "Enable/disable visibility in the lamp menu",
		position = 16,
		section = lamp_settings
	)
	default boolean mining() { return true; }

	@ConfigItem(
		keyName = "prayer",
		name = "Prayer",
		description = "Enable/disable visibility in the lamp menu",
		position = 17,
		section = lamp_settings
	)
	default boolean prayer() { return true; }

	@ConfigItem(
		keyName = "ranged",
		name = "Ranged",
		description = "Enable/disable visibility in the lamp menu",
		position = 18,
		section = lamp_settings
	)
	default boolean ranged() { return true; }

	@ConfigItem(
		keyName = "runecraft",
		name = "Runecraft",
		description = "Enable/disable visibility in the lamp menu",
		position = 19,
		section = lamp_settings
	)
	default boolean runecraft() { return true; }

	@ConfigItem(
		keyName = "slayer",
		name = "Slayer",
		description = "Enable/disable visibility in the lamp menu",
		position = 20,
		section = lamp_settings
	)
	default boolean slayer() { return false; }

	@ConfigItem(
		keyName = "smithing",
		name = "Smithing",
		description = "Enable/disable visibility in the lamp menu",
		position = 21,
		section = lamp_settings
	)
	default boolean smithing() { return true; }

	@ConfigItem(
		keyName = "strength",
		name = "Strength",
		description = "Enable/disable visibility in the lamp menu",
		position = 22,
		section = lamp_settings
	)
	default boolean strength() { return true; }

	@ConfigItem(
		keyName = "thieving",
		name = "Thieving",
		description = "Enable/disable visibility in the lamp menu",
		position = 23,
		section = lamp_settings
	)
	default boolean thieving() { return true; }

	@ConfigItem(
		keyName = "woodcutting",
		name = "Woodcutting",
		description = "Enable/disable visibility in the lamp menu",
		position = 24,
		section = lamp_settings
	)
	default boolean woodcutting() { return true; }

}
