package net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.client;

import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.ClientStatus;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacket;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacketType;

public class StatusUpdatePacket extends APPacket {

    int status;

    public StatusUpdatePacket(ClientStatus status) {
        super(APPacketType.StatusUpdate);
        this.status = status.getValue();

    }
}
