package net.runelite.client.plugins.pluginhub.tictac7x.motherlode.rockfalls;

import net.runelite.api.GameObject;
import net.runelite.client.plugins.pluginhub.tictac7x.motherlode.sectors.Sectors;
import net.runelite.client.plugins.pluginhub.tictac7x.motherlode.Character;
import net.runelite.client.plugins.pluginhub.tictac7x.motherlode.sectors.Sector;
import net.runelite.client.plugins.pluginhub.tictac7x.motherlode.TicTac7xMotherlodeConfig;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

public class Rockfall {
    public final int x;
    public final int y;
    public final List<Sector> sectors;

    public Rockfall(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.sectors = Sectors.getSectors(x, y, true);
    }

    public Color getTileColor(final TicTac7xMotherlodeConfig config) {
        return config.getRockfallsColor();
    }

    public boolean isRendering(final TicTac7xMotherlodeConfig config, final Character character) {
        if (config.upstairsOnly() && sectors.contains(Sector.DOWNSTAIRS)) {
            return false;
        }

        if (Collections.disjoint(character.getSectors(), sectors)) {
            return false;
        }

        return true;
    }

    public static boolean isRockfall(final GameObject gameObject) {
        switch (gameObject.getId()) {
            case 26679:
            case 26680:
                return true;
            default:
                return false;
        }
    }
}
