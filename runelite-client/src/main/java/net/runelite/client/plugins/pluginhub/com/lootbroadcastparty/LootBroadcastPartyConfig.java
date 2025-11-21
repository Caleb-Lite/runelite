package net.runelite.client.plugins.pluginhub.com.lootbroadcastparty;

import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup(LootBroadcastPartyConfig.GROUP)
public interface LootBroadcastPartyConfig extends Config
{
	String GROUP = "loot-broadcast-party";

	@ConfigItem(
			keyName = "collectionLogs",
			name = "Collection Log Notifications",
			description = "Enables seeing collection log notifications",
			position = 1
	)
	default boolean collectionLogs()
	{
		return true;
	}

	@ConfigItem(
			keyName = "valuableDrops",
			name = "Valuable Drop Notifications",
			description = "Enables seeing valuable drop notifications",
			position = 2
	)
	default boolean valuableDrops()
	{
		return true;
	}

	@ConfigItem(
			keyName = "combatAchievements",
			name = "Combat Achievement Notifications",
			description = "Enables seeing combat achievement notifications",
			position = 3
	)
	default boolean combatAchievements()
	{
		return true;
	}

	@ConfigItem(
			keyName = "levelUps",
			name = "Level Up Notifications",
			description = "Enables seeing level up notifications",
			position = 3
	)
	default boolean levelUps()
	{
		return true;
	}
}

