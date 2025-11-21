package net.runelite.client.plugins.pluginhub.com.grouptileman.runelite.config;

import lombok.Value;

/**
 * Used for serialization of ground marker points.
 */
@Value
public class Tile {
    private int regionId;
    private int regionX;
    private int regionY;
    private int z;
}
