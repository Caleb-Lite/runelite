package net.runelite.client.plugins.pluginhub.com.hidewidgets;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

@ConfigGroup("hidewidgets")
public interface HideWidgetsConfig extends Config
{
	@ConfigItem(
		position = 0,
		keyName = "hideWidgetsToggle",
		name = "Hide widgets toggle",
		description = "Enable hotkey to hide all widgets"
	)
	default Keybind hideWidgetsToggle()
	{
		return new Keybind(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK);
	}

	@ConfigItem(
		position = 1,
		keyName = "autoHideOnCutscene",
		name = "Hide widgets in cutscenes",
		description = "Automatically hide in a cutscene"
	)
	default boolean autoHideOnCutscene()
	{
		return false;
	}
}

