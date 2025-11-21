package net.runelite.client.plugins.pluginhub.com.funnybossnames;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ConfigGroup("FunnyBossNames")
public interface FunnyBossNamesConfig extends Config {
    @ConfigSection(
            name = "General Settings",
            description = "General boss nickname settings",
            position = 0
    )
    String generalSettings = "generalSettings";

    @ConfigSection(
            name = "God Wars Dungeon",
            description = "God Wars Dungeon boss settings",
            position = 1
    )
    String godWarsDungeon = "godWarsDungeon";

    @ConfigSection(
            name = "Wilderness Bosses",
            description = "Wilderness boss settings",
            position = 2
    )
    String wildernessBosses = "wildernessBosses";

    @ConfigSection(
            name = "Slayer Bosses",
            description = "Slayer boss settings",
            position = 3
    )
    String slayerBosses = "slayerBosses";

    @ConfigSection(
            name = "Raids",
            description = "Raid boss settings",
            position = 4
    )
    String raids = "raids";

    @ConfigSection(
            name = "DKS & Group Bosses",
            description = "Dagannoth Kings and other group boss settings",
            position = 5
    )
    String dksGroupBosses = "dksGroupBosses";

    @ConfigSection(
            name = "Desert Treasure 2",
            description = "Desert Treasure 2 boss settings",
            position = 6
    )
    String desertTreasure2 = "desertTreasure2";

    @ConfigSection(
            name = "Other Bosses",
            description = "Other boss settings",
            position = 7
    )
    String otherBosses = "otherBosses";

    // General Settings
    @ConfigItem(
            keyName = "enableBossNicknames",
            name = "Enable Boss Nicknames",
            description = "Master toggle for all boss nicknames",
            section = generalSettings,
            position = 1
    )
    default boolean enableBossNicknames() {
        return true;
    }

    // God Wars Dungeon
    @ConfigItem(
            keyName = "enableGeneralGraardor",
            name = "Enable General Graardor",
            description = "Enable nickname for General Graardor",
            section = godWarsDungeon,
            position = 10
    )
    default boolean enableGeneralGraardor() {
        return true;
    }

    @ConfigItem(
            keyName = "generalGraardor",
            name = "General Graardor Nickname",
            description = "Custom nickname for General Graardor",
            section = godWarsDungeon,
            position = 11
    )
    default String generalGraardor() {
        return "Garagedoor";
    }

    @ConfigItem(
            keyName = "enableKrilTsutsaroth",
            name = "Enable K'ril Tsutsaroth",
            description = "Enable nickname for K'ril Tsutsaroth",
            section = godWarsDungeon,
            position = 20
    )
    default boolean enableKrilTsutsaroth() {
        return true;
    }

    @ConfigItem(
            keyName = "krilTsutsaroth",
            name = "K'ril Tsutsaroth Nickname",
            description = "Custom nickname for K'ril Tsutsaroth",
            section = godWarsDungeon,
            position = 21
    )
    default String krilTsutsaroth() {
        return "Krazy K'ril";
    }

    @ConfigItem(
            keyName = "enableKreearra",
            name = "Enable Kree'arra",
            description = "Enable nickname for Kree'arra",
            section = godWarsDungeon,
            position = 30
    )
    default boolean enableKreearra() {
        return true;
    }

    @ConfigItem(
            keyName = "kreearra",
            name = "Kree'arra Nickname",
            description = "Custom nickname for Kree'arra",
            section = godWarsDungeon,
            position = 31
    )
    default String kreearra() {
        return "Squawky McBeaky";
    }

    @ConfigItem(
            keyName = "enableCommanderZilyana",
            name = "Enable Commander Zilyana",
            description = "Enable nickname for Commander Zilyana",
            section = godWarsDungeon,
            position = 40
    )
    default boolean enableCommanderZilyana() {
        return true;
    }

    @ConfigItem(
            keyName = "commanderZilyana",
            name = "Commander Zilyana Nickname",
            description = "Custom nickname for Commander Zilyana",
            section = godWarsDungeon,
            position = 41
    )
    default String commanderZilyana() {
        return "Commander Banana";
    }

    // Wilderness Bosses
    @ConfigItem(
            keyName = "enableCallisto",
            name = "Enable Callisto",
            description = "Enable nickname for Callisto",
            section = wildernessBosses,
            position = 10
    )
    default boolean enableCallisto() {
        return true;
    }

    @ConfigItem(
            keyName = "callisto",
            name = "Callisto Nickname",
            description = "Custom nickname for Callisto",
            section = wildernessBosses,
            position = 11
    )
    default String callisto() {
        return "Beary Potter";
    }

    @ConfigItem(
            keyName = "enableArtio",
            name = "Enable Artio",
            description = "Enable nickname for Artio",
            section = wildernessBosses,
            position = 20
    )
    default boolean enableArtio() {
        return true;
    }

    @ConfigItem(
            keyName = "artio",
            name = "Artio Nickname",
            description = "Custom nickname for Artio",
            section = wildernessBosses,
            position = 21
    )
    default String artio() {
        return "Bear Grylls";
    }

    @ConfigItem(
            keyName = "enableVenenatis",
            name = "Enable Venenatis",
            description = "Enable nickname for Venenatis",
            section = wildernessBosses,
            position = 30
    )
    default boolean enableVenenatis() {
        return true;
    }

    @ConfigItem(
            keyName = "venenatis",
            name = "Venenatis Nickname",
            description = "Custom nickname for Venenatis",
            section = wildernessBosses,
            position = 31
    )
    default String venenatis() {
        return "Sitanenev";
    }

    @ConfigItem(
            keyName = "enableSpindel",
            name = "Enable Spindel",
            description = "Enable nickname for Spindel",
            section = wildernessBosses,
            position = 40
    )
    default boolean enableSpindel() {
        return true;
    }

    @ConfigItem(
            keyName = "spindel",
            name = "Spindel Nickname",
            description = "Custom nickname for Spindel",
            section = wildernessBosses,
            position = 41
    )
    default String spindel() {
        return "Webbed Wacko";
    }

    @ConfigItem(
            keyName = "enableVetion",
            name = "Enable Vet'ion",
            description = "Enable nickname for Vet'ion",
            section = wildernessBosses,
            position = 50
    )
    default boolean enableVetion() {
        return true;
    }

