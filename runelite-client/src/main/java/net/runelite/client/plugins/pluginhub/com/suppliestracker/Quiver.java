package net.runelite.client.plugins.pluginhub.com.suppliestracker;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import javax.inject.Inject;
import lombok.Getter;
import net.runelite.api.Item;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.VarPlayerID;
import net.runelite.client.game.ItemVariationMapping;

public class Quiver
{
	private final SuppliesTrackerPlugin plugin;
	private int quiverAmmoId;
	private int quiverAmmoCount;

	private static final Set<Integer> DIZANAS_QUIVER_IDS = ImmutableSet.<Integer>builder()
		.addAll(ItemVariationMapping.getVariations(ItemVariationMapping.map(ItemID.DIZANAS_QUIVER_CHARGED)))
		.addAll(ItemVariationMapping.getVariations(ItemVariationMapping.map(ItemID.DIZANAS_QUIVER_INFINITE)))
		.addAll(ItemVariationMapping.getVariations(ItemVariationMapping.map(ItemID.SKILLCAPE_MAX_DIZANAS)))
		.build();

	@Inject
	Quiver(SuppliesTrackerPlugin plugin) {
		this.plugin = plugin;
		quiverAmmoId = -1;
		quiverAmmoCount = 0;
	}

	public void updateVarp(int varpId)
	{
		if (varpId != VarPlayerID.DIZANAS_QUIVER_TEMP_AMMO_AMOUNT && varpId != VarPlayerID.DIZANAS_QUIVER_TEMP_AMMO)
		{
			return;
		}

		int oldAmmoId = quiverAmmoId;
		int oldAmmoCount = quiverAmmoCount;
		quiverAmmoId = plugin.client.getVarpValue(VarPlayerID.DIZANAS_QUIVER_TEMP_AMMO);
		quiverAmmoCount = plugin.client.getVarpValue(VarPlayerID.DIZANAS_QUIVER_TEMP_AMMO_AMOUNT);

		if (quiverAmmoId < 0 && oldAmmoId > 0) {
			// ammo is now missing, either deposited into bank/withdraw into inv, or last arrows fired
			if (oldAmmoCount > 0 && oldAmmoCount <= 2) { // assume arrow fired if was only 1-2 ammo (dark bow can fire 2 at a time)
				plugin.buildEntries(oldAmmoId, oldAmmoCount);
				// there is a bug where withdrawing a 1-2 ammo from quiver will track as being fired, uncommon wontfix
			}
		} else if (quiverAmmoId >= 0 && quiverAmmoId == oldAmmoId) {
			int countChange = oldAmmoCount - quiverAmmoCount;
			if (countChange >= 0 && countChange <= 2) {
				plugin.buildEntries(quiverAmmoId, countChange);
			}
		}
	}
}

/*
 * Copyright (c) 2018, Daddy Dozer <Whitedylan7@gmail.com>
 * Copyright (c) 2018, Davis Cook <daviscook447@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *	list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *	this list of conditions and the following disclaimer in the documentation
 *	and/or other materials provided with the distribution.
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