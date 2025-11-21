package net.runelite.client.plugins.pluginhub.com.example;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("CCClogSpamHider")
public interface CCClogSpamHiderConfig extends Config
{
	@ConfigSection (
			name = "Minigames",
			description = "Hide collection log broadcasts from minigames",
			position = 0
	)
	String minigamesSection = "minigames";

	@ConfigSection(
			name = "Clue Scrolls",
			description = "Hide collection log broadcasts from clue scrolls",
			position = 1
	)
	String cluesSection = "clues";

	@ConfigSection(
			name = "Other",
			description = "Hide other collection log broadcasts",
			position = 2
	)
	String otherSection = "other";

	@ConfigItem(
			keyName = "fossilNotes",
			name = "Hide Fossil Island Notes",
			description = "Hide Fossil island notes Collection log CC broadcasts",
			section = otherSection
	)
	default boolean fossilNotes(){
		return true;
	}

	@ConfigItem(
			keyName = "chompyHats",
			name = "Hide Chompy hats",
			description = "Hide Chompy bird hats Collection log CC broadcasts",
			section = otherSection
	)
	default boolean chompyHats(){
		return true;
	}

	@ConfigItem(
			keyName = "castleWars",
			name = "Castle Wars",
			description = "Hide Castle Wars Collection log CC broadcasts ",
			section = minigamesSection
	)
	default boolean castleWars() { return false; }

	@ConfigItem(
			keyName = "troubleBrewing",
			name = "Trouble Brewing",
			description = "Hide Trouble Brewing Collection log CC broadcasts ",
			section = minigamesSection
	)
	default boolean troubleBrewing() { return false; }

	@ConfigItem(
			keyName = "agilityArena",
			name = "Agility Arena/Graceful",
			description = "Hide Agility Arena and Graceful Collection log CC broadcasts ",
			section = minigamesSection
	)
	default boolean agilityArena() { return false; }

	@ConfigItem(
			keyName = "sharedClueLog",
			name = "Shared Clue Rewards",
			description = "Hide Shared Clue Rewards Collection log cc broadcasts",
			section = cluesSection,
			position = 0
	)
	default boolean sharedClueLog() {return true;}

	@ConfigItem(
			keyName = "beginnerClueLog",
			name = "Beginner Clue Rewards",
			description = "Hide Beginner Clue Rewards Collection log cc broadcasts",
			section = cluesSection,
			position = 1
	)
	default boolean beginnerClueLog() {return false;}

	@ConfigItem(
			keyName = "easyClueLog",
			name = "Easy Clue Rewards",
			description = "Hide Easy Clue Rewards Collection log cc broadcasts",
			section = cluesSection,
			position = 2
	)
	default boolean easyClueLog() {return false;}

	@ConfigItem(
			keyName = "mediumClueLog",
			name = "Medium Clue Rewards",
			description = "Hide Medium Clue Rewards Collection log cc broadcasts",
			section = cluesSection,
			position = 3
	)
	default boolean mediumClueLog() {return false;}

	@ConfigItem(
			keyName = "HardClueLog",
			name = "Hard Clue Rewards",
			description = "Hide Hard Clue Rewards Collection log cc broadcasts",
			section = cluesSection,
			position = 4
	)
	default boolean hardClueLog() {return false;}

	@ConfigItem(
			keyName = "eliteClueLog",
			name = "Elite Clue Rewards",
			description = "Hide Elite Clue Rewards Collection log cc broadcasts",
			section = cluesSection,
			position = 5
	)
	default boolean eliteClueLog() {return false;}

	@ConfigItem(
			keyName = "masterClueLog",
			name = "Master Clue Rewards",
			description = "Hide Master Clue Rewards Collection log cc broadcasts",
			section = cluesSection,
			position = 6
	)
	default boolean masterClueLog() {return false;}

	@ConfigItem(
			keyName = "myNotes",
			name = "My Notes",
			description = "Hide My Notes Collection log cc broadcasts",
			section = otherSection
	)
	default boolean myNotesLog() {return true;}

	@ConfigItem(
			keyName = "forestry",
			name = "Forestry",
			description = "Hide Forestry Collection log cc broadcasts",
			section = otherSection
	)
	default boolean forestryLog() {return true;}

}