    @ConfigItem(
            keyName = "vetion",
            name = "Vet'ion Nickname",
            description = "Custom nickname for Vet'ion",
            section = wildernessBosses,
            position = 51
    )
    default String vetion() {
        return "Neanderthal Nerd";
    }

    @ConfigItem(
            keyName = "enableCalvarion",
            name = "Enable Calvar'ion",
            description = "Enable nickname for Calvar'ion",
            section = wildernessBosses,
            position = 60
    )
    default boolean enableCalvarion() {
        return true;
    }

    @ConfigItem(
            keyName = "calvarion",
            name = "Calvar'ion Nickname",
            description = "Custom nickname for Calvar'ion",
            section = wildernessBosses,
            position = 61
    )
    default String calvarion() {
        return "Prehistoric Prankster";
    }

    @ConfigItem(
            keyName = "enableChaosElemental",
            name = "Enable Chaos Elemental",
            description = "Enable nickname for Chaos Elemental",
            section = wildernessBosses,
            position = 70
    )
    default boolean enableChaosElemental() {
        return true;
    }

    @ConfigItem(
            keyName = "chaosElemental",
            name = "Chaos Elemental Nickname",
            description = "Custom nickname for Chaos Elemental",
            section = wildernessBosses,
            position = 71
    )
    default String chaosElemental() {
        return "Whirling Wally";
    }

    @ConfigItem(
            keyName = "enableChaosFanatic",
            name = "Enable Chaos Fanatic",
            description = "Enable nickname for Chaos Fanatic",
            section = wildernessBosses,
            position = 80
    )
    default boolean enableChaosFanatic() {
        return true;
    }

    @ConfigItem(
            keyName = "chaosFanatic",
            name = "Chaos Fanatic Nickname",
            description = "Custom nickname for Chaos Fanatic",
            section = wildernessBosses,
            position = 81
    )
    default String chaosFanatic() {
        return "Fanatic Funnyman";
    }

    @ConfigItem(
            keyName = "enableCrazyArchaeologist",
            name = "Enable Crazy Archaeologist",
            description = "Enable nickname for Crazy Archaeologist",
            section = wildernessBosses,
            position = 90
    )
    default boolean enableCrazyArchaeologist() {
        return true;
    }

    @ConfigItem(
            keyName = "crazyArchaeologist",
            name = "Crazy Archaeologist Nickname",
            description = "Custom nickname for Crazy Archaeologist",
            section = wildernessBosses,
            position = 91
    )
    default String crazyArchaeologist() {
        return "Nutty Nerd";
    }

    @ConfigItem(
            keyName = "enableKingBlackDragon",
            name = "Enable King Black Dragon",
            description = "Enable nickname for King Black Dragon",
            section = wildernessBosses,
            position = 100
    )
    default boolean enableKingBlackDragon() {
        return true;
    }

    @ConfigItem(
            keyName = "kingBlackDragon",
            name = "King Black Dragon Nickname",
            description = "Custom nickname for King Black Dragon",
            section = wildernessBosses,
            position = 101
    )
    default String kingBlackDragon() {
        return "Bling Bling Dragon King";
    }

    @ConfigItem(
            keyName = "enableScorpia",
            name = "Enable Scorpia",
            description = "Enable nickname for Scorpia",
            section = wildernessBosses,
            position = 110
    )
    default boolean enableScorpia() {
        return true;
    }

    @ConfigItem(
            keyName = "scorpia",
            name = "Scorpia Nickname",
            description = "Custom nickname for Scorpia",
            section = wildernessBosses,
            position = 111
    )
    default String scorpia() {
        return "Stingy McStingSting";
    }

    // Slayer Bosses
    @ConfigItem(
            keyName = "enableCerberus",
            name = "Enable Cerberus",
            description = "Enable nickname for Cerberus",
            section = slayerBosses,
            position = 10
    )
    default boolean enableCerberus() {
        return true;
    }

    @ConfigItem(
            keyName = "cerberus",
            name = "Cerberus Nickname",
            description = "Custom nickname for Cerberus",
            section = slayerBosses,
            position = 11
    )
    default String cerberus() {
        return "Woof Woof Bark";
    }

    @ConfigItem(
            keyName = "enableKraken",
            name = "Enable Kraken",
            description = "Enable nickname for Kraken",
            section = slayerBosses,
            position = 20
    )
    default boolean enableKraken() {
        return true;
    }

    @ConfigItem(
            keyName = "kraken",
            name = "Kraken Nickname",
            description = "Custom nickname for Kraken",
            section = slayerBosses,
            position = 21
    )
    default String kraken() {
        return "Tentacle haver";
    }

    @ConfigItem(
            keyName = "enableThermonuclearSmokeDevil",
            name = "Enable Thermonuclear Smoke Devil",
            description = "Enable nickname for Thermonuclear Smoke Devil",
            section = slayerBosses,
            position = 30
    )
    default boolean enableThermonuclearSmokeDevil() {
        return true;
    }

    @ConfigItem(
            keyName = "thermonuclearSmokeDevil",
            name = "Thermonuclear Smoke Devil Nickname",
            description = "Custom nickname for Thermonuclear Smoke Devil",
            section = slayerBosses,
            position = 31
    )
    default String thermonuclearSmokeDevil() {
        return "Chernobyl";
    }

    @ConfigItem(
            keyName = "enableKalphiteQueen",
            name = "Enable Kalphite Queen",
            description = "Enable nickname for Kalphite Queen",
            section = slayerBosses,
            position = 40
    )
    default boolean enableKalphiteQueen() {
        return true;
    }

    @ConfigItem(
            keyName = "kalphiteQueen",
            name = "Kalphite Queen Nickname",
            description = "Custom nickname for Kalphite Queen",
            section = slayerBosses,
            position = 41
    )
    default String kalphiteQueen() {
        return "Hugh Jass";
    }

    @ConfigItem(
            keyName = "enableAlchemicalHydra",
            name = "Enable Alchemical Hydra",
            description = "Enable nickname for Alchemical Hydra",
            section = slayerBosses,
            position = 50
    )
    default boolean enableAlchemicalHydra() {
        return true;
    }

