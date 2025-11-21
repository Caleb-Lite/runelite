package net.runelite.client.plugins.pluginhub.com.levelupquestreqs.questmaps;

import net.runelite.client.plugins.pluginhub.com.levelupquestreqs.LevelUpQuestReqsPlugin;
import com.levelupquestreqs.questmaps.quests.*;
import net.runelite.api.Quest;
import net.runelite.api.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuestSkillMaps
{
    private final List<BaseQuestSkillMap> questSkillMaps = new ArrayList<>();

    private final LevelUpQuestReqsPlugin plugin;

    public QuestSkillMaps(LevelUpQuestReqsPlugin plugin)
    {
        this.plugin = plugin;

        questSkillMaps.add(new AKingdomDivided());
        questSkillMaps.add(new AnimalMagnetism());
        questSkillMaps.add(new AnotherSliceOfHAM());
        questSkillMaps.add(new ATasteOfHope());
        questSkillMaps.add(new BeneathCursedSands());
        questSkillMaps.add(new BetweenARock());
        questSkillMaps.add(new BigChompyBirdHunting());
        questSkillMaps.add(new CabinFever());
        questSkillMaps.add(new ColdWar());
        questSkillMaps.add(new CreatureOfFenkenstrain());
        questSkillMaps.add(new CurseOfTheEmptyLord());
        questSkillMaps.add(new DarknessOfHallowvale());
        questSkillMaps.add(new DeathToTheDorgeshuun());
        questSkillMaps.add(new DesertTreasure());
        questSkillMaps.add(new DeviousMinds());
        questSkillMaps.add(new DragonSlayerII());
        questSkillMaps.add(new EadgarsRuse());
        questSkillMaps.add(new EaglesPeak());
        questSkillMaps.add(new ElementalWorkshopI());
        questSkillMaps.add(new ElementalWorkshopII());
        questSkillMaps.add(new EnakhrasLament());
        questSkillMaps.add(new EnlightenedJourney());
        questSkillMaps.add(new FairytaleII());
        questSkillMaps.add(new FamilyCrest());
        questSkillMaps.add(new FishingContest());
        questSkillMaps.add(new ForgettableTale());
        questSkillMaps.add(new GardenOfTranquility());
        questSkillMaps.add(new GettingAhead());
        questSkillMaps.add(new GhostsAhoy());
        questSkillMaps.add(new GrimTales());
        questSkillMaps.add(new HauntedMine());
        questSkillMaps.add(new HeroesQuest());
        questSkillMaps.add(new HolyGrail());
        questSkillMaps.add(new HopespearsWill());
        questSkillMaps.add(new HorrorFromTheDeep());
        questSkillMaps.add(new InAidOfTheMyreque());
        questSkillMaps.add(new InSearchOfTheMyreque());
        questSkillMaps.add(new JunglePotion());
        questSkillMaps.add(new KingsRansom());
        questSkillMaps.add(new LairOfTarnRazorlor());
        questSkillMaps.add(new LandOfTheGoblins());
        questSkillMaps.add(new LegendsQuest());
        questSkillMaps.add(new LostCity());
        questSkillMaps.add(new LunarDiplomacy());
        questSkillMaps.add(new MageArenaI());
        questSkillMaps.add(new MageArenaII());
        questSkillMaps.add(new MakingFriendsWithMyArm());
        questSkillMaps.add(new MonkeyMadnessII());
        questSkillMaps.add(new MountainDaughter());
        questSkillMaps.add(new MourningsEndPartI());
        questSkillMaps.add(new MyArmsBigAdventure());
        questSkillMaps.add(new OlafsQuest());
        questSkillMaps.add(new OneSmallFavour());
        questSkillMaps.add(new RagAndBoneManII());
        questSkillMaps.add(new Regicide());
        questSkillMaps.add(new RoyalTrouble());
        questSkillMaps.add(new RumDeal());
        questSkillMaps.add(new ScorpionCatcher());
        questSkillMaps.add(new SeaSlug());
        questSkillMaps.add(new ShadesOfMortton());
        questSkillMaps.add(new ShadowOfTheStorm());
        questSkillMaps.add(new ShiloVillage());
        questSkillMaps.add(new SinsOfTheFather());
        questSkillMaps.add(new SkippyAndTheMogres());
        questSkillMaps.add(new SleepingGiants());
        questSkillMaps.add(new SongOfTheElves());
        questSkillMaps.add(new SpiritsOfTheElid());
        questSkillMaps.add(new SwanSong());
        questSkillMaps.add(new TaiBwoWannaiTrio());
        questSkillMaps.add(new TaleOfTheRighteous());
        questSkillMaps.add(new TearsOfGuthix());
        questSkillMaps.add(new TempleOfIkov());
        questSkillMaps.add(new TempleOfTheEye());
        questSkillMaps.add(new TheAscentOfArceuus());
        questSkillMaps.add(new TheDepthsOfDespair());
        questSkillMaps.add(new TheDigSite());
        questSkillMaps.add(new TheEyesOfGlouphrie());
        questSkillMaps.add(new TheFeud());
        questSkillMaps.add(new TheFremennikExiles());
        questSkillMaps.add(new TheFremennikIsles());
        questSkillMaps.add(new TheFremennikTrials());
        questSkillMaps.add(new TheFrozenDoor());
        questSkillMaps.add(new TheGardenOfDeath());
        questSkillMaps.add(new TheGiantDwarf());
        questSkillMaps.add(new TheGolem());
        questSkillMaps.add(new TheGrandTree());
        questSkillMaps.add(new TheGreatBrainRobbery());
        questSkillMaps.add(new TheHandInTheSand());
        questSkillMaps.add(new TheKnightsSword());
        questSkillMaps.add(new TheLostTribe());
        questSkillMaps.add(new TheQueenOfThieves());
        questSkillMaps.add(new TheSlugMenace());
        questSkillMaps.add(new TheTouristTrap());
        questSkillMaps.add(new TowerOfLife());
        questSkillMaps.add(new TribalTotem());
        questSkillMaps.add(new TrollRomance());
        questSkillMaps.add(new TrollStronghold());
        questSkillMaps.add(new UndergroundPass());
        questSkillMaps.add(new Watchtower());
        questSkillMaps.add(new WhatLiesBelow());
        questSkillMaps.add(new ZogreFleshEaters());
        questSkillMaps.add(new SecretsOfTheNorth());
        questSkillMaps.add(new DesertTreasureII());
        questSkillMaps.add(new PathOfGlouphrie());
    }

    public List<Quest> getQuestsFulfilledByLevel(Map<Skill, Integer> previousSkills, Skill skillLevelled, Integer newLevel)
    {
        List<Quest> matchedQuests = new ArrayList<>();

        List<BaseQuestSkillMap> questSkillMapsCopy = questSkillMaps;
        BaseQuestSkillMap[] questSkillMapsForSkill = questSkillMapsCopy.stream().filter(questSkillMap -> plugin.getIncompleteQuestsList().contains(questSkillMap.getQuest()) && questSkillMap.hasSkillAsRequirement(skillLevelled)).toArray(BaseQuestSkillMap[]::new);

        for (BaseQuestSkillMap questSkillMap : questSkillMapsForSkill)
        {
            if (questSkillMap.newSkillLevelMeetsRequirements(previousSkills, skillLevelled, newLevel))
                matchedQuests.add(questSkillMap.getQuest());
        }

        return matchedQuests;
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