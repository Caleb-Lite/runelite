package net.runelite.client.plugins.pluginhub.com.projectilerage.runelite.partyplay;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Units;

@ConfigGroup(PartyPlayConfig.GROUP)
public interface PartyPlayConfig extends Config
{
	String GROUP = "partyplay";

	@ConfigItem(
			keyName = "includeSelf",
			name = "Include yourself",
			description = "Shows yourself in the panel as part of the party",
			position = 1
	)
	default boolean includeSelf()
	{
		return false;
	}

	@ConfigItem(
			keyName = "recolorNames",
			name = "Recolor names",
			description = "Recolor party members names based on unique color hash",
			position = 2
	)
	default boolean recolorNames()
	{
		return true;
	}

	@ConfigItem(
		keyName = "actionTimeout",
		name = "Activity timeout",
		description = "Configures after how long of not updating activity will be reset (in minutes)",
		position = 3
	)
	@Units(Units.MINUTES)
	default int actionTimeout()
	{
		return 5;
	}

	@ConfigItem(
			keyName = "showSlayerActivity",
			name="Slayer",
			description = "Show/share slayer activity information e.g. Assigned monster + count",
			position = 4
	)
	default boolean showSlayerActivity() { return true; }

	@ConfigItem(
		keyName = "showMainMenu",
		name = "Main Menu",
		description = "Share status when in main menu",
		position = 5
	)
	default boolean showMainMenu()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showSkillActivity",
		name = "Skilling",
		description = "Show/share activity while training skills",
		position = 6
	)
	default boolean showSkillingActivity()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showBossActivity",
		name = "Bosses",
		description = "Show/share activity and location while at bosses",
		position = 7
	)
	default boolean showBossActivity()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showCityActivity",
		name = "Cities",
		description = "Show/share activity and location while in cities",
		position = 8
	)
	default boolean showCityActivity()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showDungeonActivity",
		name = "Dungeons",
		description = "Show/share activity and location while in dungeons",
		position = 9
	)
	default boolean showDungeonActivity()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showMinigameActivity",
		name = "Minigames",
		description = "Show/share activity and location while in minigames",
		position = 10
	)
	default boolean showMinigameActivity()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showRaidingActivity",
		name = "Raids",
		description = "Show/share activity and location while in Raids",
		position = 11
	)
	default boolean showRaidingActivity()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showRegionsActivity",
		name = "Regions",
		description = "Show/share activity and location while in other regions",
		position = 12
	)
	default boolean showRegionsActivity()
	{
		return true;
	}
}
