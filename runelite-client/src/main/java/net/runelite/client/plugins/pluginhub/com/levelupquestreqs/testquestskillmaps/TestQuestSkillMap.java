package net.runelite.client.plugins.pluginhub.com.levelupquestreqs.testquestskillmaps;

import net.runelite.api.Quest;
import net.runelite.api.Skill;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class TestQuestSkillMap
{
    public Map<Skill, Integer> previousSkills;
    public Skill skillLevelled;
    public Integer newLevel;
    public Quest questExpected;

    public TestQuestSkillMap(Map<Skill, Integer> previousSkills, Skill skillLevelled, Integer newLevel, Quest questExpected)
    {
        this.previousSkills = previousSkills;
        this.skillLevelled = skillLevelled;
        this.newLevel = newLevel;
        this.questExpected = questExpected;
    }

    public TestQuestSkillMap(Skill skillLevelled, Integer newLevel, Quest questExpected)
    {
        this.previousSkills = getBaseSkillMap();
        this.skillLevelled = skillLevelled;
        this.newLevel = newLevel;
        this.questExpected = questExpected;
    }

    public TestQuestSkillMap()
    {
    }

    protected static Map<Skill, Integer> getBaseSkillMap()
    {
        return Arrays.stream(Skill.values()).collect(Collectors.toMap(skill -> skill, skill -> skill.getName() == "Hitpoints" ? 10 : 1));
    }

    protected void replaceSkill(Skill skill, Integer newLevel)
    {
        if (skillLevelled != skill) previousSkills.replace(skill, newLevel);
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