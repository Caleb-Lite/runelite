package net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins.skills.firemaking;

import com.google.common.collect.ImmutableSet;
import net.runelite.api.ItemID;

import java.util.*;

public enum FiremakingItems
{
    // LOGS
    LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.LOGS, ItemID.ACHEY_TREE_LOGS))
            .itemType(FiremakingItemType.LOGS)
            .xp(40)
            .minSacredOilDoses(2)
            .build()),
    COLOURED_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BLUE_LOGS, ItemID.GREEN_LOGS, ItemID.PURPLE_LOGS, ItemID.RED_LOGS, ItemID.WHITE_LOGS))
            .itemType(FiremakingItemType.LOGS)
            .xp(50)
            .build()),
    OAK_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.OAK_LOGS))
            .itemType(FiremakingItemType.LOGS)
            .xp(60)
            .level(15)
            .minSacredOilDoses(2)
            .build()),
    WILLOW_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.WILLOW_LOGS))
            .itemType(FiremakingItemType.LOGS)
            .xp(90)
            .level(30)
            .minSacredOilDoses(3)
            .build()),
    BLISTERWOOD_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BLISTERWOOD_LOGS))
            .itemType(FiremakingItemType.LOGS)
            .xp(96)
            .level(62)
            .build()),
    TEAK_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.TEAK_LOGS))
            .itemType(FiremakingItemType.LOGS)
            .xp(105)
            .level(35)
            .minSacredOilDoses(3)
            .build()),
    ARCTIC_PINE_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.ARCTIC_PINE_LOGS))
            .itemType(FiremakingItemType.LOGS)
            .xp(125)
            .level(42)
            .minSacredOilDoses(2)
            .build()),
    MAPLE_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.MAPLE_LOGS))
            .itemType(FiremakingItemType.LOGS)
            .xp(135)
            .level(45)
            .minSacredOilDoses(3)
            .build()),
    MAHOGANY_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.MAHOGANY_LOGS))
            .itemType(FiremakingItemType.LOGS)
            .xp(157.5)
            .level(50)
            .minSacredOilDoses(3)
            .build()),
    YEW_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.YEW_LOGS))
            .itemType(FiremakingItemType.LOGS)
            .xp(202.5)
            .level(60)
            .minSacredOilDoses(4)
            .build()),
    MAGIC_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.MAGIC_LOGS))
            .itemType(FiremakingItemType.LOGS)
            .xp(303.8)
            .level(75)
            .minSacredOilDoses(4)
            .build()),
    REDWOOD_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.REDWOOD_LOGS))
            .itemType(FiremakingItemType.LOGS)
            .xp(350)
            .level(90)
            .minSacredOilDoses(4)
            .build()),


    // PYRE LOGS
    PYRE_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.PYRE_LOGS))
            .itemType(FiremakingItemType.PYRE_LOGS)
            .xp(50)
            .level(5)
            .build()),
    OAK_PYRE_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.OAK_PYRE_LOGS))
            .itemType(FiremakingItemType.PYRE_LOGS)
            .xp(70)
            .level(20)
            .build()),
    WILLOW_PYRE_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.WILLOW_PYRE_LOGS))
            .itemType(FiremakingItemType.PYRE_LOGS)
            .xp(100)
            .level(35)
            .build()),
    TEAK_PYRE_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.TEAK_PYRE_LOGS))
            .itemType(FiremakingItemType.PYRE_LOGS)
            .xp(120)
            .level(40)
            .build()),
    ARCTIC_PYRE_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.ARCTIC_PYRE_LOGS))
            .itemType(FiremakingItemType.PYRE_LOGS)
            .xp(158)
            .level(47)
            .build()),
    MAPLE_PYRE_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.MAPLE_PYRE_LOGS))
            .itemType(FiremakingItemType.PYRE_LOGS)
            .xp(175)
            .level(50)
            .build()),
    MAHOGANY_PYRE_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.MAHOGANY_PYRE_LOGS))
            .itemType(FiremakingItemType.PYRE_LOGS)
            .xp(210)
            .level(55)
            .build()),
    YEW_PYRE_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.YEW_PYRE_LOGS))
            .itemType(FiremakingItemType.PYRE_LOGS)
            .xp(255)
            .level(65)
            .build()),
    MAGIC_PYRE_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.MAGIC_PYRE_LOGS))
            .itemType(FiremakingItemType.PYRE_LOGS)
            .xp(404.5)
            .level(80)
            .build()),
    REDWOOD_PYRE_LOGS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.REDWOOD_PYRE_LOGS))
            .itemType(FiremakingItemType.PYRE_LOGS)
            .xp(500)
            .level(95)
            .build()),


    // SACRED OIL
    SACRED_OIL_2(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.SACRED_OIL2))
            .itemType(FiremakingItemType.SACRED_OIL)
            .xp(10)
            .build()),
    SACRED_OIL_3(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.SACRED_OIL3))
            .itemType(FiremakingItemType.SACRED_OIL)
            .xp(12)
            .build()),
    SACRED_OIL_4(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.SACRED_OIL4))
            .itemType(FiremakingItemType.SACRED_OIL)
            .xp(16)
            .build()),


    // ORIGAMI BALLOONS
    ORIGAMI_BALLOONS(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.ORIGAMI_BALLOON))
            .itemType(FiremakingItemType.ORIGAMI_BALLOON)
            .xp(20)
            .level(20)
            .build()),


    // WINTERTODT
    BRUMA_ROOT(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BRUMA_ROOT))
            .itemType(FiremakingItemType.WINDTERTODT_BRAZIER_ROOT)
            .level(50)
            .build()),
    BRUMA_KINDLING(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BRUMA_KINDLING))
            .itemType(FiremakingItemType.WINDTERTODT_BRAZIER_KINDLING)
            .level(50)
            .build()),


    // BLAST FURNACE
    SPADEFUL_OF_COKE(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.SPADEFUL_OF_COKE))
            .itemType(FiremakingItemType.BLAST_FURNACE)
            .xp(5)
            .level(30)
            .build()),


    // BONES
    JOGRE_BONES(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.JOGRE_BONES))
            .itemType(FiremakingItemType.BONES)
            .xp(90)
            .level(30)
            .build()),


    // LIGHT ORB
    LIGHT_ORB(FiremakingItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.LIGHT_ORB))
            .itemType(FiremakingItemType.LIGHT_ORB)
            .xp(1000)
            .level(52)
            .build())
    ;

    private static final Map<Integer, List<FiremakingItem>> idsToItem = new HashMap<>();

    static
    {
        Arrays.stream(values())
                .map(fi -> fi.item)
                .forEach(fi -> fi.getItemIDs().forEach(id -> {
                    if (!idsToItem.containsKey(id))
                    {
                        idsToItem.put(id, new ArrayList<>());
                    }
                    idsToItem.get(id).add(fi);
                }));
    }

    public final FiremakingItem item;
    FiremakingItems(FiremakingItem item)
    {
        this.item = item;
    }

    public static FiremakingItem getFiremakingItem(int itemID)
    {
        if(!idsToItem.containsKey(itemID)) return null;

        return idsToItem.get(itemID).get(0);
    }
}
