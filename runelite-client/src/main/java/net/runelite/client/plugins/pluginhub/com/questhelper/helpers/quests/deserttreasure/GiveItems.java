package net.runelite.client.plugins.pluginhub.com.questhelper.helpers.quests.deserttreasure;

import net.runelite.client.plugins.pluginhub.com.questhelper.questhelpers.QuestHelper;
import net.runelite.client.plugins.pluginhub.com.questhelper.requirements.item.ItemRequirement;
import net.runelite.client.plugins.pluginhub.com.questhelper.steps.NpcStep;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.VarbitChanged;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.VarbitID;

public class GiveItems extends NpcStep
{
	ItemRequirement magicLogs, steelBars, moltenGlass, ashes, charcoal, bloodRune, bones;

	public GiveItems(QuestHelper questHelper, int npcID, WorldPoint worldPoint, String text, ItemRequirement... itemRequirements)
	{
		super(questHelper, npcID, worldPoint, text, itemRequirements);
		magicLogs = new ItemRequirement("Magic logs", ItemID.MAGIC_LOGS, 12);
		magicLogs.addAlternates(ItemID.Cert.MAGIC_LOGS);
		steelBars = new ItemRequirement("Steel bar", ItemID.STEEL_BAR,  6);
		steelBars.addAlternates(ItemID.Cert.STEEL_BAR);
		moltenGlass = new ItemRequirement("Molten glass", ItemID.MOLTEN_GLASS, 6);
		moltenGlass.addAlternates(ItemID.Cert.MOLTEN_GLASS);
		ashes = new ItemRequirement("Ashes", ItemID.ASHES);
		charcoal = new ItemRequirement("Charcoal", ItemID.CHARCOAL);
		bloodRune = new ItemRequirement("Blood rune", ItemID.BLOODRUNE);
		bones = new ItemRequirement("Bones", ItemID.BONES);
	}

	@Override
	public void startUp()
	{
		super.startUp();
		itemQuantitiesLeft();
	}

	@Override
	public void onVarbitChanged(VarbitChanged varbitChanged)
	{
		super.onVarbitChanged(varbitChanged);
		itemQuantitiesLeft();
	}

	private void itemQuantitiesLeft()
	{
		emptyRequirements();
		if (client.getVarbitValue(VarbitID.FD_BONES) != 1)
		{
			addRequirement(bones);
		}
		if (client.getVarbitValue(VarbitID.FD_BLOODRUNE) != 1)
		{
			addRequirement(bloodRune);
		}
		if (client.getVarbitValue(VarbitID.FD_ASH) != 1)
		{
			addRequirement(ashes);
		}
		if (client.getVarbitValue(VarbitID.FD_CHARCOAL) != 1)
		{
			addRequirement(charcoal);
		}
		if (client.getVarbitValue(VarbitID.FD_MAGICLOG) != 12)
		{
			magicLogs.setQuantity(12 - client.getVarbitValue(VarbitID.FD_MAGICLOG));
			addRequirement(magicLogs);
		}
		if (client.getVarbitValue(VarbitID.FD_STEELBAR) != 6)
		{
			steelBars.setQuantity(6 - client.getVarbitValue(VarbitID.FD_STEELBAR));
			addRequirement(steelBars);
		}
		if (client.getVarbitValue(VarbitID.FD_GLASS) != 6)
		{
			moltenGlass.setQuantity(6 - client.getVarbitValue(VarbitID.FD_GLASS));
			addRequirement(moltenGlass);
		}
		if (getRequirements().isEmpty())
		{
			setText("Talk to Eblis in the east of the Bandit Camp.");
		}
	}
}

/*
 * Copyright (c) 2019, Trevor <https://github.com/Trevor159>
 * Copyright (c) 2020, Zoinkwiz <https://github.com/Zoinkwiz>
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