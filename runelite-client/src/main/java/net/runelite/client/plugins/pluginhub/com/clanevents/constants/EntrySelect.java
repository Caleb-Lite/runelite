package net.runelite.client.plugins.pluginhub.com.clanevents.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntrySelect
{
    NONE("None", 0),
    HOME("Home", 1),
    HUB("Clan Hub", 2),
    SOTW("Skill of the Week", 3),
    BOTW("Boss of the Week", 4),
    HOF_OVERALL("Hall of Fame - Overall", 5),
    HOF_KC("Hall of Fame - KC", 6),
    HOF_PB("Hall of Fame - PB", 7);

    private final String name;
    private final int value;

    @Override
    public String toString()
    {
        return name;
    }
}
