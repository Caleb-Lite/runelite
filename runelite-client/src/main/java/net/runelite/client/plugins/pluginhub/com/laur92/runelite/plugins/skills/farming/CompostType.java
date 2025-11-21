package net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins.skills.farming;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CompostType
{
    private final String name;
    private final int harvestLives;
    private final int itemID;
    private final int xp;
}

