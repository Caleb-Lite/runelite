package net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins.skills.prayer;

import com.google.common.collect.ImmutableSet;
import net.runelite.api.ItemID;

import java.util.*;

public enum PrayerItems
{
    // STANDARD BONES
    BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BONES))
            .itemType(PrayerItemType.BONES)
            .xp(4.5)
            .build()),
    BURNT_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BURNT_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(4.5)
            .build()),
    WOLF_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.WOLF_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(4.5)
            .build()),
    BAT_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BAT_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(5.3)
            .build()),
    BIG_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BIG_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(15)
            .build()),
    SHAIKAHAN_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.SHAIKAHAN_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(25)
            .build()),
    DAGANNOTH_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.DAGANNOTH_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(125)
            .build()),



    //DRACONIC BONES
    BABYDRAGON_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BABYDRAGON_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(30)
            .build()),
    DRAGON_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.DRAGON_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(72)
            .build()),
    DRAKE_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.DRAKE_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(80)
            .build()),
    HYDRA_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.HYDRA_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(110)
            .build()),
    LAVA_DRAGON_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.LAVA_DRAGON_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(85)
            .build()),
    WYRM_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.WYRM_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(50)
            .build()),
    WYVERN_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.WYVERN_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(72)
            .build()),
    SUPERIOR_DRAGON_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.SUPERIOR_DRAGON_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(150)
            .level(70)
            .build()),



    //SIMIAN BONES
    MONKEY_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.MONKEY_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(5)
            .build()),
    SMALL_ZOMBIE_MONKEY_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.SMALL_ZOMBIE_MONKEY_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(5)
            .build()),
    LARGE_ZOMBIE_MONKEY_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.LARGE_ZOMBIE_MONKEY_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(5)
            .build()),
    GORILLA_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.GORILLA_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(18)
            .build()),
    BEARDED_GORILLA_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BEARDED_GORILLA_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(18)
            .build()),
    SMALL_NINJA_MONKEY_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.SMALL_NINJA_MONKEY_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(16)
            .build()),
    MEDIUM_NINJA_MONKEY_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.MEDIUM_NINJA_MONKEY_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(18)
            .build()),
    SKELETON_GORILLA_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BONES_3187)) //Assumption has been made these are the correct item
            .itemType(PrayerItemType.BONES)
            .xp(3)
            .build()),



    //OGRE BONES
    JOGRE_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.JOGRE_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(15)
            .build()),
    BURNT_JOGRE_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BURNT_JOGRE_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(16)
            .build()),

    //TODO: The item IDs on these could be wrong, need to check; refer issue 2
    PASTY_JOGRE_BONES_ONE(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.PASTY_JOGRE_BONES, ItemID.PASTY_JOGRE_BONES_3129))
            .itemType(PrayerItemType.BONES)
            .xp(17)
            .build()),
    PASTY_JOGRE_BONES_TWO(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.PASTY_JOGRE_BONES_3131, ItemID.PASTY_JOGRE_BONES_3132))
            .itemType(PrayerItemType.BONES)
            .xp(16)
            .build()),

    //TODO: The item IDs on these could be wrong, need to check; refer issue 2
    MARINATED_JOGRE_BONES_ONE(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.MARINATED_J_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(18)
            .build()),
    MARINATED_JOGRE_BONES_TWO(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.MARINATED_J_BONES_3133))
            .itemType(PrayerItemType.BONES)
            .xp(17)
            .build()),

    ZOGRE_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.ZOGRE_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(22.5)
            .build()),
    FAYRG_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.FAYRG_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(84)
            .build()),
    RAURG_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.RAURG_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(96)
            .build()),
    OURG_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.OURG_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(140)
            .build()),



    //BONEMEAL
    BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(18)
            .build()),
    WOLF_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.WOLF_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(18)
            .build()),
    BAT_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BAT_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(21.2)
            .build()),
    BIG_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BIG_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(60)
            .build()),
    BURNT_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BURNT_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(18)
            .build()),
    BURNT_JOGRE_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BURNT_JOGRE_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(64)
            .build()),
    BABY_DRAGON_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BABY_DRAGON_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(120)
            .build()),
    DRAGON_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.DRAGON_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(288)
            .build()),
    MONKEY_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.MONKEY_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(20)
            .build()),
    SMALL_NINJA_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.SMALL_NINJA_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(20)
            .build()),
    MEDIUM_NINJA_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.MEDIUM_NINJA_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(18)
            .build()),
    GORILLA_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.GORILLA_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(72)
            .build()),
    BEARDED_GORILLA_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BEARDED_GORILLA_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(72)
            .build()),
    SKELETON_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.SKELETON_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(12)
            .build()),
    SMALL_ZOMBIE_MONKEY_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.SMALL_ZOMBIE_MONKEY_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(20)
            .build()),
    LARGE_ZOMBIE_MONKEY_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.LARGE_ZOMBIE_MONKEY_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(18)
            .build()),
    JOGRE_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.JOGRE_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(60)
            .build()),
    ZOGRE_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.ZOGRE_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(90)
            .build()),
    FAYRG_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.FAYRG_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(336)
            .build()),
    RAURG_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.RAURG_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(384)
            .build()),
    OURG_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.HYDRA_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(560)
            .build()),
    SHAIKAHAN_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.SHAIKAHAN_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(100)
            .build()),
    DAGANNOTHKING_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.DAGANNOTHKING_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(500)
            .build()),
    WYVERN_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.WYVERN_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(288)
            .build()),
    LAVA_DRAGON_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.LAVA_DRAGON_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(340)
            .build()),
    SUPERIOR_DRAGON_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.SUPERIOR_DRAGON_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(600)
            .level(70)
            .build()),
    WYRM_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.WYRM_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(200)
            .build()),
    DRAKE_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.DRAKE_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(320)
            .build()),
    HYDRA_BONEMEAL(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.HYDRA_BONEMEAL))
            .itemType(PrayerItemType.BONEMEAL)
            .xp(440)
            .build()),



    //ENRICHED BONES
    SMALL_ENRICHED_BONE(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.SMALL_ENRICHED_BONE))
            .itemType(PrayerItemType.ENRICHED_BONE)
            .xp(500)
            .build()),
    MEDIUM_ENRICHED_BONE(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.MEDIUM_ENRICHED_BONE))
            .itemType(PrayerItemType.ENRICHED_BONE)
            .xp(1000)
            .build()),
    LARGE_ENRICHED_BONE(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.LARGE_ENRICHED_BONE))
            .itemType(PrayerItemType.ENRICHED_BONE)
            .xp(1500)
            .build()),
    RARE_ENRICHED_BONE(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.RARE_ENRICHED_BONE))
            .itemType(PrayerItemType.ENRICHED_BONE)
            .xp(2500)
            .build()),



    //SPECIAL BONES
    BLEACHED_BONES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BLEACHED_BONES))
            .itemType(PrayerItemType.BONES)
            .xp(4.5)
            .build()),
    CURVED_BONE(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.CURVED_BONE))
            .itemType(PrayerItemType.BONES)
            .xp(15)
            .build()),
    LONG_BONE(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.LONG_BONE))
            .itemType(PrayerItemType.BONES)
            .xp(15.1)
            .build()),



    //GOBLIN PRIESTS
    SNOTHEADS_BONE(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.SNOTHEADS_BONE))
            .itemType(PrayerItemType.GOBLIN_PRIEST_BONE)
            .xp(1250)
            .level(50)
            .build()),
    SNAILFEETS_BONE(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.SNAILFEETS_BONE))
            .itemType(PrayerItemType.GOBLIN_PRIEST_BONE)
            .xp(2500)
            .level(50)
            .build()),
    MOSSCHINS_BONE(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.MOSSCHINS_BONE))
            .itemType(PrayerItemType.GOBLIN_PRIEST_BONE)
            .xp(5000)
            .level(50)
            .build()),
    REDEYES_BONE(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.REDEYES_BONE))
            .itemType(PrayerItemType.GOBLIN_PRIEST_BONE)
            .xp(10000)
            .level(50)
            .build()),
    STRONGBONES_BONE(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.STRONGBONES_BONE))
            .itemType(PrayerItemType.GOBLIN_PRIEST_BONE)
            .xp(20000)
            .level(50)
            .build()),



    //DEMONIC ASHES
    FIENDISH_ASHES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.FIENDISH_ASHES))
            .itemType(PrayerItemType.ASHES)
            .xp(10)
            .build()),
    VILE_ASHES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.VILE_ASHES))
            .itemType(PrayerItemType.ASHES)
            .xp(25)
            .build()),
    MALICIOUS_ASHES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.MALICIOUS_ASHES))
            .itemType(PrayerItemType.ASHES)
            .xp(65)
            .build()),
    ABYSSAL_ASHES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.ABYSSAL_ASHES))
            .itemType(PrayerItemType.ASHES)
            .xp(85)
            .build()),
    INFERNAL_ASHES(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.INFERNAL_ASHES))
            .itemType(PrayerItemType.ASHES)
            .xp(110)
            .build()),



    //SHADE REMAINS
    //TODO: Later (issue 3)



    //FISH OFFERINGS
    GUPPY(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.GUPPY))
            .itemType(PrayerItemType.FISH_OFFERING)
            .xp(4)
            .build()),
    CAVEFISH(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.CAVEFISH))
            .itemType(PrayerItemType.FISH_OFFERING)
            .xp(7)
            .build()),
    TETRA(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.TETRA))
            .itemType(PrayerItemType.FISH_OFFERING)
            .xp(10)
            .build()),
    CATFISH(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.CATFISH))
            .itemType(PrayerItemType.FISH_OFFERING)
            .xp(16)
            .build()),



    //DARK ALTAR
    VORKATHS_HEAD(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.VORKATHS_HEAD))
            .itemType(PrayerItemType.DARK_ALTAR)
            .xp(1000)
            .build()),
    ABYSSAL_HEAD(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.ABYSSAL_HEAD))
            .itemType(PrayerItemType.DARK_ALTAR)
            .xp(2500)
            .build()),
    ALCHEMICAL_HYDRA_HEADS(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.ALCHEMICAL_HYDRA_HEADS))
            .itemType(PrayerItemType.DARK_ALTAR)
            .xp(2500)
            .build()),
    BASILISK_HEAD(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BASILISK_HEAD))
            .itemType(PrayerItemType.DARK_ALTAR)
            .xp(2500)
            .build()),
    COCKATRICE_HEAD(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.COCKATRICE_HEAD))
            .itemType(PrayerItemType.DARK_ALTAR)
            .xp(2500)
            .build()),
    CRAWLING_HAND(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.CRAWLING_HAND_7975))
            .itemType(PrayerItemType.DARK_ALTAR)
            .xp(2500)
            .build()),
    KBD_HEADS(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.KBD_HEADS))
            .itemType(PrayerItemType.DARK_ALTAR)
            .xp(2500)
            .build()),
    KQ_HEAD(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.KQ_HEAD))
            .itemType(PrayerItemType.DARK_ALTAR)
            .xp(2500)
            .build()),
    KURASK_HEAD(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.KURASK_HEAD))
            .itemType(PrayerItemType.DARK_ALTAR)
            .xp(2500)
            .build()),



    //MISCELLANEOUS
    BIRDS_EGG(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.BIRDS_EGG, ItemID.BIRDS_EGG_5077, ItemID.BIRDS_EGG_5078))
            .itemType(PrayerItemType.EGG_OFFERING)
            .xp(100)
            .build()),
    VAMPYRE_DUST(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.VAMPYRE_DUST))
            .itemType(PrayerItemType.BRAZIER)
            .xp(200)
            .level(54)
            .build()),
    ELIDINIS_WARD(PrayerItem.builder()
            .itemIDs(ImmutableSet.of(ItemID.ELIDINIS_WARD))
            .itemType(PrayerItemType.ELIDINIS_WARD)
            .xp(220)
            .level(90)
            .build()),
    ;

    private static final Map<Integer, List<PrayerItem>> idsToItem = new HashMap<>();

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

    public final PrayerItem item;
    PrayerItems(PrayerItem item)
    {
        this.item = item;
    }

    public static PrayerItem getPrayerItem(int itemID)
    {
        if(!idsToItem.containsKey(itemID)) return null;

        return idsToItem.get(itemID).get(0);
    }
}
