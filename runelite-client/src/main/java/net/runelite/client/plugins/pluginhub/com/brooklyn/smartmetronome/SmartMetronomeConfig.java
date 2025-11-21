package net.runelite.client.plugins.pluginhub.com.brooklyn.smartmetronome;

import net.runelite.api.SoundEffectVolume;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;

@ConfigGroup("smartMetronome")
public interface SmartMetronomeConfig extends Config
{
	int VOLUME_MAX = SoundEffectVolume.HIGH;
	String ENABLES = "Enables Smart Metronome ";

	@ConfigSection(
		name = "Settings",
		description = "Smart Metronome Settings",
		position = 0
	)
	String settingsSection = "Settings";

	@ConfigSection(
		name = "Bosses",
		description = "Boss Regions in which to activate Smart Metronome",
		position = 1,
		closedByDefault = true
	)
	String bossSection = "Boss Regions";

	@ConfigSection(
		name = "Minigames",
		description = "Minigames in which to activate Smart Metronome",
		position = 2,
		closedByDefault = true
	)
	String minigamesSection = "Minigames";

	@ConfigSection(
		name = "Raids",
		description = "Raids in which to activate Smart Metronome",
		position = 3,
		closedByDefault = true
	)
	String raidsSection = "Raids";

	@ConfigSection(
		name = "Slayer",
		description = "Slayer locations in which to activate Smart Metronome",
		position = 4,
		closedByDefault = true
	)
	String slayerSection = "Slayer";

	@ConfigSection(
		name = "Other",
		description = "Other times in which to activate Smart Metronome",
		position = 5,
		closedByDefault = true
	)
	String otherSection = "Other";

	@ConfigItem(
		keyName = "tickCount",
		name = "Tick count",
		description = "Configures the tick on which a sound will be played.",
		section = settingsSection
	)
	default int tickCount()
	{
		return 1;
	}

	@Range(
		max = VOLUME_MAX
	)
	@ConfigItem(
		keyName = "tickVolume",
		name = "Tick volume",
		description = "Configures the volume of the tick sound. A value of 0 will disable tick sounds.",
		section = settingsSection
	)
	default int tickVolume()
	{
		return SoundEffectVolume.MEDIUM_HIGH;
	}

	@Range(
		max = VOLUME_MAX
	)
	@ConfigItem(
		keyName = "tockVolume",
		name = "Tock volume",
		description = "Configures the volume of the tock sound. A value of 0 will disable tock sounds.",
		section = settingsSection
	)
	default int tockVolume()
	{
		return SoundEffectVolume.MUTED;
	}

	@ConfigItem(
		keyName = "overrideMetronome",
		name = "Override",
		description = "Overrides Smart Metronome and enables the metronome everywhere",
		position = -2,
		section = settingsSection
	)
	default boolean overrideMetronome()
	{
		return false;
	}

