package net.runelite.client.plugins.pluginhub.com.coopermor.lairentry;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Notification;

@ConfigGroup("lairentryalarm")
public interface LairEntryAlarmConfig extends Config
{
	@ConfigItem(
		keyName = "lairEntryNotification",
		name = "Lair Entry Notification",
		description = "A notification when a player enters a lair",
		position = 1
	)
	default Notification lairEntryNotification()
	{
		return Notification.ON;
	}

	@ConfigItem(
		keyName = "blacklistEnabled",
		name = "Enable Blacklist",
		description = "Whether players should blacklisted from notifications",
		position = 2
	)
	default boolean blacklistEnabled() { return false; }

	@ConfigItem(
		keyName = "blacklist",
		name = "Blacklist",
		description = "Comma separated list of usernames to ignore",
		position = 3
	)
	default String blacklist() { return ""; }
}
