package net.runelite.client.plugins.pluginhub.com.damagecounter;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("damagecounter")
public interface DamageCounterConfig extends Config
{
	@ConfigItem(
		keyName = "sendToChat",
		name = "Display in chat log",
		description = "Display details in chat log after each kill"
	)
	default boolean sendToChat()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showDamage",
		name = "Show damage",
		description = "Show total damage instead of DPS"
	)
	default boolean showDamage()
	{
		return true;
	}

	@ConfigItem(
		keyName = "overlayAutoHide",
		name = "Automatically hide overlay",
		description = "Automatically hide the overlay when the boss dies"
	)
	default boolean overlayAutoHide()
	{
		return true;
	}

	@ConfigItem(
		keyName = "overlayHide",
		name = "Always hide overlay",
		description = "Always hide the overlay"
	)
	default boolean overlayHide() {	return false; }

	@ConfigItem(
		keyName = "overlaySort",
		name = "Sort Damage",
		description = "Sorts damage list by player from highest damage to lowest"
	)
	default boolean overlaySort() {	return false; }

	@ConfigItem(
		keyName = "additionalNpcs",
		name = "Additional NPCs",
		description = "Also track these NPCs, comma separated"
	)
	String additionalNpcs();
}

/*
 * Copyright (c) 2020 Adam <Adam@sigterm.info>
 * Copyright (c) 2020 0anth <https://github.com/0anth/damage-counter/>
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