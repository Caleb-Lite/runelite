package net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.server;

import com.google.gson.annotations.SerializedName;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacket;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacketType;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.parts.NetworkPlayer;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.parts.NetworkSlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ConnectedPacket extends APPacket {

    @SerializedName("team")
    public int team = -1;
    @SerializedName("slot")
    public int slot = -1;
    @SerializedName("players")
    public ArrayList<NetworkPlayer> players;
    @SerializedName("missing_locations")
    public HashSet<Long> missingLocations = new HashSet<>();
    @SerializedName("checked_locations")
    public HashSet<Long> checkedLocations = new HashSet<>();
    @SerializedName("slot_info")
    public HashMap<Integer, NetworkSlot> slotInfo;

    public ConnectedPacket() {
        super(APPacketType.Connected);
    }
}
