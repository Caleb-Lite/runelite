package net.runelite.client.plugins.pluginhub.com.runeprofile;

import com.google.inject.Provides;

import com.runeprofile.autosync.*;
import net.runelite.client.plugins.pluginhub.com.runeprofile.data.AddActivities;
import net.runelite.client.plugins.pluginhub.com.runeprofile.data.activities.Activity;
import net.runelite.client.plugins.pluginhub.com.runeprofile.data.activities.ActivityData;
import net.runelite.client.plugins.pluginhub.com.runeprofile.ui.ChatPlayerMenuOption;
import net.runelite.client.plugins.pluginhub.com.runeprofile.ui.CollectionLogCommand;
import net.runelite.client.plugins.pluginhub.com.runeprofile.ui.CollectionLogPageMenuOption;
import net.runelite.client.plugins.pluginhub.com.runeprofile.ui.ManualUpdateButtonManager;
import net.runelite.client.plugins.pluginhub.com.runeprofile.utils.PlayerState;
import net.runelite.client.plugins.pluginhub.com.runeprofile.utils.RuneProfileApiException;
import net.runelite.client.plugins.pluginhub.com.runeprofile.utils.Utils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Slf4j
@PluginDescriptor(
        name = "RuneProfile"
)
public class RuneProfilePlugin extends Plugin {
    public static final String CONFIG_GROUP = "runeprofile";

    @Inject
    @Named("developerMode")
    @Getter
    private boolean developerMode;

    @Inject
    private Client client;

    @Inject
    private ClientThread clientThread;

    @Inject
    private RuneProfileApiClient runeProfileApiClient;

    @Inject
    private PlayerDataService playerDataService;

    @Inject
    private AutoSyncScheduler autoSyncScheduler;

    @Inject
    private CollectionLogWidgetSubscriber collectionLogWidgetSubscriber;

    @Inject
    private CollectionNotificationSubscriber collectionNotificationSubscriber;

    @Inject
    private ValuableDropSubscriber valuableDropSubscriber;

    @Inject
    private ManualUpdateButtonManager manualUpdateButtonManager;

    @Inject
    private CollectionLogPageMenuOption collectionLogPageMenuOption;

    @Inject
    private ChatPlayerMenuOption chatPlayerMenuOption;

    @Inject
    private CollectionLogCommand collectionLogCommand;

    private RuneProfilePanel runeProfilePanel;

    @Provides
    RuneProfileConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(RuneProfileConfig.class);
    }

    @Override
    protected void startUp() {
        this.runeProfilePanel = injector.getInstance(RuneProfilePanel.class);
        runeProfilePanel.startUp();

        playerDataService.startUp();

        autoSyncScheduler.startUp();
        valuableDropSubscriber.startUp();
        collectionLogWidgetSubscriber.startUp();
        collectionNotificationSubscriber.startUp();

        manualUpdateButtonManager.startUp();
        collectionLogPageMenuOption.startUp();
        chatPlayerMenuOption.startUp();
        collectionLogCommand.startUp();
    }

    @Override
    protected void shutDown() {
        runeProfilePanel.shutDown();

        playerDataService.shutDown();

        autoSyncScheduler.shutDown();
        valuableDropSubscriber.shutDown();
        collectionLogWidgetSubscriber.shutDown();
        collectionNotificationSubscriber.shutDown();

        manualUpdateButtonManager.shutDown();
        collectionLogPageMenuOption.shutDown();
        chatPlayerMenuOption.shutDown();
        collectionLogCommand.shutDown();
    }

    public void updateProfileAsync(boolean isAutoSync) {
        if (!PlayerState.isValidPlayerState(client)) {
            if (!isAutoSync) {
                clientThread.invokeLater(() -> {
                    client.addChatMessage(ChatMessageType.CONSOLE, "RuneProfile", "You are not allowed to update your profile on this world.", "RuneProfile");
                });
            }

            throw new IllegalStateException("Invalid player state");
        }

        if (!isAutoSync) {
            // If this is a manual sync, reset the auto-sync timer
            autoSyncScheduler.resetAutoSyncTimer();
        }

        playerDataService.getPlayerDataAsync().thenCompose((data) -> {
                    // sanity check: a player reported syncs going through on invalid worlds
                    if (!PlayerState.isValidPlayerState(client)) {
                        throw new IllegalStateException("Invalid player state after fetching player data");
                    }
                    return runeProfileApiClient.updateProfileAsync(data);
                })
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Error updating profile", ex);

                        final String errorMessage = Utils.getApiErrorMessage(ex, "Failed to update your profile.");

                        if (!isAutoSync) {
                            clientThread.invokeLater(() -> {
                                client.addChatMessage(ChatMessageType.CONSOLE, "RuneProfile", errorMessage, "RuneProfile");
                            });
                        }

                        throw new RuneProfileApiException(errorMessage);
                    }

                    if (!isAutoSync) {
                        clientThread.invokeLater(() -> {
                            client.addChatMessage(ChatMessageType.CONSOLE, "RuneProfile", "Your profile has been updated!", "RuneProfile");
                        });
                    }

                    playerDataService.reset();
                });
    }

    public void deleteProfileAsync() {
        if (!PlayerState.isValidPlayerState(client)) {
            throw new IllegalStateException("Invalid player state");
        }

        playerDataService.getAccountIdAsync().thenCompose((accountId) -> runeProfileApiClient.deleteProfileAsync(accountId))
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Error deleting profile", ex);

                        final String errorMessage = Utils.getApiErrorMessage(ex, "Failed to delete your profile.");

                        clientThread.invokeLater(() -> {
                            client.addChatMessage(ChatMessageType.CONSOLE, "RuneProfile", errorMessage, "RuneProfile");
                        });

                        throw new RuneProfileApiException(errorMessage);
                    }

                    clientThread.invokeLater(() -> {
                        client.addChatMessage(ChatMessageType.CONSOLE, "RuneProfile", "Your profile has been deleted!", "RuneProfile");
                    });
                });
    }

    public void updateModelAsync() {
        if (!PlayerState.isValidPlayerState(client)) {
            throw new IllegalStateException("Invalid player state");
        }

        playerDataService.getPlayerModelDataAsync().thenCompose((data) -> runeProfileApiClient.updateModelAsync(data)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Error updating model", ex);

                        final String errorMessage = Utils.getApiErrorMessage(ex, "Failed to update your player model.");

                        clientThread.invokeLater(() -> {
                            client.addChatMessage(ChatMessageType.CONSOLE, "RuneProfile", errorMessage, "RuneProfile");
                        });

                        throw new RuneProfileApiException(errorMessage);
                    }

                    clientThread.invokeLater(() -> {
                        client.addChatMessage(ChatMessageType.CONSOLE, "RuneProfile", "Your player model has been updated!", "RuneProfile");
                    });
                }));
    }

    public void addActivitiesAsync(List<? extends Activity<? extends ActivityData>> activities) {
        if (!PlayerState.isValidPlayerState(client)) {
            throw new IllegalStateException("Invalid player state");
        }

        playerDataService.getAccountIdAsync().thenCompose((accountId) -> runeProfileApiClient.addActivities(new AddActivities(accountId, activities))).whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Error adding activities", ex);

                final String errorMessage = Utils.getApiErrorMessage(ex, "Failed to add activities.");

                clientThread.invokeLater(() -> {
                    client.addChatMessage(ChatMessageType.CONSOLE, "RuneProfile", errorMessage, "RuneProfile");
                });

                throw new RuneProfileApiException(errorMessage);
            }
        });
    }

}

/*
 * Copyright (c) 2020 Abex
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