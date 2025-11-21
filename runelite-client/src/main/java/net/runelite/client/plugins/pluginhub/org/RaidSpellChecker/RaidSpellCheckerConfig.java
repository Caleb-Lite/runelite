package net.runelite.client.plugins.pluginhub.org.RaidSpellChecker;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;
import net.runelite.client.config.Units;

import java.awt.*;

@ConfigGroup("raidspellchecker")
public interface RaidSpellCheckerConfig extends Config
{
    enum Spellbook
    {
        STANDARD,
        ANCIENT,
        LUNAR,
        ARCEUUS
    }

    enum Sound
    {
        RUBY_BOLTS,
        FAIRY_RING,
        ICE_BARRAGE,
        EEEEEEEE,
        CUSTOM
    }

//region COX
    @ConfigSection(
            name = "COX",
            description = "Spells to use in COX",
            position = 1,
            closedByDefault = true
    )
    String coxSpellbookSection = "COX Spellbook";

    // === COX Spellbook Selector === //
    @ConfigItem(
            position = 1,
            keyName = "enableCOX",
            name = "Enable",
            description = "Enabled for COX",
            section = coxSpellbookSection
    )
    default boolean enableCox()
    {
        return true;
    }

    @ConfigItem(
            position = 2,
            keyName = "spellSelectionCOX",
            name = "Spellbook selection",
            description = "Spellbook to use for COX",
            section = coxSpellbookSection
    )
    default Spellbook spellSelectionCOX()
    {
        return Spellbook.STANDARD;
    }

    // === COX Standard Spells === //
    @ConfigSection(
            name = "COX Standard Spells",
            description = "Standard spells to use in COX",
            position = 2,
            closedByDefault = true
    )
    String coxStandardSpells = "COX Standard Spells";

    @ConfigItem(
            keyName = "labelCOXStandard",
            name = "Standard spell requirements for COX.", description = "",
            position = 1,
            section = coxStandardSpells)
    default String labelCOXStandard()
    {
        return "";
    }

