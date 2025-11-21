package net.runelite.client.plugins.pluginhub.com.levelupquestreqs.questmaps;

import net.runelite.api.Quest;

import java.util.ArrayList;
import java.util.List;

public class IgnoredQuests
{
    private static final List<Quest> ignoredQuests = new ArrayList<>();

    public static boolean isIgnored(Quest quest)
    {
        return ignoredQuests.contains(quest);
    }

    public static void initialise()
    {
        ignoredQuests.clear();

        // No hard skill requirements
        ignoredQuests.add(Quest.COOKS_ASSISTANT);
        ignoredQuests.add(Quest.DEMON_SLAYER);
        ignoredQuests.add(Quest.THE_RESTLESS_GHOST);
        ignoredQuests.add(Quest.ROMEO__JULIET);
        ignoredQuests.add(Quest.SHEEP_SHEARER);
        ignoredQuests.add(Quest.SHIELD_OF_ARRAV);
        ignoredQuests.add(Quest.ERNEST_THE_CHICKEN);
        ignoredQuests.add(Quest.VAMPYRE_SLAYER);
        ignoredQuests.add(Quest.IMP_CATCHER);
        ignoredQuests.add(Quest.PRINCE_ALI_RESCUE);
        ignoredQuests.add(Quest.DORICS_QUEST);
        ignoredQuests.add(Quest.WITCHS_POTION);
        ignoredQuests.add(Quest.GOBLIN_DIPLOMACY);
        ignoredQuests.add(Quest.PIRATES_TREASURE);
        ignoredQuests.add(Quest.RUNE_MYSTERIES);
        ignoredQuests.add(Quest.MISTHALIN_MYSTERY);
        ignoredQuests.add(Quest.THE_CORSAIR_CURSE);
        ignoredQuests.add(Quest.X_MARKS_THE_SPOT);

        ignoredQuests.add(Quest.DRUIDIC_RITUAL);
        ignoredQuests.add(Quest.WITCHS_HOUSE);
        ignoredQuests.add(Quest.MERLINS_CRYSTAL);
        ignoredQuests.add(Quest.MONKS_FRIEND);
        ignoredQuests.add(Quest.CLOCK_TOWER);
        ignoredQuests.add(Quest.TREE_GNOME_VILLAGE);
        ignoredQuests.add(Quest.FIGHT_ARENA);
        ignoredQuests.add(Quest.HAZEEL_CULT);
        ignoredQuests.add(Quest.SHEEP_HERDER);
        ignoredQuests.add(Quest.PLAGUE_CITY);
        ignoredQuests.add(Quest.WATERFALL_QUEST);
        ignoredQuests.add(Quest.BIOHAZARD);
        ignoredQuests.add(Quest.OBSERVATORY_QUEST);
        ignoredQuests.add(Quest.DWARF_CANNON);
        ignoredQuests.add(Quest.MURDER_MYSTERY);
        ignoredQuests.add(Quest.GERTRUDES_CAT);
        ignoredQuests.add(Quest.PRIEST_IN_PERIL);
        ignoredQuests.add(Quest.NATURE_SPIRIT);
        ignoredQuests.add(Quest.DEATH_PLATEAU);
        ignoredQuests.add(Quest.THRONE_OF_MISCELLANIA);
        ignoredQuests.add(Quest.MONKEY_MADNESS_I);
        ignoredQuests.add(Quest.ROVING_ELVES);
        ignoredQuests.add(Quest.ICTHLARINS_LITTLE_HELPER);
        ignoredQuests.add(Quest.RECRUITMENT_DRIVE);
        ignoredQuests.add(Quest.A_TAIL_OF_TWO_CATS);
        ignoredQuests.add(Quest.MOURNINGS_END_PART_II);
        ignoredQuests.add(Quest.MAKING_HISTORY);
        ignoredQuests.add(Quest.RATCATCHERS);
        ignoredQuests.add(Quest.FAIRYTALE_I__GROWING_PAINS);
        ignoredQuests.add(Quest.A_SOULS_BANE);
        ignoredQuests.add(Quest.RAG_AND_BONE_MAN_I);
        ignoredQuests.add(Quest.CONTACT);
        ignoredQuests.add(Quest.CLIENT_OF_KOUREND);
        ignoredQuests.add(Quest.BONE_VOYAGE);
        ignoredQuests.add(Quest.THE_FORSAKEN_TOWER);
        ignoredQuests.add(Quest.A_PORCINE_OF_INTEREST);
        ignoredQuests.add(Quest.A_NIGHT_AT_THE_THEATRE);
        ignoredQuests.add(Quest.ALFRED_GRIMHANDS_BARCRAWL);
        ignoredQuests.add(Quest.BEAR_YOUR_SOUL);
        ignoredQuests.add(Quest.DADDYS_HOME);
        ignoredQuests.add(Quest.THE_ENCHANTED_KEY);
        ignoredQuests.add(Quest.ENTER_THE_ABYSS);
        ignoredQuests.add(Quest.FAMILY_PEST);
        ignoredQuests.add(Quest.THE_GENERALS_SHADOW);
        ignoredQuests.add(Quest.IN_SEARCH_OF_KNOWLEDGE);
        ignoredQuests.add(Quest.INTO_THE_TOMBS);
        ignoredQuests.add(Quest.CHILDREN_OF_THE_SUN);
        ignoredQuests.add(Quest.HIS_FAITHFUL_SERVANTS);

        // Only QP requirements (might be able to add these later?)
        ignoredQuests.add(Quest.BLACK_KNIGHTS_FORTRESS);
        ignoredQuests.add(Quest.DRAGON_SLAYER_I);
        ignoredQuests.add(Quest.BELOW_ICE_MOUNTAIN);
        ignoredQuests.add(Quest.WANTED);

        // Combat level requirement (might be able to add later?)
        ignoredQuests.add(Quest.DREAM_MENTOR);
    }
}

/*
 * Copyright (c) 2023, Hoffi Coffi
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