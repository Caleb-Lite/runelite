package net.runelite.client.plugins.pluginhub.com.wintertodt.scouter;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class WintertodtBossData
{
    @Getter
    private final int world;

    @Getter
    private final int health;

    @Getter
    @Setter
    private boolean uploaded;

    @Getter
    private long time;

    @Getter
    private int timer;

    public WintertodtBossData(int health, int world, long time, boolean uploaded, int timer)
    {
        this.health = health;
        this.world = world;
        this.uploaded = uploaded;
        this.time = time;
        this.timer = timer;
    }

    public Date convertToDate() {
        return new java.util.Date( time * 1000);
    }
}
