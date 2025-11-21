package net.runelite.client.plugins.pluginhub.com.BaPB;

import lombok.Getter;
import net.runelite.api.coords.WorldPoint;

import java.util.HashMap;
import java.util.Map;

/**
 * Maps a WorldPoint to a lobby ID (int).
 */
public class Lobby
{
    public static class LobbyRegion
    {
        private final int xMin, xMax, yMin, yMax;

        public LobbyRegion(int xMin, int yMin)
        {
            this.xMin = xMin;
            this.yMin = yMin;
            this.xMax = xMin + 7; // 8x8 lobbies
            this.yMax = yMin + 7; // 8x8 lobbies
        }

        public boolean contains(WorldPoint point)
        {
            return point.getX() >= xMin && point.getX() <= xMax
                    && point.getY() >= yMin && point.getY() <= yMax;
        }
    }

    @Getter
    public static class RelativePoint
    {
        private final int x;
        private final int y;

        public RelativePoint(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString()
        {
            return "(" + x + "," + y + ")";
        }
    }

    // Key = lobbyId, Value = region
    private final Map<Integer, LobbyRegion> regions = new HashMap<>();

    public Lobby()
    {
        regions.put(1, new LobbyRegion(2576, 5291));
        regions.put(2, new LobbyRegion(2584, 5291));
        regions.put(3, new LobbyRegion(2595, 5291));
        regions.put(4, new LobbyRegion(2603, 5291));
        regions.put(5, new LobbyRegion(2576, 5281));
        regions.put(6, new LobbyRegion(2584, 5281));
        regions.put(7, new LobbyRegion(2595, 5281));
        regions.put(8, new LobbyRegion(2603, 5281));
        regions.put(9, new LobbyRegion(2576, 5271));
        regions.put(10, new LobbyRegion(2584, 5271));
    }

    /**
     * Returns the lobby ID containing this point, or 0 if none.
     */
    public int getLobbyId(WorldPoint point)
    {
        for (Map.Entry<Integer, LobbyRegion> entry : regions.entrySet())
        {
            if (entry.getValue().contains(point))
            {
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * Returns all LobbyRegion objects
     */
    public Iterable<LobbyRegion> getAllRegions()
    {
        return regions.values();
    }

    /**
     * Returns the relative coordinates (x, y) within the given lobby.
     * Returns null if the lobbyId is invalid.
     */
    public RelativePoint getRelativeCoordinates(WorldPoint point, int lobbyId)
    {
        LobbyRegion region = regions.get(lobbyId);
        if (region == null)
        {
            return null;
        }

        int relativeX = point.getX() - region.xMin;
        int relativeY = point.getY() - region.yMin;

        return new RelativePoint(relativeX, relativeY);
    }
}

/*
 * Copyright (c) 2018, Cameron <https://github.com/noremac201>
 * Copyright (c) 2018, Jacob M <https://github.com/jacoblairm>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */