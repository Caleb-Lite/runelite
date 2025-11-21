package net.runelite.client.plugins.pluginhub.com.CosRoom.items;

import net.runelite.api.ItemID;

import java.util.HashSet;

public class FancyDressBox {
    private final HashSet<Integer> FancyDressHash;
    public boolean checkList(int Item)
    {
        return this.FancyDressHash.contains(Item);
    }
    public FancyDressBox()
    {
        this.FancyDressHash = new HashSet<Integer>();
        this.FancyDressHash.add(ItemID.BEEKEEPERS_HAT);
        this.FancyDressHash.add(ItemID.BEEKEEPERS_TOP);
        this.FancyDressHash.add(ItemID.BEEKEEPERS_LEGS);
        this.FancyDressHash.add(ItemID.BEEKEEPERS_GLOVES);
        this.FancyDressHash.add(ItemID.BEEKEEPERS_BOOTS);
        this.FancyDressHash.add(ItemID.CAMO_HELMET);
        this.FancyDressHash.add(ItemID.CAMO_TOP);
        this.FancyDressHash.add(ItemID.CAMO_BOTTOMS);
        this.FancyDressHash.add(ItemID.FROG_MASK);
        this.FancyDressHash.add(ItemID.ROYAL_FROG_BLOUSE);
        this.FancyDressHash.add(ItemID.ROYAL_FROG_TUNIC);
        this.FancyDressHash.add(ItemID.ROYAL_FROG_SKIRT);
        this.FancyDressHash.add(ItemID.ROYAL_FROG_LEGGINGS);
        this.FancyDressHash.add(ItemID.LEDERHOSEN_HAT);
        this.FancyDressHash.add(ItemID.LEDERHOSEN_TOP);
        this.FancyDressHash.add(ItemID.LEDERHOSEN_SHORTS);
        this.FancyDressHash.add(ItemID.MIME_MASK);
        this.FancyDressHash.add(ItemID.MIME_TOP);
        this.FancyDressHash.add(ItemID.MIME_LEGS);
        this.FancyDressHash.add(ItemID.MIME_GLOVES);
        this.FancyDressHash.add(ItemID.MIME_BOOTS);
        this.FancyDressHash.add(ItemID.SHADE_ROBE_TOP);
        this.FancyDressHash.add(ItemID.SHADE_ROBE);
        this.FancyDressHash.add(ItemID.STALE_BAGUETTE);
        this.FancyDressHash.add(ItemID.ZOMBIE_MASK);
        this.FancyDressHash.add(ItemID.ZOMBIE_SHIRT);
        this.FancyDressHash.add(ItemID.ZOMBIE_TROUSERS);
        this.FancyDressHash.add(ItemID.ZOMBIE_GLOVES);
        this.FancyDressHash.add(ItemID.ZOMBIE_BOOTS);



    }
}
