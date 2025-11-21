package net.runelite.client.plugins.pluginhub.com.maxhit.styles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AttackStyle
{
    ACCURATE("Accurate"),
    AGGRESSIVE("Aggressive"),
    DEFENSIVE("Defensive"),
    CONTROLLED("Controlled"),
    RANGING("Ranging"),
    LONGRANGE("Longrange"),
    CASTING("Casting"),
    DEFENSIVE_CASTING("Defensive Casting"),
    OTHER("Other");

    private final String name;
}

