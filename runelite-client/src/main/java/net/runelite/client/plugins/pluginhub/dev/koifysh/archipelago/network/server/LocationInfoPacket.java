package net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.server;

import com.google.gson.annotations.SerializedName;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacket;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacketType;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.parts.NetworkItem;

import java.util.ArrayList;

public class LocationInfoPacket extends APPacket {

    @SerializedName("locations")
    public ArrayList<NetworkItem> locations;

    public LocationInfoPacket() {
        super(APPacketType.LocationInfo);
    }
}
