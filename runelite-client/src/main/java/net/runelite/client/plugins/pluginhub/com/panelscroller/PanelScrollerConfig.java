package net.runelite.client.plugins.pluginhub.com.panelscroller;

import java.awt.event.KeyEvent;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;
import net.runelite.client.config.ModifierlessKeybind;

@ConfigGroup(PanelScrollerConfig.GROUP)
public interface PanelScrollerConfig extends Config
{
	String GROUP = "panelscroller";

	@ConfigItem(
		keyName = "modifierKey",
		name = "Modifier Key",
		description = "A key that must be held for the Previous and Next Panel keybinds to work. Must be set to use scroll wheel",
		position = 0
	)
	default Keybind modifierKey()
	{
		return Keybind.CTRL;
	}

	@ConfigItem(
		keyName = "prevPanelKey",
		name = "Previous Panel",
		description = "The keybind to cycle to the previous panel. If unbound defaults to scroll-down",
		position = 1
	)
	default ModifierlessKeybind prevPanelKey()
	{
		return new ModifierlessKeybind(KeyEvent.VK_UNDEFINED, 0);
	}

	@ConfigItem(
		keyName = "nextPanelKey",
		name = "Next Panel",
		description = "The keybind to cycle to the next panel. If unbound defaults to scroll-up",
		position = 2
	)
	default ModifierlessKeybind nextPanelKey()
	{
		return new ModifierlessKeybind(KeyEvent.VK_UNDEFINED, 0);
	}


}

