package net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.server;

import com.google.gson.annotations.SerializedName;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacket;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacketType;

import java.util.Set;

public class ConnectUpdatePacket extends APPacket {

    @SerializedName("tags")
    public Set<String> tags;

    public ConnectUpdatePacket() {
        super(APPacketType.ConnectUpdate);
    }

}