package net.runelite.client.plugins.pluginhub.com.patchtimer;

import lombok.Getter;
import net.runelite.api.coords.WorldPoint;


@Getter
public class TreeTimer{
    private final WorldPoint location;
    private String ticksLeftDisplay;
    private int ticksLeft;

    TreeTimer(WorldPoint location, int respawnTime) {
        this.ticksLeft = respawnTime;
        this.ticksLeftDisplay = "" + this.ticksLeft;
        this.location = location;
    }

    public void decrement() {
        this.ticksLeftDisplay = "" + --this.ticksLeft;
    }
}
