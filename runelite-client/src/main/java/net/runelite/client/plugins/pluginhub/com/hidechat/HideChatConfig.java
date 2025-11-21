package net.runelite.client.plugins.pluginhub.com.hidechat;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;
import net.runelite.client.config.Range;

@ConfigGroup("hidechat")
public interface HideChatConfig extends Config {
	@ConfigItem(position = 0, keyName = "Hide Chat", name = "Hide Chat", description = "Hide the chat box from screen")
	default boolean hideChatBox() {
		return false;
	}

	@ConfigItem(position = 1, keyName = "toggleHotkey", name = "Toggle Hotkey", description = "Keybind to toggle hiding the chat box")
	default Keybind toggleHotkey() {
		return Keybind.NOT_SET; // Default to no keybind
	}

	@ConfigItem(position = 2, keyName = "hideInPvm", name = "Hide chat in PvM", description = "Hide the chatbox when interacting with NPCs (PvM)")
	default boolean hideInPvm() {
		return false;
	}

	@ConfigItem(position = 3, keyName = "hideInPvp", name = "Hide chat in PvP", description = "Hide the chatbox when interacting with other players (PvP)")
	default boolean hideInPvp() {
		return false;
	}

	@ConfigItem(position = 4, keyName = "combatTimeoutSeconds", name = "Combat Hide Timeout", description = "Number of seconds after last combat XP to keep chat hidden.")
	@Range(min = 1, max = 60)
	default int combatTimeoutSeconds() {
		return 8;
	}
}
