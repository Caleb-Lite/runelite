package net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.server;

import com.google.gson.annotations.SerializedName;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacket;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.network.APPacketType;

public class SetReplyPacket extends APPacket {
    @SerializedName("key")
    public String key;
    @SerializedName("value")
    public Object value;
    @SerializedName("original_value")
    public Object original_Value;
    @SerializedName("slot")
    public int slot;
    @SerializedName("request_id")
    public int requestID;

    public SetReplyPacket() {
        super(APPacketType.SetReply);
    }

}
