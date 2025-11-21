package net.runelite.client.plugins.pluginhub.com.crosshaircursor;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

@ConfigGroup("crosshaircursor")
public interface CrosshairCursorConfig extends Config
{
    @ConfigItem(
            keyName = "toggleKey",
            name = "Toggle Cursor Keybind",
            description = "Toggle the crosshair cursor on/off"
    )
    default Keybind toggleKey()
    {
        return new Keybind(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK);
    }
}
