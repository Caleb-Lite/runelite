package net.runelite.client.plugins.pluginhub.io.mark.hdminimap.mapelement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum representing different types of map elements that can be managed
 */
@Getter
@RequiredArgsConstructor
public enum MapElementType {
    MAP_FUNCTION("map function", "mapfunctions","mapFunctions"),
    MAP_ICON("map icon", "mapicons","mapIcons");

    private final String displayName;
    private final String configPrefix;
    private final String fileName;
}