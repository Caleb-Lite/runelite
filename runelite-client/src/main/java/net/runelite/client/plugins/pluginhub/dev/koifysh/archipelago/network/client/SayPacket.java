package net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.client;

import com.google.gson.annotations.SerializedName;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacket;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacketType;

public class SayPacket extends APPacket {

    @SerializedName("text")
    String text;

    public SayPacket(String message) {
        super(APPacketType.Say);
        text = message;
    }
}
