package net.runelite.client.plugins.pluginhub.melky.worldflags;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("worldflags")
public interface WorldFlagsConfig extends Config
{
	@ConfigItem(
		keyName = "showClanFlags",
		name = "Show chat channel flags",
		description = "Replaces the string W with a flag of the region the world is in inside chat channels",
		position = 1
	)
	default boolean showClanFlags()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showFriendsFlags",
		name = "Show friends flags",
		description = "Replaces the string W with a flag of the region the world is in inside friends list",
		position = 2
	)
	default boolean showFriendsFlags()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showClanChannelFlags",
		name = "Show clan channel flags",
		description = "Replaces the string W with a flag of the region the world is in inside clan channels",
		position = 3
	)
	default boolean showClanChannelFlags()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showGuestChannelFlags",
		name = "Show guest channel flags",
		description = "Replaces the string W with a flag of the region the world is in inside guest clan channels",
		position = 4
	)
	default boolean showGuestChannelFlags()
	{
		return true;
	}
}

