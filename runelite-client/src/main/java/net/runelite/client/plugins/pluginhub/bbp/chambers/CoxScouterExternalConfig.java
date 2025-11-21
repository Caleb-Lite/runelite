package net.runelite.client.plugins.pluginhub.bbp.chambers;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

import java.awt.Color;

@ConfigGroup("coxscouterexternal")
public interface CoxScouterExternalConfig extends Config
{
	@ConfigItem(
		position = 0,
		keyName = "showTutorialOverlay",
		name = "Show tutorial overlay",
		description = "Whether to show an overlay to help understand how to use the plugin"
	)
	default boolean showTutorialOverlay()
	{
		return true;
	}

	@ConfigItem(
		position = 1,
		keyName = "scoutOverlay",
		name = "Show scout overlay",
		description = "Display an overlay that shows the current raid layout (when entering lobby)"
	)
	default boolean scoutOverlay()
	{
		return true;
	}

	@ConfigItem(
		position = 2,
		keyName = "displayFloorBreak",
		name = "Layout floor break",
		description = "Displays floor break in layout"
	)
	default boolean displayFloorBreak()
	{
		return false;
	}

	@ConfigItem(
		position = 3,
		keyName = "screenshotHotkey",
		name = "Scouter screenshot hotkey",
		description = "Hotkey used to screenshot the scouting overlay"
	)
	default Keybind screenshotHotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		position = 4,
		keyName = "showRecommendedItems",
		name = "Show recommended items",
		description = "Adds overlay with recommended items to scouter"
	)
	default boolean showRecommendedItems()
	{
		return false;
	}

	@ConfigItem(
		position = 5,
		keyName = "recommendedItems",
		name = "Recommended items",
		description = "User-set recommended items in the form: [muttadiles,ice barrage,zamorak godsword],[tekton,elder maul], ..."
	)
	default String recommendedItems()
	{
		return "";
	}

	@ConfigItem(
		position = 6,
		keyName = "highlightedRooms",
		name = "Highlighted rooms",
		description = "Display highlighted rooms in a different color on the overlay. Separate with comma (full name)"
	)
	default String highlightedRooms()
	{
		return "";
	}

	@ConfigItem(
		position = 7,
		keyName = "highlightColor",
		name = "Highlight color",
		description = "The color of highlighted rooms"
	)
	default Color highlightColor()
	{
		return Color.MAGENTA;
	}

	@ConfigItem(
		position = 8,
		keyName = "hideMissingHighlighted",
		name = "Hide missing highlighted",
		description = "Completely hides raids missing highlighted room(s)"
	)
	default boolean hideMissingHighlighted()
	{
		return false;
	}

	@ConfigItem(
		position = 9,
		keyName = "highlightedShowThreshold",
		name = "Show threshold",
		description = "The number of highlighted rooms needed to show the raid. 0 means no threshold."
	)
	default int highlightedShowThreshold()
	{
		return 0;
	}

	@ConfigItem(
		position = 10,
		keyName = "hideBlacklist",
		name = "Hide raids with blacklisted",
		description = "Completely hides raids containing blacklisted room(s)"
	)
	default boolean hideBlacklisted()
	{
		return false;
	}

	@ConfigItem(
		position = 11,
		keyName = "hideMissingLayout",
		name = "Hide missing layout",
		description = "Completely hides raids missing a whitelisted layout"
	)
	default boolean hideMissingLayout()
	{
		return false;
	}

	@ConfigItem(
		position = 12,
		keyName = "hideRopeless",
		name = "Hide ropeless raids",
		description = "Completely hides raids missing a tightrope"
	)
	default boolean hideRopeless()
	{
		return false;
	}
}
