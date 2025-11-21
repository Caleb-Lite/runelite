package net.runelite.client.plugins.pluginhub.com.toaspellbookreminder;

import net.runelite.client.plugins.pluginhub.config.SpellbookType;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup("toaspellbookreminder")
public interface ToaSpellbookReminderConfig extends Config {
    @ConfigItem(
            keyName = "spellbook",
            name = "Desired Spellbook",
            description = "The selected spellbook you wanna use regularly"
    )
    default SpellbookType spellbook() {
        return SpellbookType.Standard;
    }

    @ConfigItem(
            keyName = "correctSpellbook",
            name = "Correct Spellbook color",
            description = "The text color when you have the correct spellbook"
    )
    default Color correctSpellbook() {
        return Color.GREEN;
    }

    @ConfigItem(
            keyName = "wrongSpellbook",
            name = "Wrong Spellbook color",
            description = "The text color when you have the wrong spellbook"
    )
    default Color wrongSpellbook() {
        return Color.RED;
    }
}
