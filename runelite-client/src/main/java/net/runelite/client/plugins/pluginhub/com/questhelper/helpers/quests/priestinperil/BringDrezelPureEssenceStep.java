package net.runelite.client.plugins.pluginhub.com.questhelper.helpers.quests.priestinperil;

import net.runelite.client.plugins.pluginhub.com.questhelper.questhelpers.QuestHelper;
import net.runelite.client.plugins.pluginhub.com.questhelper.requirements.item.ItemRequirement;
import net.runelite.client.plugins.pluginhub.com.questhelper.steps.NpcStep;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.NpcID;
import net.runelite.client.eventbus.Subscribe;

import java.util.Collections;

public class BringDrezelPureEssenceStep extends NpcStep
{
	ItemRequirement essence = new ItemRequirement("Rune/Pure essence",ItemID.BLANKRUNE, 50);

	public BringDrezelPureEssenceStep(QuestHelper questHelper)
	{
		super(questHelper, NpcID.PRIESTPERILTRAPPEDMONK_VIS, new WorldPoint(3439, 9896, 0), "Bring Drezel 50 UNNOTED rune/pure essence in the underground of the Salve Temple to finish the quest!");
		essence.addAlternates(ItemID.BLANKRUNE_HIGH);
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		updateSteps();
	}

	protected void updateSteps()
	{
		int numEssence = 60 - client.getVarpValue(302);
		essence.setQuantity(numEssence);
		this.setRequirements(Collections.singletonList(essence));
		this.setText("Bring Drezel " + numEssence  + " UNNOTED rune/pure essence in the underground of the Salve Temple.");
	}
}

/*
 * Copyright (c) 2024, Zoinkwiz <https://github.com/Zoinkwiz>
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