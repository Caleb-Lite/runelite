package net.runelite.client.plugins.pluginhub.thestonedturtle.bankedexperience;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("bankedexperience")
public interface BankedExperienceConfig extends Config
{
	String POTION_STORAGE_KEY = "grabFromPotionStorage";

	@ConfigItem(
		keyName = "cascadeBankedXp",
		name = "Include output items",
		description = "Includes output items in the item quantity calculations",
		position = 1
	)
	default boolean cascadeBankedXp()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showSecondaries",
		name = "Show required secondaries",
		description = "Toggles whether the Secondaries will be displayed for the selected item",
		position = 2
	)
	default boolean showSecondaries()
	{
		return false;
	}

	@ConfigItem(
		keyName = "limitToCurrentLevel",
		name = "Respect level requirements",
		description = "Toggles whether the exp calculation will limit to your current skill level",
		position = 3
	)
	default boolean limitToCurrentLevel()
	{
		return true;
	}

	@ConfigItem(
		keyName = "grabFromSeedVault",
		name = "Include seed vault",
		description = "Toggles whether the items stored inside the Seed Vault at the Farming Guild will be included in the calculations",
		position = 4
	)
	default boolean grabFromSeedVault()
	{
		return true;
	}

	@ConfigItem(
		keyName = "grabFromInventory",
		name = "Include player inventory",
		description = "Toggles whether the items inside your inventory will be included in the calculations",
		position = 5
	)
	default boolean grabFromInventory()
	{
		return false;
	}

	@ConfigItem(
		keyName = "grabFromLootingBag",
		name = "Include looting bag",
		description = "Toggles whether the items stored inside your Looting Bag will be included in the calculations",
		position = 6
	)
	default boolean grabFromLootingBag()
	{
		return false;
	}

	@ConfigItem(
		keyName = "grabFromFossilChest",
		name = "Include Fossil Chest",
		description = "Toggles whether the fossils stored inside your Fossil Island chest will be included in the calculations",
		position = 7
	)
	default boolean grabFromFossilChest()
	{
		return true;
	}

	@ConfigItem(
		keyName = POTION_STORAGE_KEY,
		name = "Include Potion Storage",
		description = "Toggles whether items in your potion storage should be included in the calculations",
		position = 7
	)
	default boolean grabFromPotionStorage()
	{
		return true;
	}

	@ConfigItem(
		keyName = "ignoredItems",
		name = "",
		description = "",
		hidden = true
	)
	default String ignoredItems()
	{
		return "";
	}

	@ConfigItem(
		keyName = "ignoredItems",
		name = "",
		description = ""
	)
	void ignoredItems(String val);
}

/*
 * Copyright (c) 2019, TheStonedTurtle <https://github.com/TheStonedTurtle>
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