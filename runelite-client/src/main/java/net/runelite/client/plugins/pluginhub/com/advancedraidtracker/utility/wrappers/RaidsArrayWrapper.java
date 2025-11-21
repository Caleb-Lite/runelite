package net.runelite.client.plugins.pluginhub.com.advancedraidtracker.utility.wrappers;

import net.runelite.client.plugins.pluginhub.com.advancedraidtracker.SimpleTOBData;

import java.util.ArrayList;

public class RaidsArrayWrapper
{
    public ArrayList<SimpleTOBData> data;
    public String filename;

    public RaidsArrayWrapper(ArrayList<SimpleTOBData> data, String filename)
    {
        this.data = data;
        this.filename = filename;
    }
}
