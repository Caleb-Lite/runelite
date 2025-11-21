package net.runelite.client.plugins.pluginhub.com.sextantlocator;

import java.awt.Color;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;

@ConfigGroup("sextantlocator")
public interface SextantLocatorConfig extends Config
{
	@ConfigSection(
		position = 0,
		name = "Coordinates Overlay",
		description = "Settings for the coordinates overlay."
	)
	String coordinatesOverlaySection = "coordinatesOverlaySection";

	@ConfigItem(
		position = 1,
		keyName = "displayCoordinatesOverlay",
		name = "Coordinates overlay",
		description = "Display the coordinates overlay.",
		section = coordinatesOverlaySection
	)
	default boolean displayCoordinatesOverlay()
	{
		return true;
	}



	@ConfigSection(
		position = 2,
		name = "Tile Tooltip",
		description = "Settings for the coordinates tile tooltip."
	)
	String tileCoordinatesOverlaySection = "tileCoordinatesOverlaySection";

	@ConfigItem(
		position = 3,
		keyName = "displayTileCoordinatesOverlay",
		name = "Tile tooltip",
		description = "Display the coordinates tooltip of the tile hovered tile.",
		section = tileCoordinatesOverlaySection
	)
	default boolean displayTileCoordinatesOverlay()
	{
		return true;
	}

	@Alpha
	@ConfigItem(
		position = 4,
		keyName = "selectedTileFillColor",
		name = "Tile color",
		description = "Color of the tile fill.",
		section = tileCoordinatesOverlaySection
	)
	default Color selectedTileFillColor()
	{
		return new Color(0, 0, 0, 64);
	}

	@Alpha
	@ConfigItem(
		position = 5,
		keyName = "selectedTileBorderColor",
		name = "Border color",
		description = "Color of the tile border.",
		section = tileCoordinatesOverlaySection
	)
	default Color selectedTileBorderColor()
	{
		return Color.YELLOW;
	}

	@Range(
		min = 1
	)
	@ConfigItem(
		position = 6,
		keyName = "selectedTileBorderWidth",
		name = "Border width",
		description = "Width of the tile border.",
		section = tileCoordinatesOverlaySection
	)
	default int selectedTileBorderWidth()
	{
		return 2;
	}



	@ConfigSection(
		position = 7,
		name = "Map Overlay",
		description = "Settings for the map coordinates overlay."
	)
	String mapCoordinatesOverlaySection = "mapCoordinatesOverlaySection";

	@ConfigItem(
		position = 8,
		keyName = "displayMapCoordinatesOverlay",
		name = "Map coordinates",
		description = "Display the sextant coordinates overlay in the map window.",
		section = mapCoordinatesOverlaySection
	)
	default boolean displayMapCoordinatesOverlay()
	{
		return true;
	}

	@Alpha
	@ConfigItem(
		position = 9,
		keyName = "coordinateMapOverlayBackgroundColor",
		name = "Background color",
		description = "Color of the background of the map overlay.",
		section = mapCoordinatesOverlaySection
	)
	default Color coordinateMapOverlayBackgroundColor()
	{
		return Color.BLACK;
	}

	@Alpha
	@ConfigItem(
		position = 10,
		keyName = "coordinateMapOverlayTextColor",
		name = "Text color",
		description = "Color of the text of the map overlay.",
		section = mapCoordinatesOverlaySection
	)
	default Color coordinateMapOverlayTextColor()
	{
		return Color.WHITE;
	}

	@Alpha
	@ConfigItem(
		position = 11,
		keyName = "coordinateMapOverlayCrosshairColor",
		name = "Crosshair color",
		description = "Color of the crosshair of the map overlay.",
		section = mapCoordinatesOverlaySection
	)
	default Color coordinateMapOverlayCrosshairColor()
	{
		return Color.WHITE;
	}
}