    @ConfigItem(
            keyName = "alchemicalHydra",
            name = "Alchemical Hydra Nickname",
            description = "Custom nickname for Alchemical Hydra",
            section = slayerBosses,
            position = 51
    )
    default String alchemicalHydra() {
        return "Hoot Hoot Hydra";
    }

    @ConfigItem(
            keyName = "enableAbyssalSire",
            name = "Enable Abyssal Sire",
            description = "Enable nickname for Abyssal Sire",
            section = slayerBosses,
            position = 60
    )
    default boolean enableAbyssalSire() {
        return true;
    }

    @ConfigItem(
            keyName = "abyssalSire",
            name = "Abyssal Sire Nickname",
            description = "Custom nickname for Abyssal Sire",
            section = slayerBosses,
            position = 61
    )
    default String abyssalSire() {
        return "Mike";
    }

    // Raids
    @ConfigItem(
            keyName = "enableChambersOfXeric",
            name = "Enable Chambers of Xeric",
            description = "Enable nickname for Chambers of Xeric",
            section = raids,
            position = 10
    )
    default boolean enableChambersOfXeric() {
        return true;
    }

    @ConfigItem(
            keyName = "chambersOfXeric",
            name = "Chambers of Xeric Nickname",
            description = "Custom nickname for Chambers of Xeric",
            section = raids,
            position = 11
    )
    default String chambersOfXeric() {
        return "Cocks";
    }

    @ConfigItem(
            keyName = "enableTheatreOfBlood",
            name = "Enable Theatre of Blood",
            description = "Enable nickname for Theatre of Blood",
            section = raids,
            position = 20
    )
    default boolean enableTheatreOfBlood() {
        return true;
    }

    @ConfigItem(
            keyName = "theatreOfBlood",
            name = "Theatre of Blood Nickname",
            description = "Custom nickname for Theatre of Blood",
            section = raids,
            position = 21
    )
    default String theatreOfBlood() {
        return "Theatre of Butt";
    }

    @ConfigItem(
            keyName = "enableTombsOfAmascut",
            name = "Enable Tombs of Amascut",
            description = "Enable nickname for Tombs of Amascut",
            section = raids,
            position = 30
    )
    default boolean enableTombsOfAmascut() {
        return true;
    }

    @ConfigItem(
            keyName = "tombsOfAmascut",
            name = "Tombs of Amascut Nickname",
            description = "Custom nickname for Tombs of Amascut",
            section = raids,
            position = 31
    )
    default String tombsOfAmascut() {
        return "Tombs of Amascoochie";
    }

    // Individual raid bosses - Theatre of Blood
    @ConfigItem(
            keyName = "enableTheMaidenOfSugadinti",
            name = "Enable The Maiden of Sugadinti",
            description = "Enable nickname for The Maiden of Sugadinti",
            section = raids,
            position = 100
    )
    default boolean enableTheMaidenOfSugadinti() {
        return true;
    }

    @ConfigItem(
            keyName = "theMaidenOfSugadinti",
            name = "The Maiden of Sugadinti Nickname",
            description = "Custom nickname for The Maiden of Sugadinti",
            section = raids,
            position = 101
    )
    default String theMaidenOfSugadinti() {
        return "The Maiden of Spaghetti";
    }

    @ConfigItem(
            keyName = "enablePestilentBloat",
            name = "Enable Pestilent Bloat",
            description = "Enable nickname for Pestilent Bloat",
            section = raids,
            position = 110
    )
    default boolean enablePestilentBloat() {
        return true;
    }

    @ConfigItem(
            keyName = "pestilentBloat",
            name = "Pestilent Bloat Nickname",
            description = "Custom nickname for Pestilent Bloat",
            section = raids,
            position = 111
    )
    default String pestilentBloat() {
        return "Big Hobo";
    }

    @ConfigItem(
            keyName = "enableSotetseg",
            name = "Enable Sotetseg",
            description = "Enable nickname for Sotetseg",
            section = raids,
            position = 120
    )
    default boolean enableSotetseg() {
        return true;
    }

    @ConfigItem(
            keyName = "sotetseg",
            name = "Sotetseg Nickname",
            description = "Custom nickname for Sotetseg",
            section = raids,
            position = 121
    )
    default String sotetseg() {
        return "Big Dog";
    }

    @ConfigItem(
            keyName = "enableXarpus",
            name = "Enable Xarpus",
            description = "Enable nickname for Xarpus",
            section = raids,
            position = 130
    )
    default boolean enableXarpus() {
        return true;
    }

    @ConfigItem(
            keyName = "xarpus",
            name = "Xarpus Nickname",
            description = "Custom nickname for Xarpus",
            section = raids,
            position = 131
    )
    default String xarpus() {
        return "Count Flapula";
    }

    @ConfigItem(
            keyName = "enableVerzikVitur",
            name = "Enable Verzik Vitur",
            description = "Enable nickname for Verzik Vitur",
            section = raids,
            position = 140
    )
    default boolean enableVerzikVitur() {
        return true;
    }

    @ConfigItem(
            keyName = "verzikVitur",
            name = "Verzik Vitur Nickname",
            description = "Custom nickname for Verzik Vitur",
            section = raids,
            position = 141
    )
    default String verzikVitur() {
        return "Bitey McSuckface";
    }

    // Individual raid bosses - Chambers of Xeric
    @ConfigItem(
            keyName = "enableIceDemon",
            name = "Enable Ice Demon",
            description = "Enable nickname for Ice Demon",
            section = raids,
            position = 200
    )
    default boolean enableIceDemon() {
        return true;
    }

    @ConfigItem(
            keyName = "iceDemon",
            name = "Ice Demon Nickname",
            description = "Custom nickname for Ice Demon",
            section = raids,
            position = 201
    )
    default String iceDemon() {
        return "Beer cooler";
    }

    @ConfigItem(
            keyName = "enableLizardmanShamans",
            name = "Enable Lizardman Shaman",
            description = "Enable nickname for Lizardman Shaman",
            section = raids,
            position = 210
    )
    default boolean enableLizardmanShamans() {
        return true;
    }

