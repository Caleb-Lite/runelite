package net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins.skills.prayer;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Builder
@Getter
@ToString
public class PrayerItem
{
    private Set<Integer> itemIDs;
    private PrayerItemType itemType;
    @Builder.Default private int level = 1;
    private double xp;
}
