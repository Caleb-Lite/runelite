package net.runelite.client.plugins.pluginhub.com.hawolt.gotr.events.minigame.impl;

import net.runelite.client.plugins.pluginhub.com.hawolt.gotr.events.minigame.AbstractMinigameEvent;
import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)
public class MilestoneEvent extends AbstractMinigameEvent {
    public MilestoneEvent(int clientTick) {
        super(clientTick);
    }

    @Override
    public String toString() {
        return "MilestoneEvent{" +
                "clientTick=" + clientTick +
                '}';
    }


}