    @ConfigItem(
            keyName = "lizardmanShamans",
            name = "Lizardman Shaman Nickname",
            description = "Custom nickname for Lizardman Shaman",
            section = raids,
            position = 211
    )
    default String lizardmanShamans() {
        return "Queen Elisabeth II.";
    }

    @ConfigItem(
            keyName = "enableMuttadiles",
            name = "Enable Muttadile",
            description = "Enable nickname for Muttadile",
            section = raids,
            position = 220
    )
    default boolean enableMuttadiles() {
        return true;
    }

    @ConfigItem(
            keyName = "muttadiles",
            name = "Muttadile Nickname",
            description = "Custom nickname for Muttadile",
            section = raids,
            position = 221
    )
    default String muttadiles() {
        return "Croco-Not-So-Doodle";
    }

    @ConfigItem(
            keyName = "enableSkeletalMystic",
            name = "Enable Skeletal Mystic",
            description = "Enable nickname for Skeletal Mystic",
            section = raids,
            position = 230
    )
    default boolean enableSkeletalMystic() {
        return true;
    }

    @ConfigItem(
            keyName = "skeletalMystic",
            name = "Skeletal Mystic Nickname",
            description = "Custom nickname for Skeletal Mystic",
            section = raids,
            position = 231
    )
    default String skeletalMystic() {
        return "Crypt Cackler";
    }

    @ConfigItem(
            keyName = "enableTekton",
            name = "Enable Tekton",
            description = "Enable nickname for Tekton",
            section = raids,
            position = 240
    )
    default boolean enableTekton() {
        return true;
    }

    @ConfigItem(
            keyName = "tekton",
            name = "Tekton Nickname",
            description = "Custom nickname for Tekton",
            section = raids,
            position = 241
    )
    default String tekton() {
        return "Crackton";
    }

    @ConfigItem(
            keyName = "enableVanguard",
            name = "Enable Vanguard",
            description = "Enable nickname for Vanguard",
            section = raids,
            position = 250
    )
    default boolean enableVanguard() {
        return true;
    }

    @ConfigItem(
            keyName = "vanguard",
            name = "Vanguard Nickname",
            description = "Custom nickname for Vanguard",
            section = raids,
            position = 251
    )
    default String vanguard() {
        return "Shrimp";
    }

    @ConfigItem(
            keyName = "enableVasaNistirio",
            name = "Enable Vasa Nistirio",
            description = "Enable nickname for Vasa Nistirio",
            section = raids,
            position = 260
    )
    default boolean enableVasaNistirio() {
        return true;
    }

    @ConfigItem(
            keyName = "vasaNistirio",
            name = "Vasa Nistirio Nickname",
            description = "Custom nickname for Vasa Nistirio",
            section = raids,
            position = 261
    )
    default String vasaNistirio() {
        return "Ali C Lopez";
    }

    @ConfigItem(
            keyName = "enableVespula",
            name = "Enable Vespula",
            description = "Enable nickname for Vespula",
            section = raids,
            position = 270
    )
    default boolean enableVespula() {
        return true;
    }

    @ConfigItem(
            keyName = "vespula",
            name = "Vespula Nickname",
            description = "Custom nickname for Vespula",
            section = raids,
            position = 271
    )
    default String vespula() {
        return "Nectar nutter";
    }

    @ConfigItem(
            keyName = "enableOlm",
            name = "Enable Great Olm",
            description = "Enable nickname for Great Olm",
            section = raids,
            position = 280
    )
    default boolean enableOlm() {
        return true;
    }

    @ConfigItem(
            keyName = "olm",
            name = "Great Olm Nickname",
            description = "Custom nickname for Great Olm",
            section = raids,
            position = 281
    )
    default String olm() {
        return "Big O'";
    }

    @ConfigItem(
            keyName = "enableOlmsLeftHand",
            name = "Enable Great Olm - Left hand",
            description = "Enable nickname for Great Olm - Left hand",
            section = raids,
            position = 290
    )
    default boolean enableOlmsLeftHand() {
        return true;
    }

    @ConfigItem(
            keyName = "olmsLeftHand",
            name = "Great Olm - Left hand Nickname",
            description = "Custom nickname for Great Olm - Left hand",
            section = raids,
            position = 291
    )
    default String olmsLeftHand() {
        return "Big O's left hand";
    }

    @ConfigItem(
            keyName = "enableOlmsRightHand",
            name = "Enable Great Olm - Right hand",
            description = "Enable nickname for Great Olm - Right hand",
            section = raids,
            position = 300
    )
    default boolean enableOlmsRightHand() {
        return true;
    }

    @ConfigItem(
            keyName = "olmsRightHand",
            name = "Great Olm - Right hand Nickname",
            description = "Custom nickname for Great Olm - Right hand",
            section = raids,
            position = 301
    )
    default String olmsRightHand() {
        return "Big O's right hand";
    }

    // Individual raid bosses - Tombs of Amascut
    @ConfigItem(
            keyName = "enableKephri",
            name = "Enable Kephri",
            description = "Enable nickname for Kephri",
            section = raids,
            position = 400
    )
    default boolean enableKephri() {
        return true;
    }

    @ConfigItem(
            keyName = "kephri",
            name = "Kephri Nickname",
            description = "Custom nickname for Kephri",
            section = raids,
            position = 401
    )
    default String kephri() {
        return "Dung enjoyer";
    }

    @ConfigItem(
            keyName = "enableAkkha",
            name = "Enable Akkha",
            description = "Enable nickname for Akkha",
            section = raids,
            position = 410
    )
    default boolean enableAkkha() {
        return true;
    }

    @ConfigItem(
            keyName = "akkha",
            name = "Akkha Nickname",
            description = "Custom nickname for Akkha",
            section = raids,
            position = 411
    )
    default String akkha() {
        return "a";
    }

    @ConfigItem(
            keyName = "enableZebak",
            name = "Enable Zebak",
            description = "Enable nickname for Zebak",
            section = raids,
            position = 420
    )
    default boolean enableZebak() {
        return true;
    }

    @ConfigItem(
            keyName = "zebak",
            name = "Zebak Nickname",
            description = "Custom nickname for Zebak",
            section = raids,
            position = 421
    )
    default String zebak() {
        return "Croc'O";
    }

