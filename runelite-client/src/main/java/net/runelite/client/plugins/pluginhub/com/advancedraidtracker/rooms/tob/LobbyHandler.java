package net.runelite.client.plugins.pluginhub.com.advancedraidtracker.rooms.tob;

import net.runelite.client.plugins.pluginhub.com.advancedraidtracker.AdvancedRaidTrackerConfig;
import net.runelite.api.Client;
import net.runelite.client.plugins.pluginhub.com.advancedraidtracker.utility.datautility.DataWriter;

public class LobbyHandler extends RoomHandler
{
    public LobbyHandler(Client client, DataWriter clog, AdvancedRaidTrackerConfig config)
    {
        super(client, clog, config);
    }
}
