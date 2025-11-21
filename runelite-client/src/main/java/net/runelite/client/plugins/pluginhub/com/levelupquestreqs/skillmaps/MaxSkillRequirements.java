package net.runelite.client.plugins.pluginhub.com.levelupquestreqs.skillmaps;

import net.runelite.api.Skill;

import java.util.EnumMap;
import java.util.Map;

public class MaxSkillRequirements
{
    private static final Map<Skill, Integer> maxSkillReqs = new EnumMap(Skill.class);

    // Initialise with the maximum level each skill is utilised as a quest requirement
    public static void initialise()
    {
        maxSkillReqs.put(Skill.ATTACK, 50);
        maxSkillReqs.put(Skill.HITPOINTS, 50);
        maxSkillReqs.put(Skill.MINING, 72);
        maxSkillReqs.put(Skill.STRENGTH, 50);
        maxSkillReqs.put(Skill.AGILITY, 70);
        maxSkillReqs.put(Skill.SMITHING, 70);
        maxSkillReqs.put(Skill.DEFENCE, 65);
        maxSkillReqs.put(Skill.HERBLORE, 70);
        maxSkillReqs.put(Skill.FISHING, 62);
        maxSkillReqs.put(Skill.RANGED, 60);
        maxSkillReqs.put(Skill.THIEVING, 60);
        maxSkillReqs.put(Skill.COOKING, 70);
        maxSkillReqs.put(Skill.PRAYER, 50);
        maxSkillReqs.put(Skill.CRAFTING, 70);
        maxSkillReqs.put(Skill.FIREMAKING, 66);
        maxSkillReqs.put(Skill.MAGIC, 75);
        maxSkillReqs.put(Skill.FLETCHING, 60);
        maxSkillReqs.put(Skill.WOODCUTTING, 71);
        maxSkillReqs.put(Skill.RUNECRAFT, 55);
        maxSkillReqs.put(Skill.SLAYER, 69); // nice
        maxSkillReqs.put(Skill.FARMING, 70);
        maxSkillReqs.put(Skill.CONSTRUCTION, 70);
        maxSkillReqs.put(Skill.HUNTER, 70);
    }

    public static Integer getMaxLevelForSkill(Skill skill)
    {
        return maxSkillReqs.getOrDefault(skill, 99);
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