package net.runelite.client.plugins.pluginhub.com.PartialSets;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import java.awt.*;

@ConfigGroup("partial_sets")
public interface PartialSetsConfig extends Config
{
	@ConfigSection(
			name = "Appearance",
			description = "How to show things",
			position = 0
	)
	String appearance = "appearance";
	@ConfigSection(
			name = "Misc",
			description = "miscellaneous sets",
			position = 1,
			closedByDefault = true
	)
	String misc = "misc";
	@ConfigSection(
			name = "Skilling",
			description = "Skilling sets",
			position = 2,
			closedByDefault = true
	)
	String skilling = "skilling";
	@ConfigSection(
			name = "Moons",
			description = "Sets from Moons of peril",
			position = 3,
			closedByDefault = true
	)
	String moons = "moons";
	@ConfigSection(
			name = "Barrows",
			description = "Sets from Barrows",
			position = 4,
			closedByDefault = true
	)
	String barrows = "barrows";

	@ConfigItem(
			keyName = "associated_items",
			name = "Associated items",
			description = "Include associated items in sets"
	)
	default boolean Associated() { return false; }

	@ConfigItem(
			keyName = "appearance_text_color",
			name = "Text Color",
			description = "What color to use for text",
			section = appearance
	)
	default Color TextColor() { return Color.RED; }
	@ConfigItem(
			keyName = "appearance_text_color_equipped",
			name = "Equipped text Color",
			description = "What color to use for equipped items",
			section = appearance
	)
	default Color EquippedTextColor() { return Color.GREEN; }
	@ConfigItem(
			keyName = "appearance_text_color_unequipped",
			name = "Unequipped text Color",
			description = "What color to use for unequipped items",
			section = appearance
	)
	default Color UnequippedTextColor() { return Color.RED; }
	@ConfigItem(
			keyName = "appearance_text_color_set",
			name = "Set text Color",
			description = "What color to use for equipment set names",
			section = appearance
	)
	default Color SetTextColor() { return Color.ORANGE; }
	@ConfigItem(
			keyName = "cycle_interval",
			name = "Option speed",
			description = "How often to go to the next option in tooltips (ms)",
			section = appearance
	)
	default Integer CycleInterval() { return 2500; }

	// Misc
	@ConfigItem(
			keyName = "set_void_knight",
			name = "Void Knight",
			description = "Track 'Void Knight'",
			section = misc
	)
	default boolean VoidKnight() { return true; }
	@ConfigItem(
			keyName = "set_obsidian",
			name = "Obsidian",
			description = "Track 'Obsidian'",
			section = misc
	)
	default boolean Obsidian() { return true; }
	@ConfigItem(
			keyName = "set_virtus",
			name = "Virtus",
			description = "Track 'Virtus'",
			section = misc
	)
	default boolean Virtus() { return true; }
	@ConfigItem(
			keyName = "set_swampbark",
			name = "Swampbark",
			description = "Track 'Swampbark'",
			section = misc
	)
	default boolean Swampbark() { return true; }
	@ConfigItem(
			keyName = "set_bloodbark",
			name = "Bloodbark",
			description = "Track 'Bloodbark'",
			section = misc
	)
	default boolean Bloodbark() { return true; }
	@ConfigItem(
			keyName = "set_inquisitor",
			name = "Inquisitor",
			description = "Track 'Inquisitor'",
			section = misc
	)
	default boolean Inquisitor() { return true; }
	@ConfigItem(
			keyName = "set_crystal",
			name = "Crystal",
			description = "Track 'Crystal'",
			section = misc
	)
	default boolean Crystal() { return true; }
	@ConfigItem(
			keyName = "set_shayzien",
			name = "Shayzien",
			description = "Track 'Shayzien'",
			section = misc
	)
	default boolean Shayzien() { return true; }
	@ConfigItem(
			keyName = "set_justicar",
			name = "Justicar",
			description = "Track 'Justicar'",
			section = misc
	)
	default boolean Justicar() { return true; }
	@ConfigItem(
			keyName = "set_graceful",
			name = "Graceful",
			description = "Track 'Graceful'",
			section = misc
	)
	default boolean Graceful() { return true; }
	@ConfigItem(
			keyName = "set_gauntlet",
			name = "Gauntlet",
			description = "Track 'Gauntlet'",
			section = misc
	)
	default boolean Gauntlet() { return true; }

