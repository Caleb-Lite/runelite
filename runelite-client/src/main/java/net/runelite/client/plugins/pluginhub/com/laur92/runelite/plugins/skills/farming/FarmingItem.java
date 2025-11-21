package net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins.skills.farming;

import lombok.*;

import java.util.Set;

@Builder
@Getter
@ToString
public class FarmingItem
{
    private Set<Integer> itemIDs;
    private String name;
    private String plural_name;
    @Builder.Default private int level = 1;
    private FarmingPatchType patchType;
    private double plantXP;
    private int plantQuantity;
    private double checkHealthXP;
    private double harvestXP;
    private int minHarvestQuantity;
    private int maxHarvestQuantity;
    private int minCTS;
    private int maxCTS;
}

