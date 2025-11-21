package net.runelite.client.plugins.pluginhub.com.hawolt.gotr.events.minigame.impl;

import net.runelite.client.plugins.pluginhub.com.hawolt.gotr.data.GameStartIndicator;
import net.runelite.client.plugins.pluginhub.com.hawolt.gotr.events.minigame.AbstractMinigameEvent;
import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)
public class GameStartingSoonEvent extends AbstractMinigameEvent {
    private final GameStartIndicator gameStartIndicator;

    public GameStartingSoonEvent(int clientTick, GameStartIndicator gameStartIndicator) {
        super(clientTick);
        this.gameStartIndicator = gameStartIndicator;
    }

    @Override
    public String toString() {
        return "GameStartingSoonEvent{" +
                "gameStartIndicator=" + gameStartIndicator +
                ", clientTick=" + clientTick +
                '}';
    }
}