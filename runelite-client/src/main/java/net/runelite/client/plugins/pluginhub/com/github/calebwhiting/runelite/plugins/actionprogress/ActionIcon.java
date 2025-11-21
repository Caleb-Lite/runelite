package net.runelite.client.plugins.pluginhub.com.github.calebwhiting.runelite.plugins.actionprogress;

import net.runelite.client.plugins.pluginhub.com.github.calebwhiting.runelite.api.ui.IconSource;
import net.runelite.client.plugins.pluginhub.com.github.calebwhiting.runelite.api.ui.ItemIconSource;
import net.runelite.client.plugins.pluginhub.com.github.calebwhiting.runelite.api.ui.SpriteIconSource;
import net.runelite.api.ItemID;
import net.runelite.api.SpriteID;

public interface ActionIcon
{

	IconSource SPRITE_TOTAL = new SpriteIconSource(SpriteID.SKILL_TOTAL, 0);
	IconSource SPRITE_CRAFTING = new SpriteIconSource(SpriteID.SKILL_CRAFTING, 0);
	IconSource SPRITE_FISHING = new SpriteIconSource(SpriteID.SKILL_FISHING, 0);
	IconSource SPRITE_WOODCUTTING = new SpriteIconSource(SpriteID.SKILL_WOODCUTTING, 0);
	IconSource SPRITE_COOKING = new SpriteIconSource(SpriteID.SKILL_COOKING, 0);
	IconSource SPRITE_FLETCHING = new SpriteIconSource(SpriteID.SKILL_FLETCHING, 0);
	IconSource SPRITE_FIREMAKING = new SpriteIconSource(SpriteID.SKILL_FIREMAKING, 0);
	IconSource SPRITE_SMITHING = new SpriteIconSource(SpriteID.SKILL_SMITHING, 0);
	IconSource SPRITE_MAGIC = new SpriteIconSource(SpriteID.SKILL_MAGIC, 0);
	IconSource SPRITE_HERBLORE = new SpriteIconSource(SpriteID.SKILL_HERBLORE, 0);
	IconSource SPRITE_GUARDIAN_OF_THE_RIFT_REWARD = new ItemIconSource(ItemID.ABYSSAL_PROTECTOR);
	IconSource SPRITE_FARMING = new SpriteIconSource(SpriteID.SKILL_FARMING, 0);
	IconSource SPRITE_BUCKET = new ItemIconSource(ItemID.BUCKET);

}

/*
 * Copyright (c) 2019 Adam <Adam@sigterm.info>
 * Copyright (c) 2021 Nick Wolff <nick@wolff.tech>
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

//Taken from https://github.com/WolffTech/coal-bag-plugin/blob/master/src/main/java/com/coalbagplugin/CoalBag.java
