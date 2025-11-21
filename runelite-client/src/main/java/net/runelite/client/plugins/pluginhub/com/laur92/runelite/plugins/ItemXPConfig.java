package net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins;

import net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins.skills.farming.Compost;
import net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins.skills.prayer.POHAltar;
import net.runelite.client.config.*;

@ConfigGroup(ItemXPConfig.CONFIG_GROUP)
public interface ItemXPConfig extends Config
{
    String CONFIG_GROUP = "itemXP";
    String PLUGIN_VERSION = "PLUGIN_VERSION";

    String SHOW_LEVEL_REQUIREMENT = "SHOW_LEVEL_REQUIREMENT";
    String SHOW_STACK_CALCULATIONS = "SHOW_STACK_CALCULATIONS";
    String SHOW_SKILL_FARMING = "SHOW_SKILL_FARMING";
    String SHOW_SKILL_PRAYER = "SHOW_SKILL_PRAYER";
    String SHOW_SKILL_FIREMAKING = "SHOW_SKILL_FIREMAKING";
    String SHOW_SKILL_FLETCHING = "SHOW_SKILL_FLETCHING";
    String FARMING_SHOW_RANGE = "FARMING_SHOW_RANGE";
    String FARMING_SHOW_EXPECTED_YIELD = "FARMING_SHOW_EXPECTED_YIELD";
    String FARMING_COMPOST_TYPE = "FARMING_COMPOST_TYPE";
    String FARMING_MAGIC_SECATEURS = "FARMING_MAGIC_SECATEURS";
    String FARMING_SKILL_CAPE = "FARMING_SKILL_CAPE";
    String FARMING_ATTAS = "FARMING_ATTAS";
    String PRAYER_SHOW_ECTO = "PRAYER_SHOW_ECTO";
    String PRAYER_POH_ALTAR = "PRAYER_POH_ALTAR";
    String PRAYER_POH_BURNERS = "PRAYER_POH_BURNERS";
    String PRAYER_CHAOS_TEMPLE = "PRAYER_CHAOS_TEMPLE";
    String PRAYER_OFFERING_SPELLS = "PRAYER_OFFERING_SPELLS";
    String FIREMAKING_SHOW_CAMPFIRE = "FIREMAKING_SHOW_CAMPFIRE";
    String FIREMAKING_SHOW_PYRE = "FIREMAKING_SHOW_PYRE";

    @ConfigSection(
            name = "Overall",
            description = "Overall settings for all tooltips",
            position = 0
    )
    String overallSection = "OVERALL_SECTION";

    @ConfigSection(
            name = "Skills",
            description = "Settings to turn on/off specific skills",
            position = 1
    )
    String skillsSection = "SKILLS_SECTION";

    @ConfigSection(
            name = "Farming",
            description = "Farming specfic settings",
            position = 2,
            closedByDefault = true
    )
    String farmingSkillSection = "FARMING_SKILL_SECTION";

    @ConfigSection(
            name = "Prayer",
            description = "Prayer specfic settings",
            position = 3,
            closedByDefault = true
    )
    String prayerSkillSection = "PRAYER_SKILL_SECTION";

    @ConfigSection(
            name = "Firemaking",
            description = "Firemaking specific settings",
            position = 4,
            closedByDefault = true
    )
    String firemakingSkillSection = "FIREMAKING_SKILL_SECTION";


    @ConfigItem(
            keyName = PLUGIN_VERSION,
            name = "Plugin Version",
            section = overallSection,
            description = "Last loaded plugin version",
            secret = true,
            hidden = true
    )
    default String pluginVersion() { return "0.1"; }

    @ConfigItem(
            position = 0,
            keyName = SHOW_LEVEL_REQUIREMENT,
            name = "Show Level Requirement",
            section = overallSection,
            description = "Configure whether to show the level requirement to use an item"
    )
    default boolean showLevelRequirement()
    {
        return true;
    }

    @ConfigItem(
            position = 1,
            keyName = SHOW_STACK_CALCULATIONS,
            name = "Show Stack XP",
            section = overallSection,
            description = "Also show XP for using entire stack in addition to per item"
    )
    default boolean showStackCalculations() { return true; }


    @ConfigItem(
            position = 0,
            keyName = SHOW_SKILL_FARMING,
            name = "Farming",
            section = skillsSection,
            description = "Show farming related XP on items",
            warning = "This plugin does not take into account the effects of disease or death; all plants are assumed to survive"
    )
    default boolean showFarmingSkill()
    {
        return true;
    }
    @ConfigItem(
            position = 1,
            keyName = SHOW_SKILL_PRAYER,
            name = "Prayer",
            section = skillsSection,
            description = "Show prayer related XP on items"
    )
    default boolean showPrayerSkill()
    {
        return true;
    }

