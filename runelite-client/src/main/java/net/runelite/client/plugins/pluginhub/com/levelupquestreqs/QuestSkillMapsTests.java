package net.runelite.client.plugins.pluginhub.com.levelupquestreqs;

import net.runelite.client.plugins.pluginhub.com.levelupquestreqs.questmaps.QuestSkillMaps;
import net.runelite.client.plugins.pluginhub.com.levelupquestreqs.testquestskillmaps.AKingdomDividedMap;
import net.runelite.client.plugins.pluginhub.com.levelupquestreqs.testquestskillmaps.LandOfTheGoblinsMap;
import net.runelite.client.plugins.pluginhub.com.levelupquestreqs.testquestskillmaps.TestQuestSkillMap;
import net.runelite.api.Quest;
import net.runelite.api.Skill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class QuestSkillMapsTests
{
    private Map<Skill, Integer> previousSkills;
    private Skill levelledSkill;
    private Integer newLevel;
    private Quest expectedQuest;

    private QuestSkillMaps questSkillMaps;
    private final LevelUpQuestReqsPlugin plugin = new LevelUpQuestReqsPlugin();

    public QuestSkillMapsTests(TestQuestSkillMap testData)
    {
        previousSkills = testData.previousSkills;
        levelledSkill = testData.skillLevelled;
        newLevel = testData.newLevel;
        expectedQuest = testData.questExpected;
        questSkillMaps = new QuestSkillMaps(plugin);
    }

    @Parameterized.Parameters
    public static Collection testData()
    {
        List<TestQuestSkillMap> parameterList = new ArrayList<>();

        parameterList.add(new AKingdomDividedMap(Skill.AGILITY, 54));
        parameterList.add(new AKingdomDividedMap(Skill.THIEVING, 52));
        parameterList.add(new AKingdomDividedMap(Skill.WOODCUTTING, 52));
        parameterList.add(new AKingdomDividedMap(Skill.HERBLORE, 50));
        parameterList.add(new AKingdomDividedMap(Skill.MINING, 42));
        parameterList.add(new AKingdomDividedMap(Skill.CRAFTING, 38));
        parameterList.add(new AKingdomDividedMap(Skill.MAGIC, 35));
        parameterList.add(new LandOfTheGoblinsMap(Skill.AGILITY, 38));
        parameterList.add(new LandOfTheGoblinsMap(Skill.FISHING, 40));
        parameterList.add(new LandOfTheGoblinsMap(Skill.THIEVING, 45));
        parameterList.add(new LandOfTheGoblinsMap(Skill.HERBLORE, 48));
        parameterList.add(new TestQuestSkillMap(Skill.HERBLORE, 3, Quest.JUNGLE_POTION));

        parameterList.add(new TestQuestSkillMap(Skill.AGILITY, 2, null));

        return parameterList;
    }

    @Test
    public void validateQuestSkillMaps()
    {
        if (expectedQuest == null)
            assertTrue(questSkillMaps.getQuestsFulfilledByLevel(previousSkills, levelledSkill, newLevel).isEmpty());
        else
        {
            if (!plugin.getIncompleteQuestsList().contains(expectedQuest))
                plugin.getIncompleteQuestsList().add(expectedQuest);

            assertTrue(questSkillMaps.getQuestsFulfilledByLevel(previousSkills, levelledSkill, newLevel).contains(expectedQuest));
        }
    }

    @Test
    public void validQuestSkillMapsWithNoIncompleteQuests()
    {
        assertTrue(questSkillMaps.getQuestsFulfilledByLevel(previousSkills, levelledSkill, newLevel).isEmpty());
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