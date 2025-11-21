package net.runelite.client.plugins.pluginhub.com.hawolt.gotr.events.minigame.impl;

import net.runelite.client.plugins.pluginhub.com.hawolt.gotr.events.minigame.AbstractMinigameEvent;
import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)
public class PortalSpawnEvent extends AbstractMinigameEvent {
    @Getter(AccessLevel.PUBLIC)
    private int ticksUntilDespawn;

    public PortalSpawnEvent(int clientTick, int ticksUntilDespawn) {
        super(clientTick);
        this.ticksUntilDespawn = ticksUntilDespawn;
    }

    @Override
    public String toString() {
        return "PortalSpawnEvent{" +
                "ticksUntilDespawn=" + ticksUntilDespawn +
                ", clientTick=" + clientTick +
                '}';
    }
}
