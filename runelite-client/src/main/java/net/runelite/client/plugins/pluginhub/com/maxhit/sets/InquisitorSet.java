package net.runelite.client.plugins.pluginhub.com.maxhit.sets;

import net.runelite.client.plugins.pluginhub.com.maxhit.equipment.EquipmentFunctions;
import net.runelite.client.plugins.pluginhub.com.maxhit.equipment.InquisitorPieces;
import net.runelite.client.plugins.pluginhub.com.maxhit.styles.AttackType;
import net.runelite.client.plugins.pluginhub.com.maxhit.styles.StyleFactory;
import java.util.Arrays;
import net.runelite.api.EquipmentInventorySlot;
import net.runelite.api.Client;
import net.runelite.api.ItemContainer;
import net.runelite.api.gameval.ItemID;

public class InquisitorSet
{

	public static double getMultiplier(Client client, ItemContainer equippedItems)
	{
		double bonus = 0.0;
		AttackType attackType = StyleFactory.getAttackType(client);
		if (attackType != AttackType.CRUSH)
			return bonus;
		long piecesEquipped = Arrays.stream(InquisitorPieces.values())
			.filter(piece -> piece.isEquipped(equippedItems))
			.count();

		boolean maceEquipped = EquipmentFunctions.HasEquipped(equippedItems, EquipmentInventorySlot.WEAPON, ItemID.INQUISITORS_MACE);
		if (maceEquipped)
			bonus += piecesEquipped * 0.025;
		else
			bonus += piecesEquipped * 0.005;
		if (piecesEquipped == 3 && !maceEquipped)
		{
			bonus += 0.01;
		}
		return bonus;
	}
}

/*
 * Copyright (c) 2023, pajlada <https://github.com/pajlada>
 * Copyright (c) 2023, pajlads <https://github.com/pajlads>
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

