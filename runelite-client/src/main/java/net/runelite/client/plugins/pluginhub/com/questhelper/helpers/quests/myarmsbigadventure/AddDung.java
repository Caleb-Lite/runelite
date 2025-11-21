package net.runelite.client.plugins.pluginhub.com.questhelper.helpers.quests.myarmsbigadventure;

import net.runelite.client.plugins.pluginhub.com.questhelper.questhelpers.QuestHelper;
import net.runelite.client.plugins.pluginhub.com.questhelper.requirements.item.ItemRequirement;
import net.runelite.client.plugins.pluginhub.com.questhelper.steps.ObjectStep;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.ObjectID;
import net.runelite.api.gameval.VarbitID;
import net.runelite.client.eventbus.Subscribe;

import java.util.Arrays;

public class AddDung extends ObjectStep
{
	ItemRequirement dung = new ItemRequirement("Ugthanki dung", ItemID.FEUD_CAMEL_POOH_BUCKET, 3);
	ItemRequirement spade = new ItemRequirement("Spade", ItemID.SPADE);

	public AddDung(QuestHelper questHelper)
	{
		super(questHelper, ObjectID.MYARM_FAKEFARMINGPATCH, new WorldPoint(2831, 3696, 0), "Add 3 ugthanki dung on My Arm's soil patch.");
		this.addIcon(ItemID.FEUD_CAMEL_POOH_BUCKET);
		dung.setTooltip("You can get some by feeding the camels in Pollnivneach hot sauce, then using a bucket on their dung");
		dung.setHighlightInInventory(true);
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		updateSteps();
	}

	protected void updateSteps()
	{
		int numCompToAdd = 3 - client.getVarbitValue(VarbitID.MYARM_DUNG);
		dung.setQuantity(numCompToAdd);
		this.setRequirements(Arrays.asList(dung, spade));
		this.setText("Add " + numCompToAdd + " ugthanki dung on My Arm's soil patch.");
	}
}


/*
 * Copyright (c) 2020, Zoinkwiz
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