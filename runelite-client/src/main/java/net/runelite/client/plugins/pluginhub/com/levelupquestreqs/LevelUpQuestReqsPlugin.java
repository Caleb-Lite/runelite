package net.runelite.client.plugins.pluginhub.com.levelupquestreqs;

import net.runelite.client.plugins.pluginhub.com.levelupquestreqs.questmaps.IgnoredQuests;
import net.runelite.client.plugins.pluginhub.com.levelupquestreqs.questmaps.QuestSkillMaps;
import net.runelite.client.plugins.pluginhub.com.levelupquestreqs.skillmaps.MaxSkillRequirements;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.StatChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.chatbox.ChatboxPanelManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import java.util.*;

@Slf4j
@PluginDescriptor(
        name = "Level Up Quest Requirements",
        description = "Displays a dialog on levelling up when you first reach the requirements for a quest.",
        tags = {"quest", "requirements", "req", "reqs", "level", "levels"},
        enabledByDefault = true
)
public class LevelUpQuestReqsPlugin extends Plugin
{
    @Getter(AccessLevel.PACKAGE)
    @Inject
    private Client client;

    @Getter(AccessLevel.PACKAGE)
    @Inject
    private ClientThread clientThread;

    @Getter(AccessLevel.PACKAGE)
    @Inject
    private ChatboxPanelManager chatboxPanelManager;

    private final QuestSkillMaps questSkillMaps = new QuestSkillMaps(this);

    private final Map<Skill, Integer> previousLevelMap = new EnumMap<>(Skill.class);
    @Getter
    private final List<Quest> incompleteQuestsList = new ArrayList<>();
    private final List<Quest> newQuestReqsMet = new ArrayList<>();

    private boolean loggingIn;

    private LevelUpQuestReqsInterfaceInput input;

    private static final Integer rfdBaseQuestVarbit = 1850;

    @Override
    protected void startUp() throws Exception
    {
        IgnoredQuests.initialise();
        MaxSkillRequirements.initialise();

        log.info("Level Up Quest Requirements started!");
    }

    @Override
    protected void shutDown() throws Exception
    {
        if (input != null && chatboxPanelManager.getCurrentInput() == input) chatboxPanelManager.close();

        previousLevelMap.clear();
        incompleteQuestsList.clear();
        newQuestReqsMet.clear();

        input = null;
        log.info("Level Up Quest Requirements stopped!");
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged gameStateChanged)
    {
        if (gameStateChanged.getGameState() == GameState.LOGGING_IN)
        {
            previousLevelMap.clear();
            loggingIn = true;
        }
    }

    @Subscribe
    public void onGameTick(GameTick gameTick)
    {
        if (loggingIn)
        {
            loggingIn = false;
            initialisePreviousLevelMap();
            initialiseIncompleteQuestList();
        }

        if (input != null) input.closeIfTriggered();

        if (newQuestReqsMet.isEmpty() || !chatboxPanelManager.getContainerWidget().isHidden()) return;

        final Quest quest = newQuestReqsMet.remove(0);
        input = new LevelUpQuestReqsInterfaceInput(this, quest);
        chatboxPanelManager.openInput(input);
    }

    @Subscribe
    public void onStatChanged(StatChanged statChanged)
    {
        final Skill skill = statChanged.getSkill();

        final int skillLevelBefore = previousLevelMap.getOrDefault(skill, -1);

        // Return early if the level hasn't changed.
        if (statChanged.getLevel() == skillLevelBefore) return;

        // Return early if the new level can't be a requirement.
        if (statChanged.getLevel() > MaxSkillRequirements.getMaxLevelForSkill(skill)) return;

        List<Quest> questRequirementsReached = questSkillMaps.getQuestsFulfilledByLevel(previousLevelMap, skill, statChanged.getLevel());
        previousLevelMap.replace(skill, statChanged.getLevel());

        if (questRequirementsReached.isEmpty()) return;

        newQuestReqsMet.addAll(questRequirementsReached);
    }

    private void initialisePreviousLevelMap()
    {
        for (final Skill skill : Skill.values())
        {
            if (skill.getName() == "Overall") return;

            previousLevelMap.put(skill, client.getRealSkillLevel(skill));
        }

        log.info("Skill levels loaded!");
    }

