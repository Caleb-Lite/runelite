package net.runelite.client.plugins.pluginhub.dekvall.worldhider;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("worldhider")
public interface WorldHiderConfig extends Config
{
	@ConfigItem(
		keyName = "hideFlags",
		name = "Hide Flags",
		description = "Hides Flag for each world by making them all the same",
		position = 0
	)
	default boolean hideFlags()
	{
		return false;
	}

	@ConfigItem(
		keyName = "hideFavorites",
		name = "Hide Favorites",
		description = "Hides Favorite worlds",
		position = 1
	)
	default boolean hideFavorites()
	{
		return false;
	}

	@ConfigItem(
		keyName = "hideScrollbar",
		name = "Hide Scrollbar",
		description = "Hides Scrollbar",
		position = 2
	)
	default boolean hideScrollbar()
	{
		return false;
	}

	@ConfigItem(
		keyName = "hideList",
		name = "Hide List",
		description = "Hides value in world hopper list",
		position = 3
	)
	default boolean hideList()
	{
		return false;
	}

	@ConfigItem(
		keyName = "hideListConfig",
		name = "Hide World Panel",
		description = "Hides the worlds in the configuring panel",
		position = 4
	)
	default boolean hideConfigurationPanel()
	{
		return false;
	}

	@ConfigItem(
		keyName = "massHide",
		name = "Hide Friends",
		description = "Hides worlds of friends and clanmates",
		position = 5
	)
	default boolean hideFriends()
	{
		return false;
	}
}

