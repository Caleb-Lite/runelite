package net.runelite.client.plugins.pluginhub.thestonedturtle.mahoganyhomes;

import java.awt.Color;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;

@ConfigGroup(MahoganyHomesConfig.GROUP_NAME)
public interface MahoganyHomesConfig extends Config
{
	String GROUP_NAME = "MahoganyHomes";
	String HOME_KEY = "currentHome";
	String TIER_KEY = "currentTier";
	String WORLD_MAP_KEY = "worldMapIcon";
	String HINT_ARROW_KEY = "displayHintArrows";
	String SESSION_TIMEOUT_KEY = "sessionTimeout";
	String SHORTEST_PATH_KEY = "shortestPath";

	@ConfigItem(
		keyName = WORLD_MAP_KEY,
		name = "Display World Map Icon",
		description = "Configures whether an icon will be displayed on the world map showing where to go for your current contract",
		position = 0
	)
	default boolean worldMapIcon()
	{
		return true;
	}

	@ConfigItem(
		keyName = HINT_ARROW_KEY,
		name = "Display Hint Arrows",
		description = "Configures whether or not to display the hint arrows",
		position = 1
	)
	default boolean displayHintArrows()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showRequiredMaterials",
		name = "Display Required Materials",
		description = "Configures whether or not to display the required materials for your current task",
		position = 2
	)
	default boolean showRequiredMaterials()
	{
		return true;
	}

	@ConfigItem(
			keyName = "shortestPath",
			name = "Use 'Shortest Path' plugin",
			description = "Draws the shortest path to the assigned house on the map.<br/>" +
					"The plugin 'Shortest Path' needs to be enabled for this to work",
			position = 3
	)
	default boolean useShortestPath()
	{
		return false;
	}

	@ConfigSection(
		name = "Highlight Options",
		description = "Settings related to the highlighting of objects and items",
		position = 100,
		closedByDefault = true
	)
	String highlightSection = "highlightSection";

	@ConfigItem(
		keyName = "highlightHotspots",
		name = "Highlight Building Hotspots",
		description = "Configures whether or not the building hotspots will be highlighted",
		section = highlightSection,
		position = 0
	)
	default boolean highlightHotspots()
	{
		return true;
	}

	@Alpha
	@ConfigItem(
		keyName = "highlightHotspotColor",
		name = "Building Hotspot Highlight Color",
		description = "Configures the color the hotspots will be highlighted",
		section = highlightSection,
		position = 1
	)
	default Color highlightHotspotColor()
	{
		return new Color(0, 255, 0, 50);
	}

	@ConfigItem(
		keyName = "highlightStairs",
		name = "Highlight Stairs",
		description = "Configures whether or not the stairs will be highlighted",
		section = highlightSection,
		position = 2
	)
	default boolean highlightStairs()
	{
		return true;
	}

	@Alpha
	@ConfigItem(
		keyName = "highlightStairsColor",
		name = "Stairs Highlight Color",
		description = "Configures the color the stairs will be highlighted",
		section = highlightSection,
		position = 3
	)
	default Color highlightStairsColor()
	{
		return new Color(0, 255, 0, 20);
	}

	@ConfigItem(
		keyName = "highlightTeleports",
		name = "Highlight Teleport Items",
		description = "Configures whether or not the teleport items will be highlighted",
		section = highlightSection,
		position = 4
	)
	default boolean highlightTeleports()
	{
		return true;
	}

	@Alpha
	@ConfigItem(
		keyName = "highlightTeleportsColor",
		name = "Teleport Item Highlight Color",
		description = "Configures the color your teleports will be highlighted",
		section = highlightSection,
		position = 5
	)
	default Color highlightTeleportsColor()
	{
		return new Color(0, 255, 255, 50);
	}

	@ConfigSection(
		name = "Overlay Options",
		description = "Settings related to the overlay boxes",
		position = 200,
		closedByDefault = true
	)
	String overlaySection = "overlaySection";

	@ConfigItem(
		keyName = "textOverlay",
		name = "Display Text Overlay",
		description = "Configures whether or not the text overlay will be displayed for your current contract",
		section = overlaySection,
		position = 0
	)
	default boolean textOverlay()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showSessionStats",
		name = "Display Session Stats",
		description = "Configures whether or not the amount of contracts and the points received from those contracts is displayed inside the overlay<br/>" +
			"'Display Text Overlay' must be enabled for this to work",
		section = overlaySection,
		position = 1
	)
	default boolean showSessionStats()
	{
		return true;
	}

	@Range(
		min = 1,
		max = 60
	)
	@ConfigItem(
		keyName = SESSION_TIMEOUT_KEY,
		name = "Session Timeout",
		description = "Configures how many minutes must pass before the session timeouts after not having any activity.<br/>" +
			"Value must be between 1 and 60 minute(s)",
		section = overlaySection,
		position = 2
	)
	default int sessionTimeout()
	{
		return 5;
	}

	@ConfigItem(
		keyName = "checkSupplies",
		name = "Check Supplies",
		description = "Checks if you have enough supplies in your inventory to complete your current contract.<br/>" +
			"If the Plank Sack plugin is installed, it will include all planks within the plank sack regardless of plank type",
		position = 10
	)
	default boolean checkSupplies()
	{
		return true;
	}
}
