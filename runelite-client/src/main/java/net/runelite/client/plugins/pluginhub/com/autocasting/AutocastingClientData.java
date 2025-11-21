package net.runelite.client.plugins.pluginhub.com.autocasting;

import net.runelite.client.plugins.pluginhub.com.autocasting.datatypes.Spell;
import net.runelite.api.*;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.gameval.VarbitID;

import org.apache.commons.lang3.ArrayUtils;

import javax.inject.Inject;

public class AutocastingClientData
{
	@Inject
	private Client client;

	public int getAutocastVarbit()
	{
		return client.getVarbitValue(VarbitID.AUTOCAST_SPELL);
	}

	public Spell getAutocastSpell()
	{
		int varbitValue = getAutocastVarbit();
		return Spell.getSpell(varbitValue);
	}

	public int getWeaponTypeId()
	{
		return client.getVarbitValue(VarbitID.COMBAT_WEAPON_CATEGORY);
	}

	// Based off StatusBarsPlugin.java
	public boolean isInCombat()
	{
		final Player localPlayer = client.getLocalPlayer();
		if (localPlayer == null)
		{
			return false;
		}
		final Actor interacting = localPlayer.getInteracting();
		boolean fightingNPC = interacting instanceof NPC && ArrayUtils.contains(((NPC) interacting).getComposition().getActions(), "Attack");
		boolean fightingPlayer = interacting instanceof Player && client.getVarbitValue(VarbitID.PVP_AREA_CLIENT) == 1;
		return fightingNPC || fightingPlayer;
	}

	public int getGameTick()
	{
		return client.getTickCount();
	}

	public int fps()
	{
		return client.getFPS();
	}

	public EnumComposition getRunepouchEnum()
	{
		return client.getEnum(EnumID.RUNEPOUCH_RUNE);
	}

	public int varbitValue(int varbit)
	{
		return client.getVarbitValue(varbit);
	}

	public ItemContainer getInventory()
	{
		return client.getItemContainer(InventoryID.INV);
	}

	public ItemContainer getEquipment()
	{
		return client.getItemContainer(InventoryID.WORN);
	}
}

/*
 * Copyright (c) 2017, honeyhoney <https://github.com/honeyhoney>
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