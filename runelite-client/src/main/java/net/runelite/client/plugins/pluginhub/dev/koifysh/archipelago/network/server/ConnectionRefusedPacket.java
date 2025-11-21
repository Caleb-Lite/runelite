package net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.server;

import com.google.gson.annotations.SerializedName;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacket;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacketType;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.ConnectionResult;

public class ConnectionRefusedPacket extends APPacket {

    @SerializedName("errors")
    public ConnectionResult[] errors;

    public ConnectionRefusedPacket() {
        super(APPacketType.ConnectionRefused);
    }
}
