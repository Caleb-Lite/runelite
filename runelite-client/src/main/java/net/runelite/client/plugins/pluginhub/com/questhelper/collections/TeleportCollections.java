package net.runelite.client.plugins.pluginhub.com.questhelper.collections;

import net.runelite.client.plugins.pluginhub.com.questhelper.requirements.item.ItemRequirement;
import net.runelite.client.plugins.pluginhub.com.questhelper.requirements.item.ItemRequirements;
import net.runelite.client.plugins.pluginhub.com.questhelper.requirements.item.TeleportItemRequirement;
import net.runelite.client.plugins.pluginhub.com.questhelper.requirements.util.LogicType;
import net.runelite.api.gameval.ItemID;

public enum TeleportCollections
{
	BURTHORPE_TELEPORT()
		{
			public ItemRequirement getItemRequirement()
			{
				TeleportItemRequirement burthTele = new TeleportItemRequirement("Teleport to Burthorpe. Games necklace (Burthorpe [1]), minigame teleport (Burthorpe Games Room)",
					ItemCollections.GAMES_NECKLACES);
				return burthTele;
			}
		},
	VARROCK_TELEPORT()
		{
			public ItemRequirement getItemRequirement()
			{
				TeleportItemRequirement varrockTele = new TeleportItemRequirement("Teleport to Varrock. Varrock teleport tablet/spell, Chronicle, Ring of Wealth (Grand Exchange [2])",
					ItemID.POH_TABLET_VARROCKTELEPORT);
				varrockTele.addAlternates(ItemID.CHRONICLE);
				varrockTele.addAlternates(ItemCollections.RING_OF_WEALTHS);

				ItemRequirement varrockRunes = new ItemRequirements("Varrock teleport runes",
					new ItemRequirement("Law rune", ItemID.LAWRUNE, 1),
					new ItemRequirement("Air rune", ItemID.AIRRUNE, 3),
					new ItemRequirement("Water rune", ItemID.WATERRUNE, 1)
				);
				return new ItemRequirements(LogicType.OR, "Teleport to Varrock. Varrock teleport tablet/spell, Chronicle, Ring of Wealth (Grand Exchange [2])",
					varrockTele, varrockRunes);
			}
		},
	SOPHANEM_TELEPORT()
		{
			public ItemRequirement getItemRequirement()
			{
				TeleportItemRequirement sophTele = new TeleportItemRequirement("Teleport to Sophanem. Pharaoh's sceptre (Jalsavrah [1])",
					ItemCollections.PHAROAH_SCEPTRE);
				return sophTele;
			}
		},
	ARDOUGNE_TELEPORT()
		{
			@Override
			public ItemRequirement getItemRequirement()
			{
				TeleportItemRequirement ardougneTele = new TeleportItemRequirement("Teleport to Ardougne. Ardougne cloak, Ardougne teleport tablet/spell",
					ItemCollections.ARDY_CLOAKS);
				ardougneTele.addAlternates(ItemID.POH_TABLET_ARDOUGNETELEPORT);

				ItemRequirement ardougneRunes = new ItemRequirements("Ardougne teleport runes",
					new ItemRequirement("Law rune", ItemID.LAWRUNE, 2),
					new ItemRequirement("Water rune", ItemID.WATERRUNE, 2)
				);
				return new ItemRequirements(LogicType.OR, "Teleport to Ardougne. Ardougne cloak, Ardougne teleport tablet/spell", ardougneTele, ardougneRunes);
			}
		},
	FALADOR_TELEPORT()
		{
			@Override
			public ItemRequirement getItemRequirement()
			{
				TeleportItemRequirement faladorTele = new TeleportItemRequirement("Teleport to Falador. Falador teleport tablet/spell",
					ItemID.POH_TABLET_FALADORTELEPORT);

				ItemRequirement faladorRunes = new ItemRequirements("Falador teleport runes",
					new ItemRequirement("Law rune", ItemID.LAWRUNE, 1),
					new ItemRequirement("Air rune", ItemID.AIRRUNE, 3),
					new ItemRequirement("Water rune", ItemID.WATERRUNE, 1)
				);

				return new ItemRequirements(LogicType.OR, "Teleport to Falador. Falador teleport tablet/spell", faladorTele, faladorRunes);
			}
		};

	public abstract ItemRequirement getItemRequirement();
}

/*
 * Copyright (c) 2023, pajlada <https://github.com/pajlada>
 * Copyright (c) 2017, Seth <Sethtroll3@gmail.com>
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