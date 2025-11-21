package net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.events;

import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.parts.NetworkItem;

import java.util.ArrayList;

public class LocationInfoEvent implements Event{
    public ArrayList<NetworkItem> locations;
    public LocationInfoEvent(ArrayList<NetworkItem> locations) {
        this.locations = locations;
    }
}
