package net.runelite.client.plugins.pluginhub.com.hawolt.gotr.events.minigame.impl;

import net.runelite.client.plugins.pluginhub.com.hawolt.gotr.events.minigame.AbstractMinigameEvent;
import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)
public class RewardEligibleEvent extends AbstractMinigameEvent {
    public RewardEligibleEvent(int clientTick) {
        super(clientTick);
    }

    @Override
    public String toString() {
        return "RewardEligibleEvent{" +
                "clientTick=" + clientTick +
                '}';
    }
}
