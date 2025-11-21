package net.runelite.client.plugins.pluginhub.rsfost.loadtime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RegionMode
{
    DESTINATION_ONLY("Destination"),
    ORIGIN_ONLY("Origin"),
    DESTINATION_OR_ORIGIN("Both");

    private final String name;

    @Override
    public String toString()
    {
        return name;
    }
}
