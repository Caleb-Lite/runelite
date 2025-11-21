package net.runelite.client.plugins.pluginhub.com.github.dappermickie.wildernessmaplocations;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("WildernessMapLocations")
public interface WildernessMapLocationsConfig extends Config
{
	@ConfigItem(
		keyName = "closestLocationOverlayEnabled",
		name = "Enable Closest Location Overlay",
		description = "When this is enabled the closest location overlay shows up in wilderness."
	)
	default boolean closestLocationOverlayEnabled()
	{
		return true;
	}

	@ConfigItem(
		keyName = "wildernessMapLocationsOverlay",
		name = "Enable the wilderness map location overlay",
		description = "When this is enabled the wilderness map location overlay shows up when you open the world map."
	)
	default boolean wildernessMapLocationsOverlay()
	{
		return true;
	}

	@ConfigItem(
		keyName = "wildernessMapLocationsBackgroundAlpha",
		name = "Background Alpha",
		description = "Set the alpha (transparency) of the background color for wilderness map locations (0 is transparent, 255 is filled)."
	)
	@Range(min = 0, max = 255)
	default int wildernessMapLocationsBackgroundAlpha()
	{
		return 80;
	}
}
