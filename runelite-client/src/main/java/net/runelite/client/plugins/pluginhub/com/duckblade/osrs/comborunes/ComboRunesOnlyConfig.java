package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.comborunes;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(ComboRunesOnlyConfig.CONFIG_GROUP)
public interface ComboRunesOnlyConfig extends Config
{

	String CONFIG_GROUP = "comborunesonly";
	String UPDATE_VERSION_KEY_NAME = "updateVersion";

	@ConfigItem(
		keyName = "removeAir",
		name = "Remove for Air Altar",
		description = "Remove left-click craft-rune from the Air Altar.",
		position = 1
	)
	default boolean removeAir()
	{
		return true;
	}

	@ConfigItem(
		keyName = "removeWater",
		name = "Remove for Water Altar",
		description = "Remove left-click craft-rune from the Water Altar.",
		position = 2
	)
	default boolean removeWater()
	{
		return true;
	}

	@ConfigItem(
		keyName = "removeEarth",
		name = "Remove for Earth Altar",
		description = "Remove left-click craft-rune from the Earth Altar.",
		position = 3
	)
	default boolean removeEarth()
	{
		return true;
	}

	@ConfigItem(
		keyName = "removeFire",
		name = "Remove for Fire Altar",
		description = "Remove left-click craft-rune from the Fire Altar.",
		position = 4
	)
	default boolean removeFire()
	{
		return true;
	}

	@ConfigItem(
		keyName = "removeMode",
		name = "Mode",
		description = "Whether to deprioritize or completely remove craft-rune at altars.",
		position = 5
	)
	default RemoveMode removeMode()
	{
		return RemoveMode.REMOVE_COMPLETELY;
	}

	@ConfigItem(
		keyName = UPDATE_VERSION_KEY_NAME,
		name = "Update Version",
		description = "",
		hidden = true
	)
	default int updateVersion()
	{
		return 0;
	}

}