    @ConfigItem(
            position = 2,
            keyName = SHOW_SKILL_FIREMAKING,
            name = "Firemaking",
            section = skillsSection,
            description = "Show firemaking related XP on items"
    )
    default boolean showFiremakingSkill()
    {
        return true;
    }

    @ConfigItem(
            position = 3,
            keyName = SHOW_SKILL_FLETCHING,
            name = "Fletching",
            section = skillsSection,
            description = "Show fletching related XP on items",
            hidden = true
    )
    default boolean showFletchingSkill()
    {
        return true;
    }

    @ConfigItem(
            position = 0,
            keyName = FARMING_SHOW_RANGE,
            name = "Show Yield Range",
            section = farmingSkillSection,
            description = "Show min/max yield from plants"
    )
    default boolean showFarmingYieldRange() { return true; }

    @ConfigItem(
            position = 1,
            keyName = FARMING_SHOW_EXPECTED_YIELD,
            name = "Show Expected Yield",
            section = farmingSkillSection,
            description = "Show the expected number of items harvested for crops",
            warning = "This will be based on other settings in this section"
    )
    default boolean showFarmingExpectedYield() { return true; }

    @ConfigItem(
            position = 2,
            keyName = FARMING_COMPOST_TYPE,
            name = "Compost",
            section = farmingSkillSection,
            description = "The type of compost you use, for harvest calculations"
    )
    default Compost compostType() { return Compost.NONE; }


    @ConfigItem(
            position = 3,
            keyName = FARMING_MAGIC_SECATEURS,
            name = "Magic Secateurs",
            section = farmingSkillSection,
            description = "If you are wielding magic secateurs for harvest bonus"
    )
    default boolean farmingUsingMagicSecateurs() { return false; }

    @ConfigItem(
            position = 4,
            keyName = FARMING_SKILL_CAPE,
            name = "Skill Cape",
            section = farmingSkillSection,
            description = "If you are wearing a farming or max cape"
    )
    default boolean farmingUsingSkillCape() { return false; }

    @ConfigItem(
            position = 5,
            keyName = FARMING_ATTAS,
            name = "Attas Plant",
            section = farmingSkillSection,
            description = "If you have an attas plant growing in the anima patch at the Farming Guild"
    )
    default boolean farmingUsingAttas() { return false; }


    @ConfigItem(
            position = 0,
            keyName = PRAYER_SHOW_ECTO,
            name = "Show Ecto",
            section = prayerSkillSection,
            description = "Show ectofuntus worship experience on bones"
    )
    default boolean prayerShowEcto() { return true; }

    @ConfigItem(
            position = 1,
            keyName = PRAYER_POH_ALTAR,
            name = "POH Altar",
            section = prayerSkillSection,
            description = "Type of altar for POH offering"
    )
    default POHAltar prayerPOHAltar() { return POHAltar.GILDED; }

    @ConfigItem(
            position = 2,
            keyName = PRAYER_POH_BURNERS,
            name = "POH Burners",
            section = prayerSkillSection,
            description = "Number of burners active for POH offering. This setting does nothing if No Altar is chosen above"
    )
    @Range(min = 0, max = 2)
    default int prayerPOHBurners() { return 2; }

    @ConfigItem(
            position = 3,
            keyName = PRAYER_CHAOS_TEMPLE,
            name = "Show Chaos Temple",
            section = prayerSkillSection,
            description = "Show chaos temple offering experience on bones"
    )
    default boolean prayerChaosTemple() { return true; }

    @ConfigItem(
            position = 4,
            keyName = PRAYER_OFFERING_SPELLS,
            name = "Show Offering Spells",
            section = prayerSkillSection,
            description = "Show demonic/sinister offering spell experience on demonic ashes/bones (requires 84/92 magic and arceuus spellbook)"
    )
    default boolean prayerOfferingSpells() { return true; }

    @ConfigItem(
            position = 0,
            keyName = FIREMAKING_SHOW_CAMPFIRE,
            name = "Show Campfire",
            section = firemakingSkillSection,
            description = "Show campfire related experience on logs"
    )
    default boolean firemakingShowCampfire() { return true; }

    @ConfigItem(
            position = 0,
            keyName = FIREMAKING_SHOW_PYRE,
            name = "Show Pyre",
            section = firemakingSkillSection,
            description = "Show experience for turning logs into pyre logs"
    )
    default boolean firemakingShowPyre() { return true; }



}
