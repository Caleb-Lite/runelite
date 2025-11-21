package net.runelite.client.plugins.pluginhub.com.hotcoldhelper;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;

@ConfigGroup("hotcoldhelper")
public interface HotColdHelperConfig extends Config
{
	@ConfigSection(
		name = "General",
		description = "General Plugin Configuration",
		position = 0
	)
	String generalSection = "generalSection";

	@Range(
			min = 1,
			max = 10
	)
	@ConfigItem(
			keyName = "maxTeleportEntries",
			name = "Max Teleport Entries",
			description = "Maximum number of teleport locations to display in the overlay (1-10)",
			position = 0,
			section = "generalSection"
	)
	default int maxTeleportEntries()
	{
		return 5;
	}

	@ConfigItem(
		keyName = "useShorthandNames",
		name = "Use Shorthand Names",
		description = "Use shorter teleport names in the overlay (With teleport hotkeys where applicable)",
		position = 1,
		section = "generalSection"
	)
	default boolean useShorthandNames()
	{
		return false;
	}

	@ConfigSection(
			name = "Spellbooks",
			description = "Configure which spellbook teleports to include",
			position = 1
	)
	String spellbooksSection = "spellbooksSection";

	@ConfigItem(
			position = 0,
			keyName = "includeStandardTeleports",
			name = "Standard Spellbook",
			description = "Includes teleports from the Standard spellbook",
			section = "spellbooksSection"
	)
	default boolean includeStandardTeleports()
	{
		return true;
	}

	@ConfigItem(
			position = 1,
			keyName = "includeAncientTeleports",
			name = "Ancient Magicks",
			description = "Includes teleports from the Ancient Magicks spellbook",
			section = "spellbooksSection"
	)
	default boolean includeAncientTeleports()
	{
		return true;
	}

	@ConfigItem(
			position = 2,
			keyName = "includeLunarTeleports",
			name = "Lunar Spellbook",
			description = "Includes teleports from the Lunar spellbook",
			section = "spellbooksSection"
	)
	default boolean includeLunarTeleports()
	{
		return true;
	}

	@ConfigItem(
			position = 3,
			keyName = "includeArceuusTeleports",
			name = "Arceuus Spellbook",
			description = "Includes teleports from the Arceuus spellbook",
			section = "spellbooksSection"
	)
	default boolean includeArceuusTeleports()
	{
		return true;
	}

	@ConfigSection(
			name = "Capes of Accomplishment",
			description = "Configure which cape teleports to include",
			position = 2
	)
	String capesSection = "capesSection";

	@ConfigItem(
			position = 0,
			keyName = "includeDiaryCapeTeleports",
			name = "Achievement Diary Cape",
			description = "Include Achievement Diary Cape teleports",
			section = "capesSection"
	)
	default boolean includeDiaryCapeTeleports()
	{
		return true;
	}

	@ConfigItem(
			position = 1,
			keyName = "includeQPCapeTeleports",
			name = "Quest Point Cape",
			description = "Include Quest Point Cape teleports",
			section = "capesSection"
	)
	default boolean includeQPCapeTeleports()
	{
		return true;
	}

	@ConfigItem(
			position = 2,
			keyName = "includeMusicCapeTeleports",
			name = "Music Cape",
			description = "Include Music Cape teleports",
			section = "capesSection"
	)
	default boolean includeMusicCapeTeleports()
	{
		return true;
	}

	@ConfigItem(
			position = 3,
			keyName = "includeStrengthCapeTeleports",
			name = "Strength Cape",
			description = "Include Strength Cape teleports",
			section = "capesSection"
	)
	default boolean includeStrengthCapeTeleports()
	{
		return true;
	}

	@ConfigItem(
			position = 4,
			keyName = "includeHunterCapeTeleports",
			name = "Hunter Cape",
			description = "Include Hunter Cape teleports",
			section = "capesSection"
	)
	default boolean includeHunterCapeTeleports()
	{
		return true;
	}

	@ConfigItem(
			position = 5,
			keyName = "includeFarmingCapeTeleports",
			name = "Farming Cape",
			description = "Include Farming Cape teleports",
			section = "capesSection"
	)
	default boolean includeFarmingCapeTeleports()
	{
		return true;
	}

