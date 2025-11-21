package net.runelite.client.plugins.pluginhub.io.mark.hdminimap.render;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MinimapStyle {
    DEFAULT("OSRS"),
    HD("HD");

    private final String displayName;

    @Override
    public String toString() {
        return displayName;
    }
}