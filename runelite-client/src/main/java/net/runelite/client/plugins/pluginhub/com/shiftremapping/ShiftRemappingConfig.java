package net.runelite.client.plugins.pluginhub.com.shiftremapping;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ModifierlessKeybind;

@ConfigGroup("shiftremapping")
public interface ShiftRemappingConfig extends Config
{
	@ConfigItem(
		position = 0,
		keyName = "shift",
		name = "Shift",
		description = "The key which will replace Shift."
	)
	default ModifierlessKeybind shift()
	{
		return new ModifierlessKeybind(KeyEvent.VK_UNDEFINED, InputEvent.SHIFT_DOWN_MASK);
	}
	@ConfigItem(
			position = 1,
			keyName = "enterToChat",
			name = "Enter to chat",
			description = "Enables pressing Enter to chat."
	)
	default boolean enterToChat()
	{
		return true;
	}
	@ConfigItem(
			position = 2,
			keyName = "disableIfMenuOpen",
			name = "Disable if menu open",
			description = "Disables shift remap if dialog options or bank pin interface is open."
	)
	default boolean disableIfMenuOpen()
	{
		return true;
	}
}