	@ConfigItem(
			position = 6,
			keyName = "includeFishingCapeTeleports",
			name = "Fishing Cape",
			description = "Include Fishing Cape teleports",
			section = "capesSection"
	)
	default boolean includeFishingCapeTeleports()
	{
		return true;
	}

	@ConfigItem(
			position = 7,
			keyName = "includeCraftingCapeTeleports",
			name = "Crafting Cape",
			description = "Include Crafting Cape teleports",
			section = "capesSection"
	)
	default boolean includeCraftingCapeTeleports()
	{
		return true;
	}

	@ConfigItem(
			position = 8,
			keyName = "includeConstructionCapeTeleports",
			name = "Construction Cape",
			description = "Include Construction Cape teleports",
			section = "capesSection"
	)
	default boolean includeConstructionCapeTeleports()
	{
		return true;
	}

	@ConfigSection(
			name = "Miscellaneous",
			description = "Configure other teleport options",
			position = 3
	)
	String miscSection = "miscSection";

	@ConfigItem(
		position = 0,
		keyName = "includeWildernessTeleports",
		name = "Wilderness Teleports",
		description = "Include teleports that lead into the Wilderness",
		section = "miscSection"
	)
	default boolean includeWildernessTeleports()
	{
		return false;
	}

	@ConfigItem(
			position = 1,
			keyName = "includePOHJewelryTeleports",
			name = "POH Jewelry Teleports",
			description = "Include teleports from jewelry items that can be stored in the POH",
			section = "miscSection"
	)
	default boolean includePOHJewelryTeleports()
	{
		return false;
	}

	@ConfigItem(
			position = 2,
			keyName = "includeNecklaceOfPassageTeleports",
			name = "Necklace of Passage",
			description = "Include teleports from Necklace of Passage",
			section = "miscSection"
	)
	default boolean includeNecklaceOfPassageTeleports()
	{
		return false;
	}

	@ConfigItem(
			position = 3,
			keyName = "includeCamuletTeleports",
			name = "Camulet",
			description = "Include teleports from Camulet",
			section = "miscSection"
	)
	default boolean includeCamuletTeleports()
	{
		return false;
	}

	@ConfigItem(
			position = 4,
			keyName = "includePendantOfAtesTeleports",
			name = "Pendant of Ates",
			description = "Include teleports from Pendant of Ates",
			section = "miscSection"
	)
	default boolean includePendantOfAtesTeleports()
	{
		return false;
	}

	@ConfigItem(
			position = 5,
			keyName = "includePharaohsSceptreTeleports",
			name = "Pharaoh's Sceptre",
			description = "Include teleports from Pharaoh's Sceptre",
			section = "miscSection"
	)
	default boolean includePharaohsSceptreTeleports()
	{
		return false;
	}

	@ConfigItem(
			position = 6,
			keyName = "includeRingOfTheElementsTeleports",
			name = "Ring of the Elements",
			description = "Include teleports from Ring of the Elements",
			section = "miscSection"
	)
	default boolean includeRingOfTheElementsTeleports()
	{
		return false;
	}

	@ConfigItem(
			position = 7,
			keyName = "includeRoyalSeedPodTeleports",
			name = "Royal Seed Pod",
			description = "Include teleports from Royal Seed Pod",
			section = "miscSection"
	)
	default boolean includeRoyalSeedPodTeleports()
	{
		return false;
	}

	@ConfigItem(
		position = 8,
		keyName = "includeSlayerRingTeleports",
		name = "Slayer Ring",
		description = "Include teleports from Slayer Ring",
		section = "miscSection"
	)
	default boolean includeSlayerRingTeleports()
	{
		return false;
	}

	@ConfigItem(
		position = 9,
		keyName = "includeMSBTeleports",
		name = "Master Scroll Book",
		description = "Include teleports from Master Scroll Book",
		section = "miscSection"
	)
	default boolean includeMSBTeleports()
	{
		return true;
	}
}
/*
 * Copyright (c) 2025, Lalochazia
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
