package net.runelite.client.plugins.pluginhub.com.example;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Keybind;
import net.runelite.client.config.Range;

@ConfigGroup(WorldCycleConfig.GROUP)
public interface WorldCycleConfig extends Config
{
	String GROUP = "worldcycle";
	String CONFIG_WORLDSET = "worldset";

	@ConfigSection(name="Panel Overlay", description="Optional panel displaying the neighboring worlds in your cycle.", position=20, closedByDefault=true)
	String panelOverlay = "panelOverlay";

	@ConfigItem(
		keyName = "previousKey",
		name = "Quick-hop previous",
		description = "When you press this key you'll hop to the previous world",
		position = 0
	)
	default Keybind previousKey()
	{
		return new Keybind(KeyEvent.VK_LEFT, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK);
	}

	@ConfigItem(
		keyName = "nextKey",
		name = "Quick-hop next",
		description = "When you press this key you'll hop to the next world",
		position = 1
	)
	default Keybind nextKey()
	{
		return new Keybind(KeyEvent.VK_RIGHT, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK);
	}

	@ConfigItem(
		keyName = "showMessage",
		name = "Show world hop message in chat",
		description = "Shows what world is being hopped to in the chat",
		position = 2
	)
	default boolean showWorldHopMessage()
	{
		return true;
	}

	@ConfigItem(
			keyName = "acceptPartyCycle",
			name = "Accept Party Cycle",
			description = "Accept changes to the world cycle made by party members, PVP/HIGH RISK ARE OMITTED",
			position = 3
	)
	default boolean acceptPartyCycle()
	{
		return false;
	}

	@Alpha
	@ConfigItem(
			keyName = "worldPanelColor",
			name = "World Panel Color",
			description = "world panel color",
			position = 4,
			section = panelOverlay
	)
	default Color worldPanelColor()
	{
		return new Color(0,0,0,100);
	}

	@Range(max=16, min=10)
	@ConfigItem(
			position=5,
			keyName="fontSize",
			name="Font Size",
			description="font size",
			section = panelOverlay
	)
	default int fontSize() {
		return 12;
	}

	@ConfigItem(
			keyName = "boldFont",
			name = "Bold Font",
			description = "Configures whether font is bold or not",
			position = 6,
			section = panelOverlay
	)
	default boolean boldFont()
	{
		return true;
	}

	@ConfigItem(
			keyName = "displayPreviousWorld",
			name = "Display Previous World",
			description = "Display the upcoming world you'll hop to using the [Quick-hop previous] Hotkey.",
			position = 7,
			section = panelOverlay
	)
	default boolean displayPreviousWorld()
	{
		return false;
	}

	@ConfigItem(
			keyName = "previousWorldColor",
			name = "Previous World Color",
			description = "Text color of the previous world in cycle",
			position = 8,
			section = panelOverlay
	)
	default Color previousWorldColor()
	{
		return Color.WHITE;
	}

	@ConfigItem(
			keyName = "displayCurrentWorld",
			name = "Display Current World",
			description = "Display the current world you're on, regardless of it being in cycle.",
			position = 9,
			section = panelOverlay
	)
	default boolean displayCurrentWorld()
	{
		return false;
	}

	@ConfigItem(
			keyName = "currentWorldColor",
			name = "Current World Color",
			description = "Text color of your current world.",
			position = 10,
			section = panelOverlay
	)
	default Color currentWorldColor()
	{
		return Color.GREEN;
	}

	@ConfigItem(
			keyName = "displayNextWorld",
			name = "Display Next World",
			description = "Display the upcoming world you'll hop to using the [Quick-hop next] Hotkey.",
			position = 11,
			section = panelOverlay
	)
	default boolean displayNextWorld()
	{
		return false;
	}

	@ConfigItem(
			keyName = "nextWorldColor",
			name = "Next World Color",
			description = "Text color of the next world in cycle",
			position = 12,
			section = panelOverlay
	)
	default Color nextWorldColor()
	{
		return Color.WHITE;
	}

}

/*
 * Copyright (c) 2022, Jamal <http://github.com/1Defence>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */