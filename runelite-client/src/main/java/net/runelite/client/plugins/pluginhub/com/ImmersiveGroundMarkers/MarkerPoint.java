package net.runelite.client.plugins.pluginhub.com.ImmersiveGroundMarkers;

import javax.annotation.Nullable;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(exclude = {"orientation", "modelId", "animation", "colorsToReplace", "colorsToFind"})
public class MarkerPoint {
    private int regionID;
    private int regionX;
    private int regionY;
    private int z;
    private int modelId;
    private int orientation;
    private int animation;
    @Nullable
    private short[] colorsToReplace;
    @Nullable
    private short[] colorsToFind;
}

