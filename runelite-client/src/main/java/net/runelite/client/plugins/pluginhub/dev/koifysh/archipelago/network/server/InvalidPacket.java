package net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.server;

import com.google.gson.annotations.SerializedName;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacket;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacketType;

public class InvalidPacket extends APPacket {

    @SerializedName("type")
    public String type;
    @SerializedName("original_cmd")
    public String Original_cmd;
    @SerializedName("text")
    public String text;

    public InvalidPacket() {
        super(APPacketType.InvalidPacket);
    }
}
