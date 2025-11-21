package net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.server;

import com.google.gson.annotations.SerializedName;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacket;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacketType;

public class PrintPacket extends APPacket {

    @SerializedName("text")
    String text;

    public PrintPacket() {
        super(APPacketType.Print);
    }

    public String getText() {
        return text;
    }
}