    @ConfigItem(
            keyName = "enableBaba",
            name = "Enable Ba-Ba",
            description = "Enable nickname for Ba-Ba",
            section = raids,
            position = 430
    )
    default boolean enableBaba() {
        return true;
    }

    @ConfigItem(
            keyName = "baba",
            name = "Ba-Ba Nickname",
            description = "Custom nickname for Ba-Ba",
            section = raids,
            position = 431
    )
    default String baba() {
        return "Harambe";
    }

    @ConfigItem(
            keyName = "enableTumekensWarden",
            name = "Enable Tumeken's Warden",
            description = "Enable nickname for Tumeken's Warden",
            section = raids,
            position = 440
    )
    default boolean enableTumekensWarden() {
        return true;
    }

    @ConfigItem(
            keyName = "tumekensWarden",
            name = "Tumeken's Warden Nickname",
            description = "Custom nickname for Tumeken's Warden",
            section = raids,
            position = 441
    )
    default String tumekensWarden() {
        return "Tumeken's Simp";
    }

    @ConfigItem(
            keyName = "enableElidinisWarden",
            name = "Enable Elidinis' Warden",
            description = "Enable nickname for Elidinis' Warden",
            section = raids,
            position = 450
    )
    default boolean enableElidinisWarden() {
        return true;
    }

    @ConfigItem(
            keyName = "elidinisWarden",
            name = "Elidinis' Warden Nickname",
            description = "Custom nickname for Elidinis' Warden",
            section = raids,
            position = 451
    )
    default String elidinisWarden() {
        return "Eilidinis' Simp";
    }

    // DKS & Group Bosses
    @ConfigItem(
            keyName = "enableDagannothPrime",
            name = "Enable Dagannoth Prime",
            description = "Enable nickname for Dagannoth Prime",
            section = dksGroupBosses,
            position = 10
    )
    default boolean enableDagannothPrime() {
        return true;
    }

    @ConfigItem(
            keyName = "dagannothPrime",
            name = "Dagannoth Prime Nickname",
            description = "Custom nickname for Dagannoth Prime",
            section = dksGroupBosses,
            position = 11
    )
    default String dagannothPrime() {
        return "Optimus Prime";
    }

    @ConfigItem(
            keyName = "enableDagannothRex",
            name = "Enable Dagannoth Rex",
            description = "Enable nickname for Dagannoth Rex",
            section = dksGroupBosses,
            position = 20
    )
    default boolean enableDagannothRex() {
        return true;
    }

    @ConfigItem(
            keyName = "dagannothRex",
            name = "Dagannoth Rex Nickname",
            description = "Custom nickname for Dagannoth Rex",
            section = dksGroupBosses,
            position = 21
    )
    default String dagannothRex() {
        return "Sexy Rexy";
    }

    @ConfigItem(
            keyName = "enableDagannothSupreme",
            name = "Enable Dagannoth Supreme",
            description = "Enable nickname for Dagannoth Supreme",
            section = dksGroupBosses,
            position = 30
    )
    default boolean enableDagannothSupreme() {
        return true;
    }

    @ConfigItem(
            keyName = "dagannothSupreme",
            name = "Dagannoth Supreme Nickname",
            description = "Custom nickname for Dagannoth Supreme",
            section = dksGroupBosses,
            position = 31
    )
    default String dagannothSupreme() {
        return "Dagannoth Supreme Leader";
    }

    @ConfigItem(
            keyName = "enableDagganotheKings",
            name = "Enable Dagannoth Kings",
            description = "Enable nickname for Dagannoth Kings",
            section = dksGroupBosses,
            position = 40
    )
    default boolean enableDagganotheKings() {
        return true;
    }

    @ConfigItem(
            keyName = "dagganotheKings",
            name = "Dagannoth Kings Nickname",
            description = "Custom nickname for Dagannoth Kings",
            section = dksGroupBosses,
            position = 41
    )
    default String dagganotheKings() {
        return "Dagannoth Twinks";
    }

    @ConfigItem(
            keyName = "enableGrotesqueGuardians",
            name = "Enable Grotesque Guardians",
            description = "Enable nickname for Grotesque Guardians",
            section = dksGroupBosses,
            position = 50
    )
    default boolean enableGrotesqueGuardians() {
        return true;
    }

    @ConfigItem(
            keyName = "grotesqueGuardians",
            name = "Grotesque Guardians Nickname",
            description = "Custom nickname for Grotesque Guardians",
            section = dksGroupBosses,
            position = 51
    )
    default String grotesqueGuardians() {
        return "Rocko and Socko";
    }

    @ConfigItem(
            keyName = "enableDusk",
            name = "Enable Dusk",
            description = "Enable nickname for Dusk",
            section = dksGroupBosses,
            position = 60
    )
    default boolean enableDusk() {
        return true;
    }

    @ConfigItem(
            keyName = "dusk",
            name = "Dusk Nickname",
            description = "Custom nickname for Dusk",
            section = dksGroupBosses,
            position = 61
    )
    default String dusk() {
        return "Rocko";
    }

    @ConfigItem(
            keyName = "enableDawn",
            name = "Enable Dawn",
            description = "Enable nickname for Dawn",
            section = dksGroupBosses,
            position = 70
    )
    default boolean enableDawn() {
        return true;
    }

    @ConfigItem(
            keyName = "dawn",
            name = "Dawn Nickname",
            description = "Custom nickname for Dawn",
            section = dksGroupBosses,
            position = 71
    )
    default String dawn() {
        return "Socko";
    }

    // Desert Treasure 2
    @ConfigItem(
            keyName = "enableTheLeviathan",
            name = "Enable The Leviathan",
            description = "Enable nickname for The Leviathan",
            section = desertTreasure2,
            position = 10
    )
    default boolean enableTheLeviathan() {
        return true;
    }

    @ConfigItem(
            keyName = "theLeviathan",
            name = "The Leviathan Nickname",
            description = "Custom nickname for The Leviathan",
            section = desertTreasure2,
            position = 11
    )
    default String theLeviathan() {
        return "Alaskan Bull Worm";
    }

