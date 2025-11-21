package net.runelite.client.plugins.pluginhub.com.menustoneshider;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

@ConfigGroup("menustoneshider")
public interface MenuStonesHiderConfig extends Config
{
    @ConfigItem(
            keyName = "hideTopMenu",
            name = "Hide Top Menu Stones",
            description = "Configures whether the Top Menu Stones are hidden",
            position = 0
    )
    default boolean hideTopMenu()
    {
        return true;
    }

    @ConfigItem(
            keyName = "hideBottomMenu",
            name = "Hide Bottom Menu Stones",
            description = "Configures whether the Bottom Menu Stones are hidden",
            position = 1
    )
    default boolean hideBottomMenu()
    {
        return true;
    }

    @ConfigItem(
            keyName = "hideBackground",
            name = "Hide Background",
            description = "Configures whether the background of the menu is hidden (Resizable classic only)",
            position = 2
    )
    default boolean hideBackground()
    {
        return false;
    }

    @ConfigItem(
            keyName = "hideLeftBar",
            name = "Hide Left Sidebar",
            description = "Configures whether the left sidebar is hidden (Resizable classic only)",
            position = 3
    )
    default boolean hideLeftBar()
    {
        return false;
    }

    @ConfigItem(
            keyName = "hideRightBar",
            name = "Hide Right Sidebar",
            description = "Configures whether the right sidebar is hidden (Resizable classic only)",
            position = 4
    )
    default boolean hideRightBar()
    {
        return false;
    }

    @ConfigItem(
            keyName = "showMenuToggle",
            name = "Show hidden Menu hotkey",
            description = "Enable hotkey to show all hidden menu widgets",
            position = 5
    )
    default Keybind showMenuToggle()
    {
        return new Keybind(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK);
    }
}