	// Skilling
	@ConfigItem(
			keyName = "set_farming",
			name = "Farming",
			description = "Track 'Farming'",
			section = skilling
	)
	default boolean Farming() { return true; }
	@ConfigItem(
			keyName = "set_rogue",
			name = "Rogue",
			description = "Track 'Rogue'",
			section = skilling
	)
	default boolean Rogue() { return true; }
	@ConfigItem(
			keyName = "set_hunter",
			name = "Hunter",
			description = "Track 'Hunter'",
			section = skilling
	)
	default boolean Hunter() { return true; }
	@ConfigItem(
			keyName = "set_lumberjack",
			name = "Lumberjack",
			description = "Track 'Lumberjack'",
			section = skilling
	)
	default boolean Lumberjack() { return true; }
	@ConfigItem(
			keyName = "set_carpenter",
			name = "Carpenter",
			description = "Track 'Carpenter'",
			section = skilling
	)
	default boolean Carpenter() { return true; }
	@ConfigItem(
			keyName = "set_runecrafting",
			name = "Raiments of the eye",
			description = "Track 'Raiments of the eye'",
			section = skilling
	)
	default boolean Runecrafting() { return true; }
	@ConfigItem(
			keyName = "set_fishing",
			name = "Anglers outfit",
			description = "Track 'Anglers outfit'",
			section = skilling
	)
	default boolean Fishing() { return true; }
	@ConfigItem(
			keyName = "set_zealot",
			name = "Zealots robes",
			description = "Track 'Zealot robes'",
			section = skilling
	)
	default boolean Zealot() { return true; }
	@ConfigItem(
			keyName = "set_pyromancer",
			name = "Pyromancers outfit",
			description = "Track 'Pyromancers outfit'",
			section = skilling
	)
	default boolean Pyromancer() { return true; }

	// Moons
	@ConfigItem(
			keyName = "set_bloodmoon",
			name = "BloodMoon",
			description = "Track 'BloodMoon'",
			section = moons
	)
	default boolean BloodMoon() { return true; }
	@ConfigItem(
			keyName = "set_bluemoon",
			name = "BlueMoon",
			description = "Track 'BlueMoon'",
			section = moons
	)
	default boolean BlueMoon() { return true; }
	@ConfigItem(
			keyName = "set_eclipsemoon",
			name = "EclipseMoon",
			description = "Track 'EclipseMoon'",
			section = moons
	)
	default boolean EclipseMoon() { return true; }

	// Barrows
	@ConfigItem(
			keyName = "set_ahrims",
			name = "Ahrims",
			description = "Track 'Ahrims'",
			section = barrows
	)
	default boolean Ahrims() { return true; }
	@ConfigItem(
			keyName = "set_dharoks",
			name = "Dharoks",
			description = "Track 'Dharoks'",
			section = barrows
	)
	default boolean Dharoks() { return true; }
	@ConfigItem(
			keyName = "set_guthans",
			name = "Guthans",
			description = "Track 'Guthans'",
			section = barrows
	)
	default boolean Guthans() { return true; }
	@ConfigItem(
			keyName = "set_karils",
			name = "Karils",
			description = "Track 'Karils'",
			section = barrows
	)
	default boolean Karils() { return true; }
	@ConfigItem(
			keyName = "set_torags",
			name = "Torags",
			description = "Track 'Torags'",
			section = barrows
	)
	default boolean Torags() { return true; }
	@ConfigItem(
			keyName = "set_veracs",
			name = "Veracs",
			description = "Track 'Veracs'",
			section = barrows
	)
	default boolean Veracs() { return true; }
}