    @ConfigItem(
            keyName = "enableVardorvis",
            name = "Enable Vardorvis",
            description = "Enable nickname for Vardorvis",
            section = desertTreasure2,
            position = 20
    )
    default boolean enableVardorvis() {
        return true;
    }

    @ConfigItem(
            keyName = "vardorvis",
            name = "Vardorvis Nickname",
            description = "Custom nickname for Vardorvis",
            section = desertTreasure2,
            position = 21
    )
    default String vardorvis() {
        return "Extendo Arm-azing";
    }

    @ConfigItem(
            keyName = "enableDukeSuccellus",
            name = "Enable Duke Sucellus",
            description = "Enable nickname for Duke Sucellus",
            section = desertTreasure2,
            position = 30
    )
    default boolean enableDukeSuccellus() {
        return true;
    }

    @ConfigItem(
            keyName = "dukeSuccellus",
            name = "Duke Sucellus Nickname",
            description = "Custom nickname for Duke Sucellus",
            section = desertTreasure2,
            position = 31
    )
    default String dukeSuccellus() {
        return "Obese Obscenity";
    }

    @ConfigItem(
            keyName = "enableTheWhisperer",
            name = "Enable The Whisperer",
            description = "Enable nickname for The Whisperer",
            section = desertTreasure2,
            position = 40
    )
    default boolean enableTheWhisperer() {
        return true;
    }

    @ConfigItem(
            keyName = "theWhisperer",
            name = "The Whisperer Nickname",
            description = "Custom nickname for The Whisperer",
            section = desertTreasure2,
            position = 41
    )
    default String theWhisperer() {
        return "The Mumbler";
    }

    @ConfigItem(
            keyName = "enableWhisperer",
            name = "Enable Whisperer",
            description = "Enable nickname for Whisperer",
            section = desertTreasure2,
            position = 50
    )
    default boolean enableWhisperer() {
        return true;
    }

    @ConfigItem(
            keyName = "whisperer",
            name = "Whisperer Nickname",
            description = "Custom nickname for Whisperer",
            section = desertTreasure2,
            position = 51
    )
    default String whisperer() {
        return "Mumbler";
    }

    // Other Bosses
    @ConfigItem(
            keyName = "enableCorporealBeast",
            name = "Enable Corporeal Beast",
            description = "Enable nickname for Corporeal Beast",
            section = otherBosses,
            position = 10
    )
    default boolean enableCorporealBeast() {
        return true;
    }

    @ConfigItem(
            keyName = "corporealBeast",
            name = "Corporeal Beast Nickname",
            description = "Custom nickname for Corporeal Beast",
            section = otherBosses,
            position = 11
    )
    default String corporealBeast() {
        return "Corporate tax evader";
    }

    @ConfigItem(
            keyName = "enableGiantMole",
            name = "Enable Giant Mole",
            description = "Enable nickname for Giant Mole",
            section = otherBosses,
            position = 20
    )
    default boolean enableGiantMole() {
        return true;
    }

    @ConfigItem(
            keyName = "giantMole",
            name = "Giant Mole Nickname",
            description = "Custom nickname for Giant Mole",
            section = otherBosses,
            position = 21
    )
    default String giantMole() {
        return "The Spanker";
    }

    @ConfigItem(
            keyName = "enableDerangedArchaeologist",
            name = "Enable Deranged Archaeologist",
            description = "Enable nickname for Deranged Archaeologist",
            section = otherBosses,
            position = 30
    )
    default boolean enableDerangedArchaeologist() {
        return true;
    }

    @ConfigItem(
            keyName = "derangedArchaeologist",
            name = "Deranged Archaeologist Nickname",
            description = "Custom nickname for Deranged Archaeologist",
            section = otherBosses,
            position = 31
    )
    default String derangedArchaeologist() {
        return "Insane Investigator";
    }

    @ConfigItem(
            keyName = "enableSarachnis",
            name = "Enable Sarachnis",
            description = "Enable nickname for Sarachnis",
            section = otherBosses,
            position = 40
    )
    default boolean enableSarachnis() {
        return true;
    }

    @ConfigItem(
            keyName = "sarachnis",
            name = "Sarachnis Nickname",
            description = "Custom nickname for Sarachnis",
            section = otherBosses,
            position = 41
    )
    default String sarachnis() {
        return "Sriracha";
    }

    @ConfigItem(
            keyName = "enableZalcano",
            name = "Enable Zalcano",
            description = "Enable nickname for Zalcano",
            section = otherBosses,
            position = 50
    )
    default boolean enableZalcano() {
        return true;
    }

    @ConfigItem(
            keyName = "zalcano",
            name = "Zalcano Nickname",
            description = "Custom nickname for Zalcano",
            section = otherBosses,
            position = 51
    )
    default String zalcano() {
        return "Rock 'n' Rollcano";
    }

    @ConfigItem(
            keyName = "enablePhantomMuspah",
            name = "Enable Phantom Muspah",
            description = "Enable nickname for Phantom Muspah",
            section = otherBosses,
            position = 60
    )
    default boolean enablePhantomMuspah() {
        return true;
    }

    @ConfigItem(
            keyName = "phantomMuspah",
            name = "Phantom Muspah Nickname",
            description = "Custom nickname for Phantom Muspah",
            section = otherBosses,
            position = 61
    )
    default String phantomMuspah() {
        return "The Grumbler";
    }

    @ConfigItem(
            keyName = "enableBarrowsChests",
            name = "Enable Barrows Chests",
            description = "Enable nickname for Barrows Chests",
            section = otherBosses,
            position = 70
    )
    default boolean enableBarrowsChests() {
        return true;
    }

    @ConfigItem(
            keyName = "barrowsChests",
            name = "Barrows Chests Nickname",
            description = "Custom nickname for Barrows Chests",
            section = otherBosses,
            position = 71
    )
    default String barrowsChests() {
        return "Barrows Chests";
    }

    @ConfigItem(
            keyName = "enableBarrowsChest",
            name = "Enable Barrows Chest",
            description = "Enable nickname for Barrows Chest",
            section = otherBosses,
            position = 80
    )
    default boolean enableBarrowsChest() {
        return true;
    }

    @ConfigItem(
            keyName = "barrowsChest",
            name = "Barrows Chest Nickname",
            description = "Custom nickname for Barrows Chest",
            section = otherBosses,
            position = 81
    )
    default String barrowsChest() {
        return "Barrows Chest";
    }

