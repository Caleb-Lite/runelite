package net.runelite.client.plugins.pluginhub.com.github.jeromkiller.HideAndSeekTracker.Util;

import net.runelite.client.plugins.pluginhub.com.github.jeromkiller.HideAndSeekTracker.game.HideAndSeekPlayer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class playerExport {
    private final String name;
    private final int tickCount;
    private final int hints;

    public playerExport(HideAndSeekPlayer player) {
        this.name = player.getName();
        this.tickCount = player.getTickCount();
        this.hints = player.getHints();
    }

    public HideAndSeekPlayer toHideAndSeekPlayer() {
        return new HideAndSeekPlayer(name, tickCount, hints);
    }
}
