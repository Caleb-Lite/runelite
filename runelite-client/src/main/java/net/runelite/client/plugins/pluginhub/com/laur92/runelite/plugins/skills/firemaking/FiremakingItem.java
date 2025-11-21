package net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins.skills.firemaking;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Builder
@Getter
@ToString
public class FiremakingItem
{
    private Set<Integer> itemIDs;
    private FiremakingItemType itemType;
    @Builder.Default private int level = 1;
    private int minSacredOilDoses;
    private double xp;
}
