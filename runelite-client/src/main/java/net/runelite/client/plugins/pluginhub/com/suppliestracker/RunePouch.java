package net.runelite.client.plugins.pluginhub.com.suppliestracker;

import net.runelite.client.plugins.pluginhub.com.suppliestracker.Skills.XpDropTracker;
import java.util.Arrays;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.runelite.api.Skill;
import net.runelite.api.gameval.VarbitID;

@Singleton
public class RunePouch
{
	private final SuppliesTrackerPlugin plugin;

	//Rune pouch stuff
	private static final int[] AMOUNT_VARBITS =
		{
			VarbitID.RUNE_POUCH_QUANTITY_1, VarbitID.RUNE_POUCH_QUANTITY_2,
			VarbitID.RUNE_POUCH_QUANTITY_3, VarbitID.RUNE_POUCH_QUANTITY_4
		};
	private static final int[] RUNE_VARBITS =
		{
			VarbitID.RUNE_POUCH_TYPE_1, VarbitID.RUNE_POUCH_TYPE_2,
			VarbitID.RUNE_POUCH_TYPE_3, VarbitID.RUNE_POUCH_TYPE_4
		};

	private final int[] pouchAmount;
	private final int[] pouchRuneIds;
	private final int[] prevPouchRuneIds;
	private final int[] amounts_used;

	@Inject
	RunePouch(SuppliesTrackerPlugin plugin) {
		this.plugin = plugin;
		pouchAmount = new int[RUNE_VARBITS.length];
		pouchRuneIds = new int[RUNE_VARBITS.length];
		prevPouchRuneIds = new int[RUNE_VARBITS.length];
		amounts_used = new int[RUNE_VARBITS.length];
	}

	/**
	 * Checks local variable data against client data then returns differences then updates local to client
	 */
	void updateVarbit(int varbitId)
	{
		for (int i = 0; i < RUNE_VARBITS.length; i++) {
			if (RUNE_VARBITS[i] == varbitId) {
				prevPouchRuneIds[i] = pouchRuneIds[i];
				pouchRuneIds[i] = plugin.client.getVarbitValue(RUNE_VARBITS[i]);
				return;
			}
		}

		for (int i = 0; i < AMOUNT_VARBITS.length; i++) {
			if (AMOUNT_VARBITS[i] == varbitId) {
				int newAmount = plugin.client.getVarbitValue(AMOUNT_VARBITS[i]);
				if (newAmount < pouchAmount[i])
				{
					amounts_used[i] += pouchAmount[i] - newAmount;
				}
				pouchAmount[i] = newAmount;
				return;
			}
		}
	}

	void checkUsedRunePouch(XpDropTracker xpDropTracker, boolean noXpCast)
	{
		if (!xpDropTracker.hadXpThisTick(Skill.MAGIC) && !noXpCast) {
			return;
		}
		for (int i = 0; i < pouchRuneIds.length; i++) {
			if (amounts_used[i] != 0 && amounts_used[i] < 20)
			{
				if (pouchRuneIds[i] == 0) {
					// special case when last runes used from pouch
					plugin.buildEntries(Runes.getRune(prevPouchRuneIds[i]).getItemId(), amounts_used[i]);
				} else {
					plugin.buildEntries(Runes.getRune(pouchRuneIds[i]).getItemId(), amounts_used[i]);
				}
			}
		}
	}

	public void resetAmountUsed()
	{
		Arrays.fill(amounts_used, 0);
	}
}

/*
 * Copyright (c) 2022, Patrick <https://github.com/pwatts6060>
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