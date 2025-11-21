package net.runelite.client.plugins.pluginhub.Posiedien_Leagues_Planner;

import net.runelite.client.ui.overlay.worldmap.WorldMapPoint;

import javax.sound.sampled.Line;
import java.awt.*;
import java.util.*;

public class LeagueRegionBounds
{
    public RegionType Type = null;

    // Basically a linked list of points
    public HashMap<UUID, LeagueRegionPoint> RegionPoints = new HashMap<UUID, LeagueRegionPoint>();

    // Transient
    public ArrayList<WorldPointPolygon> RegionPolygons = new ArrayList<>();
    public ArrayList<RegionLine> RegionLines = new ArrayList<>();

    public LeagueRegionBounds()
    {
    }
    public LeagueRegionBounds(RegionType currentRegion)

    {
        Type = currentRegion;
    }

    public String ExportData()
    {
        StringBuilder ExportString = new StringBuilder();
        ExportString.append(Type);
        ExportString.append(",");

        ExportString.append(RegionPoints.size());
        ExportString.append(",");

        for (HashMap.Entry<UUID, LeagueRegionPoint> entry : RegionPoints.entrySet())
        {
            ExportString.append(entry.getValue().ExportData());
        }

        return ExportString.toString();
    }

    public void ImportData(Scanner sc)
    {
        Type = RegionType.valueOf(sc.next());

        int RegionPointSize = Integer.parseInt(sc.next());
        for (int i = 0; i < RegionPointSize; ++i)
        {
            LeagueRegionPoint NewRegionPoint = new LeagueRegionPoint();
            NewRegionPoint.ImportData(sc);
            RegionPoints.put(NewRegionPoint.GUID, NewRegionPoint);
        }

    }
}

/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
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