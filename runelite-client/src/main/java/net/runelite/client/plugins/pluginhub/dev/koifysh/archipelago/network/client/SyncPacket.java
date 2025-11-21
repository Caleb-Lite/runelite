package net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.client;

import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacket;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacketType;

public class SyncPacket extends APPacket {


    public SyncPacket() {
        super(APPacketType.Sync);
    }
}