    private void initialiseIncompleteQuestList()
    {
        Quest[] quests = Arrays.stream(Quest.values()).filter(quest -> !IgnoredQuests.isIgnored(quest)).toArray(Quest[]::new);
        for (final Quest quest : quests)
        {
            if (quest.getName().contains("Recipe for Disaster")) {
                if (!isRFDSubquestComplete(quest)) incompleteQuestsList.add(quest);

                continue;
            }

            switch (getQuestStatus(quest.getId()))
            {
                case 2:
                    continue;
                default:
                    incompleteQuestsList.add(quest);
            }
        }

        log.info("Found " + incompleteQuestsList.toArray().length + " incomplete quests!");
    }

    private Integer getQuestStatus(Integer questId) {
        client.runScript(ScriptID.QUEST_STATUS_GET, questId);
        return client.getIntStack()[0];
    }

    private boolean isRFDSubquestComplete(Quest quest) {
        final Integer questVarbit = getQuestVarbitForRFDSubquest(quest);

        switch (quest) {
            case RECIPE_FOR_DISASTER__ANOTHER_COOKS_QUEST:
                return (client.getVarbitValue(rfdBaseQuestVarbit) >= 3);
            case RECIPE_FOR_DISASTER__MOUNTAIN_DWARF:
                return (client.getVarbitValue(questVarbit) >= 60 || client.getVarbitValue(rfdBaseQuestVarbit) < 3);
            case RECIPE_FOR_DISASTER__WARTFACE__BENTNOZE:
                return (client.getVarbitValue(questVarbit) >= 40 || client.getVarbitValue(rfdBaseQuestVarbit) < 3);
            case RECIPE_FOR_DISASTER__PIRATE_PETE:
                return (client.getVarbitValue(questVarbit) >= 110 || client.getVarbitValue(rfdBaseQuestVarbit) < 3);
            case RECIPE_FOR_DISASTER__LUMBRIDGE_GUIDE:
            case RECIPE_FOR_DISASTER__EVIL_DAVE:
                return (client.getVarbitValue(questVarbit) >= 5 || client.getVarbitValue(rfdBaseQuestVarbit) < 3);
            case RECIPE_FOR_DISASTER__SIR_AMIK_VARZE:
                return (client.getVarbitValue(questVarbit) >= 20 || client.getVarbitValue(rfdBaseQuestVarbit) <3);
            case RECIPE_FOR_DISASTER__SKRACH_UGLOGWEE:
                return (client.getVarbitValue(questVarbit) >= 170 || client.getVarbitValue(rfdBaseQuestVarbit) < 3);
            case RECIPE_FOR_DISASTER__KING_AWOWOGEI:
                return (client.getVarbitValue(questVarbit) >= 50 || client.getVarbitValue(rfdBaseQuestVarbit) < 3);
            default:
                return true;
        }
    }

    private Integer getQuestVarbitForRFDSubquest(Quest quest) {
        switch (quest) {
            case RECIPE_FOR_DISASTER:
            case RECIPE_FOR_DISASTER__ANOTHER_COOKS_QUEST:
            case RECIPE_FOR_DISASTER__CULINAROMANCER:
                return rfdBaseQuestVarbit;
            case RECIPE_FOR_DISASTER__MOUNTAIN_DWARF:
                return 1892;
            case RECIPE_FOR_DISASTER__WARTFACE__BENTNOZE:
                return 1867;
            case RECIPE_FOR_DISASTER__PIRATE_PETE:
                return 1895;
            case RECIPE_FOR_DISASTER__LUMBRIDGE_GUIDE:
                return 1896;
            case RECIPE_FOR_DISASTER__EVIL_DAVE:
                return 1878;
            case RECIPE_FOR_DISASTER__SIR_AMIK_VARZE:
                return 1910;
            case RECIPE_FOR_DISASTER__SKRACH_UGLOGWEE:
                return 1904;
            case RECIPE_FOR_DISASTER__KING_AWOWOGEI:
                return 1914;
            default:
                return -1;
        }
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