    @ConfigItem(
            keyName = "enableBryophyta",
            name = "Enable Bryophyta",
            description = "Enable nickname for Bryophyta",
            section = otherBosses,
            position = 90
    )
    default boolean enableBryophyta() {
        return true;
    }

    @ConfigItem(
            keyName = "bryophyta",
            name = "Bryophyta Nickname",
            description = "Custom nickname for Bryophyta",
            section = otherBosses,
            position = 91
    )
    default String bryophyta() {
        return "Firefighter";
    }

    @ConfigItem(
            keyName = "enableTztokJad",
            name = "Enable TzTok-Jad",
            description = "Enable nickname for TzTok-Jad",
            section = otherBosses,
            position = 100
    )
    default boolean enableTztokJad() {
        return true;
    }

    @ConfigItem(
            keyName = "tztokJad",
            name = "TzTok-Jad Nickname",
            description = "Custom nickname for TzTok-Jad",
            section = otherBosses,
            position = 101
    )
    default String tztokJad() {
        return "TzTok-FatKok";
    }

    @ConfigItem(
            keyName = "enableTheGauntlet",
            name = "Enable The Gauntlet",
            description = "Enable nickname for The Gauntlet",
            section = otherBosses,
            position = 110
    )
    default boolean enableTheGauntlet() {
        return true;
    }

    @ConfigItem(
            keyName = "theGauntlet",
            name = "The Gauntlet Nickname",
            description = "Custom nickname for The Gauntlet",
            section = otherBosses,
            position = 111
    )
    default String theGauntlet() {
        return "Irons prison";
    }

    @ConfigItem(
            keyName = "enableCrystallineHunleff",
            name = "Enable Crystalline Hunllef",
            description = "Enable nickname for Crystalline Hunllef",
            section = otherBosses,
            position = 120
    )
    default boolean enableCrystallineHunllef() {
        return true;
    }

    @ConfigItem(
            keyName = "crystallineHunleff",
            name = "Crystalline Hunllef Nickname",
            description = "Custom nickname for Crystalline Hunllef",
            section = otherBosses,
            position = 121
    )
    default String crystallineHunllef() {
        return "Transparent Dog";
    }

    @ConfigItem(
            keyName = "enableCorruptedHunleff",
            name = "Enable Corrupted Hunllef",
            description = "Enable nickname for Corrupted Hunllef",
            section = otherBosses,
            position = 130
    )
    default boolean enableCorruptedHunllef() {
        return true;
    }

    @ConfigItem(
            keyName = "corruptedHunleff",
            name = "Corrupted Hunllef Nickname",
            description = "Custom nickname for Corrupted Hunllef",
            section = otherBosses,
            position = 131
    )
    default String corruptedHunllef() {
        return "Corrupted Transparent Dog";
    }

    @ConfigItem(
            keyName = "enableHespori",
            name = "Enable Hespori",
            description = "Enable nickname for Hespori",
            section = otherBosses,
            position = 140
    )
    default boolean enableHespori() {
        return true;
    }

    @ConfigItem(
            keyName = "hespori",
            name = "Hespori Nickname",
            description = "Custom nickname for Hespori",
            section = otherBosses,
            position = 141
    )
    default String hespori() {
        return "Cactus Conundrum";
    }

    @ConfigItem(
            keyName = "enableTheInferno",
            name = "Enable The Inferno",
            description = "Enable nickname for The Inferno",
            section = otherBosses,
            position = 150
    )
    default boolean enableTheInferno() {
        return true;
    }

    @ConfigItem(
            keyName = "theInferno",
            name = "The Inferno Nickname",
            description = "Custom nickname for The Inferno",
            section = otherBosses,
            position = 151
    )
    default String theInferno() {
        return "Get in here, it's not that hard...";
    }

    @ConfigItem(
            keyName = "enableNex",
            name = "Enable Nex",
            description = "Enable nickname for Nex",
            section = otherBosses,
            position = 160
    )
    default boolean enableNex() {
        return true;
    }

    @ConfigItem(
            keyName = "nex",
            name = "Nex Nickname",
            description = "Custom nickname for Nex",
            section = otherBosses,
            position = 161
    )
    default String nex() {
        return "Sex";
    }

    @ConfigItem(
            keyName = "enableTheNightmare",
            name = "Enable The Nightmare",
            description = "Enable nickname for The Nightmare",
            section = otherBosses,
            position = 170
    )
    default boolean enableTheNightmare() {
        return true;
    }

    @ConfigItem(
            keyName = "theNightmare",
            name = "The Nightmare Nickname",
            description = "Custom nickname for The Nightmare",
            section = otherBosses,
            position = 171
    )
    default String theNightmare() {
        return "Gran";
    }

    @ConfigItem(
            keyName = "enablePhosanisNightmare",
            name = "Enable Phosani's Nightmare",
            description = "Enable nickname for Phosani's Nightmare",
            section = otherBosses,
            position = 180
    )
    default boolean enablePhosanisNightmare() {
        return true;
    }

    @ConfigItem(
            keyName = "phosanisNightmare",
            name = "Phosani's Nightmare Nickname",
            description = "Custom nickname for Phosani's Nightmare",
            section = otherBosses,
            position = 181
    )
    default String phosanisNightmare() {
        return "Gran Gran";
    }

    @ConfigItem(
            keyName = "enableObor",
            name = "Enable Obor",
            description = "Enable nickname for Obor",
            section = otherBosses,
            position = 190
    )
    default boolean enableObor() {
        return true;
    }

    @ConfigItem(
            keyName = "obor",
            name = "Obor Nickname",
            description = "Custom nickname for Obor",
            section = otherBosses,
            position = 191
    )
    default String obor() {
        return "Robo";
    }

    @ConfigItem(
            keyName = "enableSkotizo",
            name = "Enable Skotizo",
            description = "Enable nickname for Skotizo",
            section = otherBosses,
            position = 200
    )
    default boolean enableSkotizo() {
        return true;
    }

