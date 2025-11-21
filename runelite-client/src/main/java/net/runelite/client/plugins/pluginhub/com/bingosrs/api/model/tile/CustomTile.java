package net.runelite.client.plugins.pluginhub.com.bingosrs.api.model.tile;

import net.runelite.client.plugins.pluginhub.com.bingosrs.api.model.RequiredDrop;

public class CustomTile extends Tile {
    public RequiredDrop[] getRequiredDrops() {
        return new RequiredDrop[0];
    }
}
