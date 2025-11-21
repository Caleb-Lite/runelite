package net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.server;

import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.Print.APPrintPart;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacket;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacketType;

public class JsonPrintPacket extends APPacket {
    APPrintPart[] parts;

    public JsonPrintPacket() {
        super(APPacketType.PrintJSON);
    }
}
