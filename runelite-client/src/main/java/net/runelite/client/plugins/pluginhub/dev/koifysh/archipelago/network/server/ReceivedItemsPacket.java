package net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.server;

import com.google.gson.annotations.SerializedName;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacket;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacketType;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.parts.NetworkItem;

import java.util.ArrayList;

public class ReceivedItemsPacket extends APPacket {

    @SerializedName("index")
    public int index;

    @SerializedName("items")
    public ArrayList<NetworkItem> items;

    public ReceivedItemsPacket() {
        super(APPacketType.ReceivedItems);
    }
}
