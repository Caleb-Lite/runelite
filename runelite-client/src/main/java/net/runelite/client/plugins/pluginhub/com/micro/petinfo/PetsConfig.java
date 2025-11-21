package net.runelite.client.plugins.pluginhub.com.micro.petinfo;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import java.awt.*;

@ConfigGroup("pets")
public interface PetsConfig extends Config
{
	@ConfigSection(
			name = "Pet group highlight settings",
			description = "Choose how and what color pet groups (i.e. bossing, skilling) should be highlighted.",
			position = 99
	)
	String highlightSection = "section";

	enum MenuMode
	{
		OFF,
		INFO,
		EXAMINE,
		BOTH
	}

	enum PetMode
	{
		OFF,
		HIGHLIGHT,
		NAME_ONLY
	}

	enum PetInfoColor
	{
		HIGHLIGHT,
		YELLOW
	}

	enum PetOwnerColor
	{
		WHITE,
		YELLOW,
		COMBAT
	}

	enum HighlightMode
	{
		OFF,
		ALL,
		OWN
	}

	@ConfigItem(
			position = 1,
			keyName = "menu",
			name = "Right click menu",
			description = "Show option on right click"
	)
	default MenuMode menu() { return MenuMode.BOTH; }

	@ConfigItem(
			position = 2,
			keyName = "showPetOwner",
			name = "Show pet's owner name",
			description = "Show pet's owner name on right click"
	)
	default boolean showPetOwner() { return true; }

	@ConfigItem(
			position = 3,
			keyName = "togglePetInfoColor",
			name = "Pet name color",
			description = "Choose between the RuneScape default yellow and the custom highlight colors for the pets name"
	)
	default PetInfoColor petInfoColor() { return PetInfoColor.YELLOW; }

	@ConfigItem(
			position = 4,
			keyName = "togglePetOwnerColor",
			name = "Pet's owner name color",
			description = "Choose between the RuneScape default yellow, white, or the combat level differential"
	)
	default PetOwnerColor petOwnerColor() { return PetOwnerColor.WHITE; }

	@ConfigItem(
			position = 5,
			keyName = "getRemoteData",
			name = "Get up-to-date list of pets",
			description = "Use the updated list of pets from the github. If off, and no local backup found, the plugin will not work. A local backup will be created on first download of an updated version."
	)
	default boolean getRemoteData() { return true; }

	@ConfigItem(
			position = 6,
			keyName = "skipConvexHull",
			name = "Imprecise click boxes (better FPS)",
			description = "Speed up the pets under cursor check if things are slow."
	)
	default boolean getSkipConvexHull()
	{
		return false;
	}

	@ConfigItem(
			position = 7,
			keyName = "toggleHighlight",
			name = "Highlight toggle",
			description = "Select if no, all, or only your own pets are highlighted",
			section = highlightSection
	)
	default HighlightMode highlight() { return HighlightMode.OFF; }

	@ConfigItem(
			position = 8,
			keyName = "showBoss",
			name = "Highlight Bossing Pets",
			description = "Toggles highlighting for bossing pets",
			section = highlightSection
	)
	default PetMode showBoss()
	{
		return PetMode.HIGHLIGHT;
	}

	@ConfigItem(
			position = 9,
			keyName = "bossColor",
			name = "Boss Pet color",
			description = "Highlight color for boss pets",
			section = highlightSection
	)
	default Color getBossColor()
	{
		return new Color(193, 18, 18);
	}

	@ConfigItem(
			position = 10,
			keyName = "showSkilling",
			name = "Highlight Skilling Pets",
			description = "Toggles highlighting for skilling pets",
			section = highlightSection
	)
	default PetMode showSkilling()
	{
		return PetMode.HIGHLIGHT;
	}

	@ConfigItem(
			position = 11,
			keyName = "skillingColor",
			name = "Skilling Pet color",
			description = "Highlight color for skilling pets",
			section = highlightSection
	)
	default Color getSkillingColor()
	{
		return new Color(106, 232, 38);
	}

	@ConfigItem(
			position = 12,
			keyName = "showToy",
			name = "Highlight Toys",
			description = "Toggles highlighting for clockwork toys",
			section = highlightSection
	)
	default PetMode showToy()
	{
		return PetMode.OFF;
	}

	@ConfigItem(
			position = 13,
			keyName = "toyColor",
			name = "Toy color",
			description = "Highlight color for clockwork toys",
			section = highlightSection
	)
	default Color getToyColor()
	{
		return new Color(139, 120, 69);
	}

	@ConfigItem(
			position = 14,
			keyName = "showOther",
			name = "Show Other Pets",
			description = "Toggles highlighting for other pets (like cats)",
			section = highlightSection
	)
	default PetMode showOther()
	{
		return PetMode.NAME_ONLY;
	}

	@ConfigItem(
			position = 15,
			keyName = "otherColor",
			name = "Other Pet color",
			description = "Highlight color for other pets",
			section = highlightSection
	)
	default Color getOtherColor()
	{
		return new Color(18, 47, 193);
	}

	@ConfigItem(
			position = 16,
			keyName = "showNpcId",
			name = "Show NPC ID",
			description = "Show the pets NPC id next to its overhead name",
			section = highlightSection
	)
	default boolean getShowNpcId()
	{
		return false;
	}
}
