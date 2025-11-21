package net.runelite.client.plugins.pluginhub.com.gghammerreminder;

import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;

import java.util.Arrays;
import java.util.List;

import static net.runelite.api.ItemID.GRANITE_HAMMER;
import static net.runelite.api.ItemID.ROCK_HAMMER;
import static net.runelite.api.ItemID.ROCK_THROWNHAMMER;

public class GGHammerReminderUtils {
    private static final List<Integer> HAMMER_ITEM_IDS = Arrays.asList(ROCK_HAMMER, ROCK_THROWNHAMMER, GRANITE_HAMMER);
    private static final int GG_REGION_ID = 6727;

    public static boolean hasHammerInInventory(Client client) {
        ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);
        if (inventory != null) {
            for (Item item : inventory.getItems()) {
                if (HAMMER_ITEM_IDS.contains(item.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isInTargetRegion(Client client) {
        int[] mapRegions = client.getMapRegions();
        for (int regionId : mapRegions) {
            if (regionId == GG_REGION_ID) {
                return true;
            }
        }
        return false;
    }
}