package net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins.skills.farming;

import net.runelite.api.ItemID;

import java.util.*;

public enum Compost
{
    NONE(CompostType.builder()
            .name("No Compost")
            .harvestLives(3)
            .itemID(-1)
            .xp(0)
            .build()),
    NORMAL(CompostType.builder()
            .name("Compost")
            .harvestLives(4)
            .itemID(ItemID.COMPOST)
            .xp(18)
            .build()),
    SUPERCOMPOST(CompostType.builder()
            .name("Supercompost")
            .harvestLives(5)
            .itemID(ItemID.SUPERCOMPOST)
            .xp(26)
            .build()),
    ULTRACOMPOST(CompostType.builder()
            .name("Ultracompost")
            .harvestLives(6)
            .itemID(ItemID.ULTRACOMPOST)
            .xp(36)
            .build());

    private static final Map<Integer, CompostType> idsToCompost = new HashMap<>();

    static
    {
        Arrays.stream(values())
                .map(fi -> fi.compost)
                .forEach(fi -> idsToCompost.put(fi.getItemID(), fi));
    }

    public final CompostType compost;
    Compost(CompostType compost) { this.compost = compost; }

    public static CompostType getCompost(int itemID)
    {
        if(!idsToCompost.containsKey(itemID)) return null;

        return idsToCompost.get(itemID);
    }

    @Override
    public String toString()
    {
        return this.compost.getName();
    }
}
