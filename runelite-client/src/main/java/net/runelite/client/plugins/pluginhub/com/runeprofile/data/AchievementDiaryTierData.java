package net.runelite.client.plugins.pluginhub.com.runeprofile.data;

import lombok.Data;

@Data
public class AchievementDiaryTierData {
    private final int areaId;
    private final int tierIndex;
    private final int completedCount;
}
