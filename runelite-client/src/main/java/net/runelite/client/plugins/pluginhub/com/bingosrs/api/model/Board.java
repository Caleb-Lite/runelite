package net.runelite.client.plugins.pluginhub.com.bingosrs.api.model;

import net.runelite.client.plugins.pluginhub.com.bingosrs.api.model.tile.Tile;
import com.google.gson.annotations.SerializedName;

public class Board {
    @SerializedName("size")
    public Integer size;

    @SerializedName("tiles")
    public Tile[] tiles;
}
