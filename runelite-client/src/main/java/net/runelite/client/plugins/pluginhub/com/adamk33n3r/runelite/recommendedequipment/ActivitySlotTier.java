package net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.recommendedequipment;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ActivitySlotTier {
    private List<ActivityItem> items = new ArrayList<>();
}
