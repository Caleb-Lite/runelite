package net.runelite.client.plugins.pluginhub.com.pleae;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.ActorDeath;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(name = "Pleae")
public class PleaePlugin extends Plugin {
    private static final int OVERHEAD_TEXT_TICK_TIMEOUT = 6;
    public static final int CYCLES_PER_GAME_TICK = Constants.GAME_TICK_LENGTH / Constants.CLIENT_TICK_LENGTH;
    private static final int CYCLES_FOR_OVERHEAD_TEXT = OVERHEAD_TEXT_TICK_TIMEOUT * CYCLES_PER_GAME_TICK;

    @Inject
    private Client client;

    @Inject
    private PleaeConfig config;

    @Provides
    PleaeConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(PleaeConfig.class);
    }

    @Subscribe
    public void onActorDeath(ActorDeath actorDeath) {
        Actor actor = actorDeath.getActor();

        if (!(actor instanceof Player)) {
            return;
        }

        Player player = (Player) actor;
        addChatMessage(player);
        addOverheadMessage(player);
    }

    private void addChatMessage(Player player) {
        if (player == null) {
            return;
        }
        if (player == client.getLocalPlayer() && !config.chatMessageForYou()) {
            return;
        }
        if (player != client.getLocalPlayer() && !config.chatMessageForOthers()) {
            return;
        }
        String playerName = player.getName();
        if (playerName == null) {
            return;
        }

        String deathMessage = getDeathMessage();
        if (deathMessage == null) {
            return;
        }

        client.addChatMessage(ChatMessageType.PUBLICCHAT, playerName, deathMessage, null);
    }

    private void addOverheadMessage(Player player) {
        if (player == null) {
            return;
        }
        if (player == client.getLocalPlayer() && !config.overheadMessageForYou()) {
            return;
        }
        if (player != client.getLocalPlayer() && !config.overheadMessageForOthers()) {
            return;
        }

        String deathMessage = getDeathMessage();
        if (deathMessage == null) {
            return;
        }

        player.setOverheadText(deathMessage);
        player.setOverheadCycle(CYCLES_FOR_OVERHEAD_TEXT);
    }

    private String getDeathMessage() {
        if (config.deathMessage() == null) {
            return null;
        }

        String configMessage = config.deathMessage().trim();
        return configMessage.length() == 0 ? null : configMessage;
    }
}
