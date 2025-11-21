package net.runelite.client.plugins.pluginhub.com.levelupquestreqs;

import net.runelite.client.plugins.pluginhub.com.levelupquestreqs.testquests.MultiSkillReqQuest;
import net.runelite.api.Skill;
import org.junit.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MultiSkillQuestTests
{
    private static Map<Skill, Integer> skills;

    static
    {
        skills = new EnumMap<>(Skill.class);
        for (Skill skill : Skill.values())
        {
            if (skill.getName() == "Hitpoints") skills.put(skill, 10);
            else skills.put(skill, 1);
        }
    }

    private static MultiSkillReqQuest multiSkillReqQuest = new MultiSkillReqQuest();

    @Test
    public void hasSkillAsRequirement_GivenIncorrectSkill_ReturnsFalse()
    {
        multiSkillReqQuest = new MultiSkillReqQuest();

        assertFalse(multiSkillReqQuest.hasSkillAsRequirement(Skill.AGILITY));
    }

    @Test
    public void hasSkillAsRequirement_GivenFirstCorrectSkill_ReturnsTrue()
    {
        multiSkillReqQuest = new MultiSkillReqQuest();

        assertTrue(multiSkillReqQuest.hasSkillAsRequirement(Skill.MINING));
    }

    @Test
    public void hasSkillAsRequirement_GivenSecondCorrectSkill_ReturnsTrue()
    {
        multiSkillReqQuest = new MultiSkillReqQuest();

        assertTrue(multiSkillReqQuest.hasSkillAsRequirement(Skill.RUNECRAFT));
    }

    @Test
    public void newSkillLevelMeetsRequirements_GivenCorrectFirstSkill_AndCorrectRequirement_ButSecondRequirementIsNotMet_ReturnsFalse()
    {
        multiSkillReqQuest = new MultiSkillReqQuest();

        assertFalse(multiSkillReqQuest.newSkillLevelMeetsRequirements(skills, Skill.MINING, 20));
    }

    @Test
    public void newSkillLevelMeetsRequirements_GivenCorrectFirstSkill_AndCorrectRequirement_AndSecondSkillMatchesRequirement_ReturnsTrue()
    {
        multiSkillReqQuest = new MultiSkillReqQuest();

        Map<Skill, Integer> adjustedSkills = skills;
        adjustedSkills.replace(Skill.RUNECRAFT, 45);

        assertTrue(multiSkillReqQuest.newSkillLevelMeetsRequirements(skills, Skill.MINING, 20));
    }

    @Test
    public void newSkillLevelMeetsRequirements_GivenCorrectFirstSkill_AndCorrectRequirement_AndSecondSkillExceedsRequirement_ReturnsTrue()
    {
        multiSkillReqQuest = new MultiSkillReqQuest();

        Map<Skill, Integer> adjustedSkills = skills;
        adjustedSkills.replace(Skill.RUNECRAFT, 99);

        assertTrue(multiSkillReqQuest.newSkillLevelMeetsRequirements(skills, Skill.MINING, 20));
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