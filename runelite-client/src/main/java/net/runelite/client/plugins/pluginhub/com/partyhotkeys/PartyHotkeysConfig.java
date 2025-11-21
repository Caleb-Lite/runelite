package net.runelite.client.plugins.pluginhub.com.partyhotkeys;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

@ConfigGroup(PartyHotkeysConfig.GROUP)
public interface PartyHotkeysConfig extends Config
{
	String GROUP = "partyhotkeys";
	@ConfigItem(
			keyName = "disconnectedWarning",
			name = "Disconnected Warning",
			description = "Displays an info box indicating the user is disconnected from party.",
			position = 0
	)
	default boolean disconnectedWarning()
	{
		return true;
	}

	@ConfigItem(
			keyName = "rejoinPreviousKey",
			name = "Rejoin Previous",
			description = "When you press this key you'll attempt to rejoin your previous party",
			position = 1
	)
	default Keybind rejoinPreviousKey()
	{
		return new Keybind(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
	}

	@ConfigItem(
			keyName = "leaveKey",
			name = "Leave Party",
			description = "When you press this key you'll leave your current party, you won't be able to rejoin via hotkeys for a few seconds",
			position = 2
	)
	default Keybind leavePartyKey()
	{
		return new Keybind(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK);
	}

	@ConfigItem(
			keyName = "presetParty1",
			name = "Preset Party 1",
			description = "Preset name of party 1 that you can join via the below hotkey (you must leave your party first)",
			position = 3
	)
	default String presetParty1()
	{
		return "";
	}
	@ConfigItem(
			keyName = "joinParty1Key",
			name = "Join Party 1",
			description = "When you press this key you'll attempt to join your 1st preset party",
			position = 4
	)
	default Keybind joinParty1Key()
	{
		return new Keybind(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK);
	}

	@ConfigItem(
			keyName = "presetParty2",
			name = "Preset Party 2",
			description = "Preset name of party 2 that you can join via the below hotkey (you must leave your party first)",
			position = 5
	)
	default String presetParty2()
	{
		return "";
	}
	@ConfigItem(
			keyName = "joinParty2Key",
			name = "Join Party 2",
			description = "When you press this key you'll attempt to join your 2nd preset party",
			position = 6
	)
	default Keybind joinParty2Key()
	{
		return new Keybind(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK);
	}

	@ConfigItem(
			keyName = "presetParty3",
			name = "Preset Party 3",
			description = "Preset name of party 3 that you can join via the below hotkey (you must leave your party first)",
			position = 7
	)
	default String presetParty3()
	{
		return "";
	}
	@ConfigItem(
			keyName = "joinParty3Key",
			name = "Join Party 3",
			description = "When you press this key you'll attempt to join your 3rd preset party",
			position = 8
	)
	default Keybind joinParty3Key()
	{
		return new Keybind(KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK);
	}
}
