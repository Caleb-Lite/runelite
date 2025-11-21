package net.runelite.client.plugins.pluginhub.com.goaltracker;

import static com.goaltracker.GoalTrackerConfig.CONFIG_GROUP;
import java.awt.Color;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

@ConfigGroup(CONFIG_GROUP)
public interface GoalTrackerConfig extends Config
{
	String OLD_CONFIG_GROUP = "goaltracker";
	String CONFIG_GROUP = "RegionLockerGoalTracker";

	@ConfigItem(
			keyName = "drawMapOverlay",
			name = "Draw goal chunks on map",
			description = "Draw a colored border for each chunk with goals",
			position = 1
	)
	default boolean drawMapOverlay()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableTooltip",
			name = "Enable tooltip",
			description = "Show tooltip with goals of the chunk you hover over while holding the hotkey below",
			position = 2
	)
	default boolean enableTooltip()
	{
		return true;
	}

	@ConfigItem(
		keyName = "hotKey",
		name = "Tooltip hotkey",
		description = "Which key to hold to view the goals tooltip on the map",
		position = 3
	)
	default Keybind hotKey()
	{
		return Keybind.SHIFT;
	}

	@ConfigItem(
			keyName = "enableQuickDelete",
			name = "Enable shift quick-delete",
			description = "Allows deleting goals more quickly by holding down shift while clicking the delete button",
			position = 4
	)
	default boolean enableQuickDelete()
	{
		return false;
	}

	String HIDE_COMPLETED_GOALS_KEY = "hideCompletedGoals";
	@ConfigItem(
			keyName = HIDE_COMPLETED_GOALS_KEY,
			name = "Hide goals when completed",
			description = "Hides all completed goals from the goal tracker panel",
			position = 5
	)
	default boolean hideCompletedGoals()
	{
		return false;
	}

	String COLLAPSE_REQUIREMENTS_KEY = "collapseRequirements";
	@ConfigItem(
			keyName = COLLAPSE_REQUIREMENTS_KEY,
			name = "Collapse requirements",
			description = "Hides all goal requirements by default",
			position = 6
	)
	default boolean collapseRequirements()
	{
		return false;
	}

	String BLOCKED_COLOR_KEY = "noProgressColor";
	@Alpha
	@ConfigItem(
			keyName = BLOCKED_COLOR_KEY,
			name = "Blocked color",
			description = "Color of goals with no progress",
			position = 7
	)
	default Color blockedColor()
	{
		return Color.RED;
	}

	String IN_PROGRESS_COLOR_KEY = "inProgressColor";
	@Alpha
	@ConfigItem(
			keyName = IN_PROGRESS_COLOR_KEY,
			name = "In-progress color",
			description = "Color of goals that are in progress",
			position = 8
	)
	default Color inProgressColor()
	{
		return Color.YELLOW;
	}

	String COMPLETED_COLOR_KEY = "completedColor";
	@Alpha
	@ConfigItem(
			keyName = COMPLETED_COLOR_KEY,
			name = "Completed color",
			description = "Color of completed goals",
			position = 9
	)
	default Color completedColor()
	{
		return Color.decode("#0dc10d"); // Same color as Jagex uses for completed quests
	}

	String REQUIRED_CHUNK_COLOR_KEY = "requiredChunkColor";
	@Alpha
	@ConfigItem(
			keyName = REQUIRED_CHUNK_COLOR_KEY,
			name = "Required chunk color",
			description = "Color of chunks that are a requirement for goals",
			position = 10
	)
	default Color requiredChunkColor()
	{
		return Color.MAGENTA;
	}
}

/*
 * Copyright (c) 2019, Slay to Stay, <https://github.com/slaytostay>
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