    @ConfigItem(
            keyName = "skotizo",
            name = "Skotizo Nickname",
            description = "Custom nickname for Skotizo",
            section = otherBosses,
            position = 201
    )
    default String skotizo() {
        return "Skoshizo";
    }

    @ConfigItem(
            keyName = "enableTempoross",
            name = "Enable Tempoross",
            description = "Enable nickname for Tempoross",
            section = otherBosses,
            position = 210
    )
    default boolean enableTempoross() {
        return true;
    }

    @ConfigItem(
            keyName = "tempoross",
            name = "Tempoross Nickname",
            description = "Custom nickname for Tempoross",
            section = otherBosses,
            position = 211
    )
    default String tempoross() {
        return "Waterwilly";
    }

    @ConfigItem(
            keyName = "enableWintertodt",
            name = "Enable Wintertodt",
            description = "Enable nickname for Wintertodt",
            section = otherBosses,
            position = 220
    )
    default boolean enableWintertodt() {
        return true;
    }

    @ConfigItem(
            keyName = "wintertodt",
            name = "Wintertodt Nickname",
            description = "Custom nickname for Wintertodt",
            section = otherBosses,
            position = 221
    )
    default String wintertodt() {
        return "Winderdod";
    }

    @ConfigItem(
            keyName = "enableZulrah",
            name = "Enable Zulrah",
            description = "Enable nickname for Zulrah",
            section = otherBosses,
            position = 230
    )
    default boolean enableZulrah() {
        return true;
    }

    @ConfigItem(
            keyName = "zulrah",
            name = "Zulrah Nickname",
            description = "Custom nickname for Zulrah",
            section = otherBosses,
            position = 231
    )
    default String zulrah() {
        return "Danger Noodle";
    }

    @ConfigItem(
            keyName = "enableTheFightCaves",
            name = "Enable The Fight Caves",
            description = "Enable nickname for The Fight Caves",
            section = otherBosses,
            position = 240
    )
    default boolean enableTheFightCaves() {
        return true;
    }

    @ConfigItem(
            keyName = "theFightCaves",
            name = "The Fight Caves Nickname",
            description = "Custom nickname for The Fight Caves",
            section = otherBosses,
            position = 241
    )
    default String theFightCaves() {
        return "Go get your fire cape";
    }

    @ConfigItem(
            keyName = "enableVorkath",
            name = "Enable Vorkath",
            description = "Enable nickname for Vorkath",
            section = otherBosses,
            position = 250
    )
    default boolean enableVorkath() {
        return true;
    }

    @ConfigItem(
            keyName = "vorkath",
            name = "Vorkath Nickname",
            description = "Custom nickname for Vorkath",
            section = otherBosses,
            position = 251
    )
    default String vorkath() {
        return "Puff Daddy";
    }

    @ConfigItem(
            keyName = "enableGauntlet",
            name = "Enable Gauntlet",
            description = "Enable nickname for Gauntlet",
            section = otherBosses,
            position = 260
    )
    default boolean enableGauntlet() {
        return true;
    }

    @ConfigItem(
            keyName = "gauntlet",
            name = "Gauntlet Nickname",
            description = "Custom nickname for Gauntlet",
            section = otherBosses,
            position = 261
    )
    default String gauntlet() {
        return "Irons prison";
    }

    @ConfigItem(
            keyName = "enableCorruptedGauntlet",
            name = "Enable Corrupted Gauntlet",
            description = "Enable nickname for Corrupted Gauntlet",
            section = otherBosses,
            position = 270
    )
    default boolean enableCorruptedGauntlet() {
        return true;
    }

    @ConfigItem(
            keyName = "corruptedGauntlet",
            name = "Corrupted Gauntlet Nickname",
            description = "Custom nickname for Corrupted Gauntlet",
            section = otherBosses,
            position = 271
    )
    default String corruptedGauntlet() {
        return "Corrupted irons prison";
    }

    @ConfigItem(
            keyName = "enableScurrius",
            name = "Enable Scurrius",
            description = "Enable nickname for Scurrius",
            section = otherBosses,
            position = 280
    )
    default boolean enableScurrius() {
        return true;
    }

    @ConfigItem(
            keyName = "scurrius",
            name = "Scurrius Nickname",
            description = "Custom nickname for Scurrius",
            section = otherBosses,
            position = 281
    )
    default String scurrius() {
        return "Ratatouille";
    }

    @ConfigItem(
            keyName = "enableSolHeredit",
            name = "Enable Sol Heredit",
            description = "Enable nickname for Sol Heredit",
            section = otherBosses,
            position = 290
    )
    default boolean enableSolHeredit() {
        return true;
    }

    @ConfigItem(
            keyName = "solHeredit",
            name = "Sol Heredit Nickname",
            description = "Custom nickname for Sol Heredit",
            section = otherBosses,
            position = 291
    )
    default String solHeredit() {
        return "Shmol Wheredidit";
    }

    @ConfigItem(
            keyName = "enableAraxxor",
            name = "Enable Araxxor",
            description = "Enable nickname for Araxxor",
            section = otherBosses,
            position = 300
    )
    default boolean enableAraxxor() {
        return true;
    }

    @ConfigItem(
            keyName = "araxxor",
            name = "Araxxor Nickname",
            description = "Custom nickname for Araxxor",
            section = otherBosses,
            position = 301
    )
    default String araxxor() {
        return "Roxxanne";
    }

    @ConfigItem(
            keyName = "enableYama",
            name = "Enable Yama",
            description = "Enable nickname for Yama",
            section = otherBosses,
            position = 310
    )
    default boolean enableYama() {
        return true;
    }

    @ConfigItem(
            keyName = "yama",
            name = "Yama Nickname",
            description = "Custom nickname for Yama",
            section = otherBosses,
            position = 311
    )
    default String yama() {
        return "Momma";
    }

    @ConfigItem(
            keyName = "enableYama",
            name = "Enable Yama",
            description = "Enable nickname for Yama",
            section = otherBosses,
            position = 312
    )
    default boolean enableMokha() {
        return true;
    }

    @ConfigItem(
            keyName = "mokha",
            name = "Mokha Nickname",
            description = "Custom nickname for Mokha",
            section = otherBosses,
            position = 313
    )
    default String mokha() {
        return "Doom of Mock-ay-what-now?";
    }
}
