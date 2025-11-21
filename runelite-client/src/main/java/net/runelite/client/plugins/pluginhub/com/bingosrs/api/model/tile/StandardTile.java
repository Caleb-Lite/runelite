package net.runelite.client.plugins.pluginhub.com.bingosrs.api.model.tile;

import net.runelite.client.plugins.pluginhub.com.bingosrs.api.model.RequiredDrop;
import com.google.gson.annotations.SerializedName;

public class StandardTile extends Tile {
    @SerializedName("requiredDrops")
    public RequiredDrop[] requiredDrops;

    public RequiredDrop[] getRequiredDrops() {
        return this.requiredDrops;
    }
}