package net.runelite.client.plugins.pluginhub.com.hawolt.gotr.events.minigame.impl;

import net.runelite.client.plugins.pluginhub.com.hawolt.gotr.events.minigame.AbstractMinigameEvent;
import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)
public class PortalDespawnEvent extends AbstractMinigameEvent {
    public PortalDespawnEvent(int clientTick) {
        super(clientTick);
    }

    @Override
    public String toString() {
        return "PortalDespawnEvent{" +
                "clientTick=" + clientTick +
                '}';
    }
}
