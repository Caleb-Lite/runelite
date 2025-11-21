package net.runelite.client.plugins.pluginhub.filo.scouter;

import net.runelite.client.plugins.pluginhub.filo.scouter.config.Crabs;
import net.runelite.client.plugins.pluginhub.filo.scouter.config.Layout;
import net.runelite.client.plugins.pluginhub.filo.scouter.config.Overload;
import net.runelite.client.plugins.pluginhub.filo.scouter.config.OverloadPosition;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import java.util.Collections;
import java.util.Set;

@ConfigGroup("coxscoutingqol")
public interface ScoutHelperConfig extends Config
{
	@ConfigSection(
		name = "Overload Settings",
		description = "Select your Overload Rooms (Ctrl and Shift)",
		position = 3,
		closedByDefault = true
	)
	String overloadSection = "Overload Section";

	@ConfigSection(
		name = "Raid Settings",
		description = "Settings for common raid options",
		position = 2,
		closedByDefault = true
	)
	String raidSection = "Raid Section";

	@ConfigSection(
		name = "Layout Settings",
		description = "Filter Layouts (Combat : Puzzle Ratios)",
		position = 1,
		closedByDefault = true
	)
	String layoutSection = "Layout Section";

	@ConfigSection(
		name = "General Settings",
		description = "General Settings",
		position = 0
	)
	String generalSection = "General Settings";

	@ConfigItem(
		keyName = "notifyRaid",
		name = "Notify on Raid",
		description = "Send a notification when a raid is scouted",
		section = generalSection
	)
	default boolean notifyRaid()
	{
		return true;
	}

	@ConfigItem(
		keyName = "layoutType",
		name = "Layout Filter",
		description = "Filter Layout Types: <br><br> 3C2P (3 Combat, 2 Puzzle) <br> 4C1P (4 Combat, 1 Puzzle) <br> 4C2P (4 Combat, 2 Puzzle) <br> None (Exception List) <br><br> To reset right-click 'Layout Filter' -> 'Reset'",
		section = layoutSection,
		position = 1
	)
	default Set<Layout> layoutType()
	{
		return Collections.emptySet();
	}

	@ConfigItem(
		keyName = "layoutKeys",
		name = "Exception Layouts",
		description = "Allows certain layouts to bypass the filter <br> Separate by ',' and spaces are allowed",
		section = layoutSection,
		position = 2
	)
	default String layoutKeys()
	{
		return "";
	}

	@ConfigItem(
		keyName = "rotationEnabled",
		name = "Rotation Toggle",
		description = "Toggle rotations and only searches for the specified rotations.",
		section = raidSection,
		position = 0
	)
	default boolean rotationEnabled()
	{
		return false;
	}

	@ConfigItem(
		keyName = "rotationList",
		name = "Rotations",
		description = "Set a required rotation (only finds these rotations!): <br><br>vasa,shamans,vespula<br>vasa,tekton,vespula<br><br>each must be on their own line!",
		section = raidSection,
		position = 1
	)
	default String rotationList()
	{
		return "";
	}

	@ConfigItem(
		keyName = "blockedRooms",
		name = "Blocked Rooms",
		description = "Block specific rooms (Combat or Puzzle) <br><br>Ice Demon,Vanguards<br><br>Separate by Comma ','",
		section = raidSection,
		position = 2
	)
	default String blockedRooms()
	{
		return "";
	}

	@ConfigItem(
		keyName = "blockedUnknownCombat",
		name = "Block Unknown Combat",
		description = "Block unknown combat rooms",
		section = raidSection,
		position = 3
	)
	default boolean blockedUnknownCombat()
	{
		return true;
	}

	@ConfigItem(
		keyName = "blockedUnknownPuzzles",
		name = "Block Unknown Puzzles",
		description = "Block unknown puzzle rooms",
		section = raidSection,
		position = 4
	)
	default boolean blockedUnknownPuzzles()
	{
		return true;
	}

	@ConfigItem(
		keyName = "preferredCrabs",
		name = "Preferred Crabs",
		description = "Filter crabs to your preferred type",
		section = raidSection,
		position = 5
	)
	default Crabs preferredCrabs()
	{
		return Crabs.ANY;
	}

	@ConfigItem(
		keyName = "overloadRooms",
		name = "Overload Filter",
		description = "A list of overload rooms you can filter <br><br>To select multiple use Ctrl-Click or Shift-Click <br><br>If you want none required Right-Click 'Overload Filter' -> 'Reset'",
		section = overloadSection,
		position = 0
	)
	default Set<Overload> overloadRooms()
	{
		return Collections.emptySet();
	}

	@ConfigItem(
		keyName = "ovlPos",
		name = "Preferred Location",
		description = "Define a preferred location for the Overload to be found",
		section = overloadSection,
		position = 1
	)
	default OverloadPosition ovlPos()
	{
		return OverloadPosition.ANY_ROOM;
	}

	@ConfigItem(
		keyName = "incPuzzleCombat",
		name = "Include Puzzle Combat",
		description = "If you want to include 'Tightrope' and 'Ice Demon' in 'First Combat'",
		section = overloadSection,
		position = 2
	)
	default boolean incPuzzleCombat()
	{
		return false;
	}
}

/*
 * Copyright (c) 2018, Kamiel
 * Copyright (c) 2024, Filofteia <https://github.com/Filofteia>
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