    @ConfigItem(
            keyName = "useFireCOX",
            name = "Fire Spell",
            description = "Use a fire spell (Standard)",
            position = 2,
            section = coxStandardSpells
    )
    default boolean useFireCOX()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useWaterCOX",
            name = "Water Spell",
            description = "Use a water spell (Standard)",
            position = 3,
            section = coxStandardSpells
    )
    default boolean useWaterCOX()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useEarthCOX",
            name = "Earth Spell",
            description = "Use an earth spell (Standard)",
            position = 4,
            section = coxStandardSpells
    )
    default boolean useEarthCOX()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useAirCOX",
            name = "Air Spell",
            description = "Use an air spell (Standard)",
            position = 5,
            section = coxStandardSpells
    )
    default boolean useAirCOX()
    {
        return false;
    }

    // === COX Arceuus Spells === //
    @ConfigSection(
            name = "COX Arceuus Spells",
            description = "Arceuus spells to use in COX",
            position = 3,
            closedByDefault = true
    )
    String coxArceuusSpells = "COX Arceuus Spells";

    @ConfigItem(
            keyName = "labelCOXArceuus",
            name = "Arceuus spell requirements for COX.", description = "",
            position = 1,
            section = coxArceuusSpells)
    default String labelCOXArceuus()
    {
        return "";
    }

    @ConfigItem(
            keyName = "useGraspCOX",
            name = "Grasp Spell",
            description = "Use a grasp spell (Arceuus)",
            position = 2,
            section = coxArceuusSpells
    )
    default boolean useGraspCOX()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useDemonbaneCOX",
            name = "Demonbane Spell",
            description = "Use a demonbane spell (Arceuus)",
            position = 3,
            section = coxArceuusSpells
    )
    default boolean useDemonbaneCOX()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useThrallsCOX",
            name = "Thralls",
            description = "Use a thrall spell (Arceuus)",
            position = 4,
            section = coxArceuusSpells
    )
    default boolean useThrallsCOX()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useDeathChargeCOX",
            name = "Death Charge",
            description = "Use the death charge spell (Arceuus)",
            position = 5,
            section = coxArceuusSpells
    )
    default boolean useDeathChargeCOX()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useMarkOfDarknessCOX",
            name = "Mark of Darkness",
            description = "Use the mark of darkness spell (Arceuus)",
            position = 6,
            section = coxArceuusSpells
    )
    default boolean useMarkOfDarknessCOX()
    {
        return false;
    }

    // === COX Ancient Spells === //
    @ConfigSection(
            name = "COX Ancient Spells",
            description = "Ancient spells to use in COX",
            position = 4,
            closedByDefault = true
    )
    String coxAncientSpells = "COX Ancient Spells";

    @ConfigItem(
            keyName = "labelCOXAncient",
            name = "Ancient spell requirements for COX.", description = "",
            position = 1,
            section = coxAncientSpells)
    default String labelCOXAncient()
    {
        return "";
    }

    @ConfigItem(
            keyName = "useSmokeCOX",
            name = "Smoke Spell",
            description = "Use a smoke spell (Ancient)",
            position = 2,
            section = coxAncientSpells
    )
    default boolean useSmokeCOX()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useShadowCOX",
            name = "Shadow Spell",
            description = "Use a shadow spell (Ancient)",
            position = 3,
            section = coxAncientSpells
    )
    default boolean useShadowCOX()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useBloodCOX",
            name = "Blood Spell",
            description = "Use a blood spell (Ancient)",
            position = 4,
            section = coxAncientSpells
    )
    default boolean useBloodCOX()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useIceCOX",
            name = "Ice Spell",
            description = "Use an ice spell (Ancient)",
            position = 5,
            section = coxAncientSpells
    )
    default boolean useIceCOX()
    {
        return false;
    }

    // === COX Lunar Spells === //
    @ConfigSection(
            name = "COX Lunar Spells",
            description = "Lunar spells to use in COX",
            position = 5,
            closedByDefault = true
    )
    String coxLunarSpells = "COX Lunar Spells";

    @ConfigItem(
            keyName = "labelCOXLunar",
            name = "Lunar spell requirements for COX.", description = "",
            position = 1,
            section = coxLunarSpells)
    default String labelCOXLunar()
    {
        return "";
    }

    @ConfigItem(
            keyName = "useCureCOX",
            name = "Cure Spells",
            description = "Use a cure spell (Lunar)",
            position = 2,
            section = coxLunarSpells
    )
    default boolean useCureCOX()
    {
        return false;
    }

    @ConfigItem(
            keyName = "usePotShareCOX",
            name = "Pot Share",
            description = "Use a potshare spell (Lunar)",
            position = 3,
            section = coxLunarSpells
    )
    default boolean usePotShareCOX()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useVengeCOX",
            name = "Venge",
            description = "Use the venge spell (Lunar)",
            position = 4,
            section = coxLunarSpells
    )
    default boolean useVengeCOX()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useHumidifyCOX",
            name = "Humidify",
            description = "Use the humidify spell (Lunar)",
            position = 5,
            section = coxLunarSpells
    )
    default boolean useHumidifyCOX()
    {
        return false;
    }

