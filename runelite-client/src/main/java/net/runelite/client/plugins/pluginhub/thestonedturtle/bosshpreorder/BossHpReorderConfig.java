package net.runelite.client.plugins.pluginhub.thestonedturtle.bosshpreorder;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;

@ConfigGroup(BossHpReorderConfig.GROUP_KEY)
public interface BossHpReorderConfig extends Config
{
	String GROUP_KEY = "bosshpreorder";
    String TOB_KEY = "applyToTob";
    String TOB_BAR_OFFSET_KEY = "tobBarOffset";

    @Range(
        min = 5,
        max = 23
    )
    @ConfigItem(
        keyName = "barOffset",
        name = "HP Bar Offset",
        description = "How many pixels from the top of the screen the HP bar should be offset by",
        position = 1
    )
    default int barOffset()
    {
        return 23;
    }


    @ConfigSection(
        name = "Theatre of Blood",
        description = "The options that relate to the Theatre of Blood",
        position = 1
    )
    String tobSection = "theatreOfBlood";

    @ConfigItem(
        keyName = TOB_KEY,
        name = "Apply to TOB",
        description = "Should the plugin also apply the offset to the Theatre of Blood progress bar?",
        position = 1,
        section = tobSection
    )
    default boolean applyToTob()
    {
        return true;
    }

    @Range(
        min = 1,
        max = 25
    )
    @ConfigItem(
        keyName = "tobBarOffset",
        name = "HP Bar Offset",
        description = "How many pixels from the top of the screen the HP bar should be offset by",
        position = 2,
        section = tobSection
    )
    default int tobBarOffset()
    {
        return 25;
    }
}
