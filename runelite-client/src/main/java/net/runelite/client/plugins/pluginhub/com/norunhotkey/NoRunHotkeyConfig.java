package net.runelite.client.plugins.pluginhub.com.norunhotkey;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

@ConfigGroup("norunhotkey")
public interface NoRunHotkeyConfig extends Config
{
    @ConfigItem(
            keyName = "hotkey",
            name = "No Run Hotkey",
            description = "Hold this hotkey to hide 'Walk here' from the menu"
    )
    default Keybind hotkey()
    {
        return Keybind.NOT_SET;
    }
}
