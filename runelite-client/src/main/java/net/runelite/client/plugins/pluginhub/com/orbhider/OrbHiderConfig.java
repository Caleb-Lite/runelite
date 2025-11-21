package net.runelite.client.plugins.pluginhub.com.orbhider;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("orbhider")
public interface OrbHiderConfig extends Config
{
	@ConfigItem(
		keyName = "hideXpOrb",
		name = "Hide XP orb",
		description = "Configures whether the XP orb is hidden",
		position = 0
	)
	default boolean hideXpOrb()
	{
		return true;
	}

	@ConfigItem(
		keyName = "hideHealthOrb",
		name = "Hide Hitpoints orb",
		description = "Configures whether the Hitpoints orb is hidden",
		position = 1
	)
	default boolean hideHealthOrb()
	{
		return false;
	}

	@ConfigItem(
		keyName = "hidePrayerOrb",
		name = "Hide Prayer orb",
		description = "Configures whether the Prayer orb is hidden",
		position = 2
	)
	default boolean hidePrayerOrb()
	{
		return false;
	}

	@ConfigItem(
		keyName = "hideRunOrb",
		name = "Hide Run orb",
		description = "Configures whether the Run orb is hidden",
		position = 3
	)
	default boolean hideRunOrb()
	{
		return false;
	}

	@ConfigItem(
		keyName = "hideSpecOrb",
		name = "Hide Special Attack orb",
		description = "Configures whether the Special Attack orb is hidden",
		position = 4
	)
	default boolean hideSpecOrb()
	{
		return true;
	}

	@ConfigItem(
		keyName = "hideWorldMapOrb",
		name = "Hide World Map orb",
		description = "Configures whether the World Map orb is hidden",
		position = 5
	)
	default boolean hideWorldMapOrb()
	{
		return true;
	}
}

