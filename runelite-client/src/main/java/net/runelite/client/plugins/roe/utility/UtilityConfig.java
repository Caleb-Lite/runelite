package net.runelite.client.plugins.roe.utility;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("roeutility")
public interface UtilityConfig extends Config
{
	@ConfigItem(
		keyName = "trackImposters",
		name = "Track Imposter Objects",
		description = "Display information about imposter tile object changes (multilocs) including varbit changes"
	)
	default boolean trackImposters()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showObjectName",
		name = "Show Object Name",
		description = "Include the object name in imposter change messages"
	)
	default boolean showObjectName()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showLocation",
		name = "Show Location",
		description = "Include world coordinates in imposter change messages"
	)
	default boolean showLocation()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showIntermediateChanges",
		name = "Show Intermediate Changes",
		description = "Show all intermediate varbit values if multiple changes occur during animation"
	)
	default boolean showIntermediateChanges()
	{
		return true;
	}
}