//endregion
//---------------------------------------------------------------------------------------------------------------------------------
//region TOB
    @ConfigSection(
            name = "TOB",
            description = "Spells to use in TOB",
            position = 6,
            closedByDefault = true
    )
    String tobSpellbookSection = "TOB";

    @ConfigItem(
            keyName = "enableTOB",
            name = "Enable",
            description = "Enabled for TOB",
            position = 1,
            section = tobSpellbookSection
    )
    default boolean enableTOB()
    {
        return true;
    }

    @ConfigItem(
            keyName = "spellSelectionTOB",
            name = "Spellbook selection",
            description = "Spellbook to use for TOB",
            position = 2,
            section = tobSpellbookSection
    )
    default Spellbook spellSelectionTOB()
    {
        return Spellbook.STANDARD;
    }

    // === TOB Standard Spells === //
    @ConfigSection(
            name = "TOB Standard Spells",
            description = "Standard spells to use in TOB",
            position = 7,
            closedByDefault = true
    )
    String tobStandardSpells = "TOB Standard Spells";

    @ConfigItem(
            keyName = "labelTOBStandard",
            name = "Standard spell requirements for TOB.", description = "",
            position = 1,
            section = tobStandardSpells)
    default String labelTOBStandard()
    {
        return "";
    }

    @ConfigItem(
            keyName = "useFireTOB",
            name = "Fire Spell",
            description = "Use a fire spell (Standard)",
            position = 2,
            section = tobStandardSpells
    )
    default boolean useFireTOB()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useWaterTOB",
            name = "Water Spell",
            description = "Use a water spell (Standard)",
            position = 3,
            section = tobStandardSpells
    )
    default boolean useWaterTOB()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useEarthTOB",
            name = "Earth Spell",
            description = "Use an earth spell (Standard)",
            position = 4,
            section = tobStandardSpells
    )
    default boolean useEarthTOB()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useAirTOB",
            name = "Air Spell",
            description = "Use an air spell (Standard)",
            position = 5,
            section = tobStandardSpells
    )
    default boolean useAirTOB()
    {
        return false;
    }

    // === TOB Arceuus Spells === //
    @ConfigSection(
            name = "TOB Arceuus Spells",
            description = "Arceuus spells to use in TOB",
            position = 8,
            closedByDefault = true
    )
    String tobArceuusSpells = "TOB Arceuus Spells";

    @ConfigItem(
            keyName = "labelTOBArceuus",
            name = "Arceuus spell requirements for TOB.", description = "",
            position = 1,
            section = tobArceuusSpells)
    default String labelTOBArceuus()
    {
        return "";
    }

    @ConfigItem(
            keyName = "useGraspTOB",
            name = "Grasp Spell",
            description = "Use a grasp spell (Arceuus)",
            position = 2,
            section = tobArceuusSpells
    )
    default boolean useGraspTOB()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useDemonbaneTOB",
            name = "Demonbane Spell",
            description = "Use a demonbane spell (Arceuus)",
            position = 3,
            section = tobArceuusSpells
    )
    default boolean useDemonbaneTOB()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useThrallsTOB",
            name = "Thralls",
            description = "Use a thrall spell (Arceuus)",
            position = 4,
            section = tobArceuusSpells
    )
    default boolean useThrallsTOB()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useDeathChargeTOB",
            name = "Death Charge",
            description = "Use the death charge spell (Arceuus)",
            position = 5,
            section = tobArceuusSpells
    )
    default boolean useDeathChargeTOB()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useMarkOfDarknessTOB",
            name = "Mark of Darkness",
            description = "Use the mark of darkness spell (Arceuus)",
            position = 6,
            section = tobArceuusSpells
    )
    default boolean useMarkOfDarknessTOB()
    {
        return false;
    }

    // === TOB Ancient Spells === //
    @ConfigSection(
            name = "TOB Ancient Spells",
            description = "Ancient spells to use in TOB",
            position = 9,
            closedByDefault = true
    )
    String tobAncientSpells = "TOB Ancient Spells";

    @ConfigItem(
            keyName = "labelTOBAncient",
            name = "Ancient spell requirements for TOB.", description = "",
            position = 1,
            section = tobAncientSpells)
    default String labelTOBAncient()
    {
        return "";
    }

    @ConfigItem(
            keyName = "useSmokeTOB",
            name = "Smoke Spell",
            description = "Use a smoke spell (Ancient)",
            position = 2,
            section = tobAncientSpells
    )
    default boolean useSmokeTOB()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useShadowTOB",
            name = "Shadow Spell",
            description = "Use a shadow spell (Ancient)",
            position = 3,
            section = tobAncientSpells
    )
    default boolean useShadowTOB()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useBloodTOB",
            name = "Blood Spell",
            description = "Use a blood spell (Ancient)",
            position = 4,
            section = tobAncientSpells
    )
    default boolean useBloodTOB()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useIceTOB",
            name = "Ice Spell",
            description = "Use an ice spell (Ancient)",
            position = 5,
            section = tobAncientSpells
    )
    default boolean useIceTOB()
    {
        return false;
    }

    // === TOB Lunar Spells === //
    @ConfigSection(
            name = "TOB Lunar Spells",
            description = "Lunar spells to use in TOB",
            position = 10,
            closedByDefault = true
    )
    String tobLunarSpells = "TOB Lunar Spells";

    @ConfigItem(
            keyName = "labelTOBLunar",
            name = "Lunar spell requirements for TOB.", description = "",
            position = 1,
            section = tobLunarSpells)
    default String labelTOBLunar()
    {
        return "";
    }

    @ConfigItem(
            keyName = "useCureTOB",
            name = "Cure Spells",
            description = "Use a cure spell (Lunar)",
            position = 2,
            section = tobLunarSpells
    )
    default boolean useCureTOB()
    {
        return false;
    }

    @ConfigItem(
            keyName = "usePotShareTOB",
            name = "Pot Share",
            description = "Use a potshare spell (Lunar)",
            position = 3,
            section = tobLunarSpells
    )
    default boolean usePotShareTOB()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useVengeTOB",
            name = "Venge",
            description = "Use the venge spell (Lunar)",
            position = 4,
            section = tobLunarSpells
    )
    default boolean useVengeTOB()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useHumidifyTOB",
            name = "Humidify",
            description = "Use the humidify spell (Lunar)",
            position = 5,
            section = tobLunarSpells
    )
    default boolean useHumidifyTOB()
    {
        return false;
    }

