package net.runelite.client.plugins.pluginhub.com.worldlocation;

import java.awt.Color;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;

@ConfigGroup("worldlocation")
public interface WorldLocationConfig extends Config
{
	@ConfigItem(
		position = 0,
		keyName = "tileLocation",
		name = "Tile Location",
		description = "Show world tile (1 x 1) location"
	)
	default boolean tileLocation()
	{
		return true;
	}

	@ConfigItem(
		position = 1,
		keyName = "tileLines",
		name = "Tile Lines",
		description = "Show tile (1 x 1) lines"
	)
	default boolean tileLines()
	{
		return false;
	}

	@ConfigItem(
		position = 2,
		keyName = "chunkLines",
		name = "Chunk Lines",
		description = "Show chunk (8 x 8) lines"
	)
	default boolean chunkLines()
	{
		return false;
	}

	@ConfigItem(
		position = 3,
		keyName = "regionLines",
		name = "Region Lines",
		description = "Show region (64 x 64) lines"
	)
	default boolean regionLines()
	{
		return false;
	}

	@ConfigItem(
		position = 4,
		keyName = "minimapTileLines",
		name = "Minimap Tile Lines",
		description = "Show tile (1 x 1) lines on the minimap"
	)
	default boolean minimapTileLines()
	{
		return false;
	}

	@ConfigItem(
		position = 5,
		keyName = "minimapChunkLines",
		name = "Minimap Chunk Lines",
		description = "Show chunk (8 x 8) lines on the minimap"
	)
	default boolean minimapChunkLines()
	{
		return false;
	}

	@ConfigItem(
		position = 6,
		keyName = "minimapRegionLines",
		name = "Minimap Region Lines",
		description = "Show region (64 x 64) lines on the minimap"
	)
	default boolean minimapRegionLines()
	{
		return false;
	}

	@ConfigItem(
		position = 7,
		keyName = "mapTileLines",
		name = "World Map Tile Lines",
		description = "Show tile (1 x 1) lines on the world map"
	)
	default boolean mapTileLines()
	{
		return false;
	}

	@ConfigItem(
		position = 8,
		keyName = "mapChunkLines",
		name = "World Map Chunk Lines",
		description = "Show chunk (8 x 8) lines on the world map"
	)
	default boolean mapChunkLines()
	{
		return false;
	}

	@ConfigItem(
		position = 9,
		keyName = "mapRegionLines",
		name = "World Map Region Lines",
		description = "Show region (64 x 64) lines on the world map"
	)
	default boolean mapRegionLines()
	{
		return false;
	}

	@ConfigItem(
		position = 10,
		keyName = "gridInfo",
		name = "Grid Info",
		description = "Show information about the current grid"
	)
	default boolean gridInfo()
	{
		return false;
	}

	@ConfigSection(
		position = 11,
		name = "Settings",
		description = "Colour and line width options",
		closedByDefault = true
	)
	String settingsSection = "settingsSection";

	@Alpha
	@ConfigItem(
		position = 12,
		keyName = "tileColour",
		name = "Tile Colour",
		description = "The colour of the tile for the world point location",
		section = settingsSection
	)
	default Color tileColour()
	{
		return new Color(0, 0, 0, 127);
	}

	@Alpha
	@ConfigItem(
		position = 13,
		keyName = "tileLineColour",
		name = "Tile Line Colour",
		description = "The colour of the tile border",
		section = settingsSection
	)
	default Color tileLineColour()
	{
		return new Color(0, 0, 0, 127);
	}

	@Alpha
	@ConfigItem(
		position = 14,
		keyName = "chunkLineColour",
		name = "Chunk Line Colour",
		description = "The colour of the chunk border",
		section = settingsSection
	)
	default Color chunkLineColour()
	{
		return Color.BLUE;
	}

	@Alpha
	@ConfigItem(
		position = 15,
		keyName = "regionLineColour",
		name = "Region Line Colour",
		description = "The colour of the region border",
		section = settingsSection
	)
	default Color regionLineColour()
	{
		return Color.GREEN;
	}

	@Range(
		max = 5
	)
	@ConfigItem(
		position = 16,
		keyName = "tileLineWidth",
		name = "Tile Line Width",
		description = "The tile border line width",
		section = settingsSection
	)
	default int tileLineWidth()
	{
		return 1;
	}

	@Range(
		max = 5
	)
	@ConfigItem(
		position = 17,
		keyName = "chunkLineWidth",
		name = "Chunk Line Width",
		description = "The chunk border line width",
		section = settingsSection
	)
	default int chunkLineWidth()
	{
		return 2;
	}

	@Range(
		max = 5
	)
	@ConfigItem(
		position = 18,
		keyName = "regionLineWidth",
		name = "Region Line Width",
		description = "The region border line width",
		section = settingsSection
	)
	default int regionLineWidth()
	{
		return 4;
	}

	@ConfigItem(
		position = 19,
		keyName = "gridInfoType",
		name = "Grid Info Type",
		description = "The info formatting for the current tile, chunk or region grid." +
			"<br>Tile: Tile X, Tile Y, Tile Z" +
			"<br>Chunk ID: unique bit-packed chunk X & Y" +
			"<br>Chunk: Chunk X, Chunk Y, Chunk Tile X, Chunk Tile Y" +
			"<br>Region ID: unique bit-packed region X & Y" +
			"<br>Region: Region X, Region Y, Region Tile X, Region Tile Y",
		section = settingsSection
	)
	default InfoType gridInfoType()
	{
		return InfoType.UNIQUE_ID;
	}

	@ConfigItem(
		position = 20,
		keyName = "instanceInfoType",
		name = "Instance Info Type",
		description = "The info type for the instance." +
			"<br>Template: source area" +
			"<br>Copy: personalized area",
		section = settingsSection
	)
	default InstanceInfoType instanceInfoType()
	{
		return InstanceInfoType.TEMPLATE;
	}
}
