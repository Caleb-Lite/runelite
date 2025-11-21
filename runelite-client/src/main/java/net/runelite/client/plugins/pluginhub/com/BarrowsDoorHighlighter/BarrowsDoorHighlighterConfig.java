package net.runelite.client.plugins.pluginhub.com.BarrowsDoorHighlighter;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.Color;

@ConfigGroup("BarrowsDoorHighlighter")
public interface BarrowsDoorHighlighterConfig extends Config
{
	enum HighlightDoors
	{
		LOCKED,
		UNLOCKED,
		BOTH,
		NEITHER,
	}
	@ConfigItem(
			keyName = "highlightDoors",
			name = "Highlight Doors",
			description = "Select which type of doors to highlight.",
			position = 0
	)
	default HighlightDoors highlightDoors()
	{
		return HighlightDoors.BOTH;
	}
	@Alpha
	@ConfigItem(
			keyName = "unlockedDoorColor",
			name = "Unlocked Door Color",
			description = "Select the unlocked door color.",
			position = 1
	)
	default Color unlockedDoorColor()
	{
		return Color.GREEN;
	}
	@Alpha
	@ConfigItem(
			keyName = "lockedDoorColor",
			name = "Locked Door Color",
			description = "Select the locked door color.",
			position = 2
	)
	default Color lockedDoorColor()
	{
		return Color.RED;
	}
}