//endregion
//---------------------------------------------------------------------------------------------------------------------------------
//region TOA
    @ConfigSection(
            name = "TOA",
            description = "Spells to use in TOA",
            position = 11,
            closedByDefault = true
    )
    String toaSpellbookSection = "TOA";

    @ConfigItem(
            keyName = "enableTOA",
            name = "Enable",
            description = "Enabled for TOA",
            position = 1,
            section = toaSpellbookSection
    )
    default boolean enableTOA()
    {
        return true;
    }

    @ConfigItem(
            keyName = "spellSelectionTOA",
            name = "Spellbook selection",
            description = "Spellbook to use for TOA",
            position = 2,
            section = toaSpellbookSection
    )
    default Spellbook spellSelectionTOA()
    {
        return Spellbook.STANDARD;
    }

    // === TOA Standard Spells === //
    @ConfigSection(
            name = "TOA Standard Spells",
            description = "Standard spells to use in TOA",
            position = 12,
            closedByDefault = true
    )
    String toaStandardSpells = "TOA Standard Spells";

    @ConfigItem(
            keyName = "labelTOAStandard",
            name = "Standard spell requirements for TOA.", description = "",
            position = 1,
            section = toaStandardSpells)
    default String labelTOAStandard()
    {
        return "";
    }

    @ConfigItem(
            keyName = "useFireTOA",
            name = "Fire Spell",
            description = "Use a fire spell (Standard)",
            position = 2,
            section = toaStandardSpells
    )
    default boolean useFireTOA()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useWaterTOA",
            name = "Water Spell",
            description = "Use a water spell (Standard)",
            position = 3,
            section = toaStandardSpells
    )
    default boolean useWaterTOA()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useEarthTOA",
            name = "Earth Spell",
            description = "Use an earth spell (Standard)",
            position = 4,
            section = toaStandardSpells
    )
    default boolean useEarthTOA()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useAirTOA",
            name = "Air Spell",
            description = "Use an air spell (Standard)",
            position = 5,
            section = toaStandardSpells
    )
    default boolean useAirTOA()
    {
        return false;
    }

    // === TOA Arceuus Spells === //
    @ConfigSection(
            name = "TOA Arceuus Spells",
            description = "Arceuus spells to use in TOA",
            position = 13,
            closedByDefault = true
    )
    String toaArceuusSpells = "TOA Arceuus Spells";

    @ConfigItem(
            keyName = "labelTOAArceuus",
            name = "Arceuus spell requirements for TOA.", description = "",
            position = 1,
            section = toaArceuusSpells)
    default String labelTOAArceuus()
    {
        return "";
    }

    @ConfigItem(
            keyName = "useGraspTOA",
            name = "Grasp Spell",
            description = "Use a grasp spell (Arceuus)",
            position = 2,
            section = toaArceuusSpells
    )
    default boolean useGraspTOA()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useDemonbaneTOA",
            name = "Demonbane Spell",
            description = "Use a demonbane spell (Arceuus)",
            position = 3,
            section = toaArceuusSpells
    )
    default boolean useDemonbaneTOA()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useThrallsTOA",
            name = "Thralls",
            description = "Use a thrall spell (Arceuus)",
            position = 4,
            section = toaArceuusSpells
    )
    default boolean useThrallsTOA()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useDeathChargeTOA",
            name = "Death Charge",
            description = "Use the death charge spell (Arceuus)",
            position = 5,
            section = toaArceuusSpells
    )
    default boolean useDeathChargeTOA()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useMarkOfDarknessTOA",
            name = "Mark of Darkness",
            description = "Use the mark of darkness spell (Arceuus)",
            position = 6,
            section = toaArceuusSpells
    )
    default boolean useMarkOfDarknessTOA()
    {
        return false;
    }

    // === TOA Ancient Spells === //
    @ConfigSection(
            name = "TOA Ancient Spells",
            description = "Ancient spells to use in TOA",
            position = 14,
            closedByDefault = true
    )
    String toaAncientSpells = "TOA Ancient Spells";

    @ConfigItem(
            keyName = "labelTOAAncient",
            name = "Ancient spell requirements for TOA.", description = "",
            position = 1,
            section = toaAncientSpells)
    default String labelTOAAncient()
    {
        return "";
    }

    @ConfigItem(
            keyName = "useSmokeTOA",
            name = "Smoke Spell",
            description = "Use a smoke spell (Ancient)",
            position = 2,
            section = toaAncientSpells
    )
    default boolean useSmokeTOA()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useShadowTOA",
            name = "Shadow Spell",
            description = "Use a shadow spell (Ancient)",
            position = 3,
            section = toaAncientSpells
    )
    default boolean useShadowTOA()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useBloodTOA",
            name = "Blood Spell",
            description = "Use a blood spell (Ancient)",
            position = 4,
            section = toaAncientSpells
    )
    default boolean useBloodTOA()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useIceTOA",
            name = "Ice Spell",
            description = "Use an ice spell (Ancient)",
            position = 5,
            section = toaAncientSpells
    )
    default boolean useIceTOA()
    {
        return false;
    }

    // === TOA Lunar Spells === //
    @ConfigSection(
            name = "TOA Lunar Spells",
            description = "Lunar spells to use in TOA",
            position = 15,
            closedByDefault = true
    )
    String toaLunarSpells = "TOA Lunar Spells";

    @ConfigItem(
            keyName = "labelTOALunar",
            name = "Lunar spell requirements for TOA.", description = "",
            position = 1,
            section = toaLunarSpells)
    default String labelTOALunar()
    {
        return "";
    }

    @ConfigItem(
            keyName = "useCureTOA",
            name = "Cure Spells",
            description = "Use a cure spell (Lunar)",
            position = 2,
            section = toaLunarSpells
    )
    default boolean useCureTOA()
    {
        return false;
    }

    @ConfigItem(
            keyName = "usePotShareTOA",
            name = "Pot Share",
            description = "Use a potshare spell (Lunar)",
            position = 3,
            section = toaLunarSpells
    )
    default boolean usePotShareTOA()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useVengeTOA",
            name = "Venge",
            description = "Use the venge spell (Lunar)",
            position = 4,
            section = toaLunarSpells
    )
    default boolean useVengeTOA()
    {
        return false;
    }

    @ConfigItem(
            keyName = "useHumidifyTOA",
            name = "Humidify",
            description = "Use the humidify spell (Lunar)",
            position = 5,
            section = toaLunarSpells
    )
    default boolean useHumidifyTOA()
    {
        return false;
    }

//endregion
//region Notification
    @ConfigSection(
            name = "Notification",
            description = "Notification Settings",
            position = 16,
            closedByDefault = true
    )
    String notification = "Notification";

    @ConfigItem(
            keyName = "enableSoundEffect",
            name = "Enable sound effect",
            description = "Enable sound effect to play",
            position = 1,
            section = notification
    )
    default boolean enableSoundEffect()
    {
        return true;
    }

    @ConfigItem(
            keyName = "soundEffect",
            name = "Sound Effect",
            description = "Sound effect to play",
            position = 2,
            section = notification
    )
    default Sound soundEffect()
    {
        return Sound.FAIRY_RING;
    }

    @Range(min = 0, max = 100)
    @Units(Units.PERCENT)
    @ConfigItem(
            keyName = "soundVolume",
            name = "Sound Volume",
            description = "Volume of the sound effect (0% = mute, 100% = full volume)",
            position = 3,
            section = notification
    )
    default int soundVolume()
    {
        return 10;
    }

    @ConfigItem(
            keyName = "customSound",
            name = "Custom Sound",
            description = "Name of custom .wav file placed in /resources/sounds/",
            position = 4,
            section = notification
    )
    default String customSound()
    {
        return "myfile.wav";
    }

    @ConfigItem(
            keyName = "enablePopup",
            name = "Enable popup",
            description = "Enable popup to display",
            position = 5,
            section = notification
    )
    default boolean enablePopup()
    {
        return true;
    }

    @ConfigItem(
            keyName = "popupSize",
            name = "Popup Size",
            description = "Size of the popup box",
            position = 6,
            section = notification
    )
    @Range(
            min = 10,
            max = 100
    )
    default int popupSize()
    {
        return 20;
    }

    @ConfigItem(
            keyName = "popupDuration",
            name = "Popup Duration (ms)",
            description = "How long the popup message stays on screen",
            position = 7,
            section = notification
    )
    @Range(
            min = 500,
            max = 10000
    )
    default int popupDuration()
    {
        return 3000; // default 3 seconds
    }

    @ConfigItem(
            keyName = "popupBackgroundColor",
            name = "Popup Background Color",
            description = "Color of the popup overlay background",
            position = 8,
            section = notification
    )
    default Color popupBackgroundColor()
    {
        return new Color(255, 0, 0, 200); // semi-transparent red by default
    }

    @ConfigItem(
            keyName = "popupTextColor",
            name = "Popup Text Color",
            description = "Color of the popup overlay text",
            position = 9,
            section = notification
    )
    default Color popupTextColor()
    {
        return new Color(255, 255, 255, 200); // semi-transparent white by default
    }
//endregion
}