	@ConfigItem(
		keyName = "abyssalSireMetronome",
		name = "Abyssal Sire",
		description = ENABLES + "at Abyssal Sire",
		section = bossSection
	)
	default boolean abyssalSireMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "alchemicalHydraMetronome",
		name = "Alchemical Hydra",
		description = ENABLES + "at Alchemical Hydra",
		section = bossSection
	)
	default boolean alchemicalHydraMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "barrowsMetronome",
		name = "Barrows",
		description = ENABLES + "at Barrows",
		section = bossSection
	)
	default boolean barrowsMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "cerberusMetronome",
		name = "Cerberus",
		description = ENABLES + "at Cerberus",
		section = bossSection
	)
	default boolean cerberusMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "commanderZilyanaMetronome",
		name = "Commander Zilyana",
		description = ENABLES + "at Commander Zilyana",
		section = bossSection
	)
	default boolean commanderZilyanaMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "corpBeastMetronome",
		name = "Corporeal Beast",
		description = ENABLES + "at Corp",
		section = bossSection
	)
	default boolean corpBeastMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "dksMetronome",
		name = "Dagannoth Kings",
		description = ENABLES + "at DKS",
		section = bossSection
	)
	default boolean dksMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "generalGraardorMetronome",
		name = "General Graardor",
		description = ENABLES + "at General Graardor",
		section = bossSection
	)
	default boolean generalGraardorMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "giantMoleMetronome",
		name = "Giant Mole",
		description = ENABLES + "in the Falador Mole Lair",
		section = bossSection
	)
	default boolean giantMoleMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "grotesqueGuardiansMetronome",
		name = "Grotesque Guardians",
		description = ENABLES + "at Grotesque Guardians",
		section = bossSection
	)
	default boolean grotesqueGuardiansMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "krilMetronome",
		name = "K'ril Tsutsaroth",
		description = ENABLES + "at K'ril Tsutsaroth",
		section = bossSection
	)
	default boolean krilMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "kqMetronome",
		name = "Kalphite Queen",
		description = ENABLES + "at Kalphite Queen",
		section = bossSection
	)
	default boolean kqMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "kreeMetronome",
		name = "Kree'arra",
		description = ENABLES + "at Kree'arra",
		section = bossSection
	)
	default boolean kreeMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "nightmareMetronome",
		name = "Nightmare",
		description = ENABLES + "at the Nightmare",
		section = bossSection
	)
	default boolean nightmareMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "sarachnisMetronome",
		name = "Sarachnis",
		description = ENABLES + "at Sarachnis",
		section = bossSection
	)
	default boolean sarachnisMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "skotizoMetronome",
		name = "Skotizo",
		description = ENABLES + "at Skotizo",
		section = bossSection
	)
	default boolean skotizoMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "thermyMetronome",
		name = "Thermonuclear Smoke Devil",
		description = ENABLES + "at the Thermonuclear Smoke Devil",
		section = bossSection
	)
	default boolean thermyMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "vorkathMetronome",
		name = "Vorkath",
		description = ENABLES + "at Vorkath",
		section = bossSection
	)
	default boolean vorkathMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "wintertodtMetronome",
		name = "Wintertodt",
		description = ENABLES + "at Wintertodt",
		section = bossSection
	)
	default boolean wintertodtMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "zalcanoMetronome",
		name = "Zalcano",
		description = ENABLES + "at Zalcano",
		section = bossSection
	)
	default boolean zalcanoMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "zulrahMetronome",
		name = "Zulrah",
		description = ENABLES + "at Zulrah",
		section = bossSection
	)
	default boolean zulrahMetronome()
	{
		return true;
	}

	// Minigames

	@ConfigItem(
		keyName = "barbAssaultMetronome",
		name = "Barbarian Assault",
		description = ENABLES + "in Barbarian Assault",
		section = minigamesSection
	)
	default boolean barbAssaultMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "blastFurnaceMetronome",
		name = "Blast Furnace",
		description = ENABLES + "at Blast Furnace",
		section = minigamesSection
	)
	default boolean blastFurnaceMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "brimhavenAgilityMetronome",
		name = "Brimhaven Agility",
		description = ENABLES + "at the Brimhaven Agility Course",
		section = minigamesSection
	)
	default boolean brimhavenAgilityMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "fightCaveMetronome",
		name = "Fight Cave",
		description = ENABLES + "in the Tzhaar Fight Cave",
		section = minigamesSection
	)
	default boolean fightCaveMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "gauntletMetronome",
		name = "Gauntlet",
		description = ENABLES + "in the Gauntlet",
		section = minigamesSection
	)
	default boolean gauntletMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "hallowedSepulchreMetronome",
		name = "Hallowed Sepulchre",
		description = ENABLES + "in the Hallowed Sepulchre",
		section = minigamesSection
	)
	default boolean hallowedSepulchreMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "infernoMetronome",
		name = "Inferno",
		description = ENABLES + "in the Inferno",
		section = minigamesSection
	)
	default boolean infernoMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "pestControlMetronome",
		name = "Pest Control",
		description = ENABLES + "at Pest Control",
		section = minigamesSection
	)
	default boolean pestControlMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "pyramidPlunderMetronome",
		name = "Pyramid Plunder",
		description = ENABLES + "in Pyramid Plunder",
		section = minigamesSection
	)
	default boolean pyramidPlunderMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "titheFarmMetronome",
		name = "Tithe Farm",
		description = ENABLES + "at Tithe Farm",
		section = minigamesSection
	)
	default boolean titheFarmMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "volcanicMineMetronome",
		name = "Volcanic Mine",
		description = ENABLES + "in Volcanic Mine",
		section = minigamesSection
	)
	default boolean volcanicMineMetronome()
	{
		return true;
	}

	// Raids

	@ConfigItem(
		keyName = "chambersMetronome",
		name = "Chamber of Xeric",
		description = ENABLES + "in Chambers of Xeric",
		section = raidsSection
	)
	default boolean chambersMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "theatreMetronome",
		name = "Theatre of Blood",
		description = ENABLES + "in the Theatre of Blood",
		section = raidsSection
	)
	default boolean theatreMetronome()
	{
		return true;
	}

	// Slayer

	@ConfigItem(
		keyName = "catacombsMetronome",
		name = "Catacombs of Kourend",
		description = ENABLES + "in the Catacombs of Kourend",
		section = slayerSection
	)
	default boolean catacombsMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "fremennikSlayerMetronome",
		name = "Fremennik Slayer Dungeon",
		description = ENABLES + "in the Fremennik Slayer Dungeon",
		section = slayerSection
	)
	default boolean fremennikSlayerMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "slayerTowerMetronome",
		name = "Slayer Tower",
		description = ENABLES + "in the Slayer Tower",
		section = slayerSection
	)
	default boolean slayerTowerMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "strongholdCaveMetronome",
		name = "Stronghold Cave",
		description = ENABLES + "in the Stronghold Slayer Cave",
		section = slayerSection
	)
	default boolean strongholdCaveMetronome()
	{
		return true;
	}

	// Other

	@ConfigItem(
		keyName = "tickManipMetronome",
		name = "Tick Manipulation Items",
		description = ENABLES + "while tick manipulation items are in your inventory",
		section = otherSection
	)
	default boolean tickManipMetronome()
	{
		return true;
	}

	@ConfigItem(
		keyName = "pvpMetronome",
		name = "PvP",
		description = ENABLES + "in all PvP situations",
		section = otherSection
	)
	default boolean pvpMetronome()
	{
		return true;
	}
}

