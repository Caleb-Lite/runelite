package net.runelite.client.plugins.pluginhub.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SpellbookType
{
    Standard("Standard"),
    Ancient("Ancient"),
    Lunar("Lunar"),
    Arceuus("Arceuus");

    private final String name;

    @Override
    public String toString()
    {
        return name;
    }
}