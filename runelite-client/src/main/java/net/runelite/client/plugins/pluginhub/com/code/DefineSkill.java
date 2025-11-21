package net.runelite.client.plugins.pluginhub.com.code;

import net.runelite.api.Skill;

public class DefineSkill {
    public static Skill defineSkill(String skillName) {
        Skill skill;
        if(skillName.equalsIgnoreCase("Agility"))
        {
            skill = Skill.AGILITY;
        }
        else if(skillName.equalsIgnoreCase("Runecrafting"))
        {
            skill = Skill.RUNECRAFT;
        }
        else if(skillName.equalsIgnoreCase("Smithing"))
        {
            skill = Skill.SMITHING;
        }
        else if(skillName.equalsIgnoreCase("Prayer"))
        {
            skill = Skill.PRAYER;
        }
        else if(skillName.equalsIgnoreCase("Mining"))
        {
            skill = Skill.MINING;
        }
        else if(skillName.equalsIgnoreCase("Hunter"))
        {
            skill = Skill.HUNTER;
        }
        else if(skillName.equalsIgnoreCase("Cooking"))
        {
            skill = Skill.COOKING;
        }
        else if(skillName.equalsIgnoreCase("Herblore"))
        {
            skill = Skill.HERBLORE;
        }
        else if(skillName.equalsIgnoreCase("Fletching"))
        {
            skill = Skill.FLETCHING;
        }
        else if (skillName.equalsIgnoreCase("Fishing"))
        {
            skill = Skill.FISHING;
        }
        else if(skillName.equalsIgnoreCase("Construction"))
        {
            skill = Skill.CONSTRUCTION;
        }
        else if(skillName.equalsIgnoreCase("Woodcutting"))
        {
            skill = Skill.WOODCUTTING;
        }
        else if(skillName.equalsIgnoreCase("Thieving"))
        {
            skill = Skill.THIEVING;
        }
        else if(skillName.equalsIgnoreCase("Firemaking"))
        {
            skill = Skill.FIREMAKING;
        }
        else if(skillName.equalsIgnoreCase("Crafting"))
        {
            skill = Skill.CRAFTING;
        }
        else
        {
            skill = Skill.HITPOINTS; // something random to avoid errors shouldnt matter anyway
        }
        return skill;

    }
}


/*
 * Copyright (c) 2021, MakingStan
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