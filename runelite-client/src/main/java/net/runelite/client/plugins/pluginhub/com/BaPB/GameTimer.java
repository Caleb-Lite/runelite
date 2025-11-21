package net.runelite.client.plugins.pluginhub.com.BaPB;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static net.runelite.client.util.RSTimeUnit.GAME_TICKS;

@Slf4j
public class GameTimer
{
    @Getter
    public int roundTicks = 0;
    private boolean running = false;

    public void start()
    {
        running = true;
    }

    public void stop()
    {
        running = false;
    }

    public void clear()
    {
        running = false;
        roundTicks = 0;
    }

    public void onGameTick()
    {
        if (running)
        {
            roundTicks++;
        }
    }

    public double getElapsedSeconds(Boolean isLeader) {
        // Default behavior: subtract ticks based on leader
        return getElapsedSeconds(isLeader, true);
    }

    public double getElapsedSeconds(Boolean isLeader, boolean offset)
    {
        int numTicks = 0;

        if (offset) {
            numTicks = isLeader ? 2 : 1;
        }

        int adjustedTicks = Math.max(0, roundTicks - numTicks);

        Duration duration = Duration.of(adjustedTicks, GAME_TICKS);
        return duration.toMillis() / 1000.0;
    }
}
