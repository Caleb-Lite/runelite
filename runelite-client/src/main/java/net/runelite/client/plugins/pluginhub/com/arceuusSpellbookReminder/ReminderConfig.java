package net.runelite.client.plugins.pluginhub.com.arceuusSpellbookReminder;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.Color;

@ConfigGroup("arceuusreminder")
public interface ReminderConfig extends Config
{
    enum ReminderStyle
    {
        LONG_TEXT,
        SHORT_TEXT,
        CUSTOM_TEXT
    }

    @ConfigItem(
            keyName = "color",
            name = "Overlay Color",
            description = "Color of the reminder overlay background"
    )
    default Color color()
    {
        return new Color(0, 102, 204, 150); // semi-transparent blue
    }

    @ConfigItem(
            keyName = "reminderStyle",
            name = "Text Style",
            description = "Choose between long or short reminder text"
    )
    default ReminderStyle reminderStyle()
    {
        return ReminderStyle.LONG_TEXT;
    }

    @ConfigItem(
            keyName = "customText",
            name = "Custom Reminder Text",
            description = "Text to display if you select the 'Custom Text' style"
    )
    default String customText()
    {
        return "SWTICH TO ARCEUUS!";
    }

    @ConfigItem(
            keyName = "ignoreClues",
            name = "Ignore if carrying clues and caskets",
            description = "Do not show reminder when you have any clue scroll or casket in inventory"
    )
    default boolean ignoreClues()
    {
        return true;
    }
}
