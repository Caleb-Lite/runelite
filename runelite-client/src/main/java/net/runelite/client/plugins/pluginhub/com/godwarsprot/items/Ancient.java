package net.runelite.client.plugins.pluginhub.com.godwarsprot.items;

import net.runelite.api.ItemID;

import java.util.HashSet;

public class Ancient {
    private final HashSet<Integer> AncientItemHash;

    public Ancient(){
        this.AncientItemHash = new HashSet<>();
        this.AncientItemHash.add(ItemID.ANCIENT_GODSWORD);
        this.AncientItemHash.add(ItemID.ANCIENT_STAFF);
        this.AncientItemHash.add(ItemID.ANCIENT_SCEPTRE);
        this.AncientItemHash.add(ItemID.ANCIENT_CROZIER);
        this.AncientItemHash.add(ItemID.VENATOR_BOW);
        this.AncientItemHash.add(ItemID.ZARYTE_CROSSBOW);
        this.AncientItemHash.add(ItemID.BOOK_OF_DARKNESS);
        this.AncientItemHash.add(ItemID.DAMAGED_BOOK_12611);
        this.AncientItemHash.add(ItemID.ANCIENT_KITESHIELD);
        this.AncientItemHash.add(ItemID.ANCIENT_DHIDE_SHIELD);
        this.AncientItemHash.add(ItemID.ANCIENT_COIF);
        this.AncientItemHash.add(ItemID.ANCIENT_FULL_HELM);
        this.AncientItemHash.add(ItemID.ANCIENT_MITRE);
        this.AncientItemHash.add(ItemID.ANCIENT_HALO);
        this.AncientItemHash.add(ItemID.ANCIENT_CEREMONIAL_MASK);
        this.AncientItemHash.add(ItemID.TORVA_FULL_HELM);
        this.AncientItemHash.add(ItemID.VIRTUS_MASK);
        this.AncientItemHash.add(ItemID.HOOD_OF_DARKNESS);
        this.AncientItemHash.add(ItemID.ANCIENT_CLOAK);
        this.AncientItemHash.add(ItemID.ANCIENT_PLATEBODY);
        this.AncientItemHash.add(ItemID.ANCIENT_DHIDE_BODY);
        this.AncientItemHash.add(ItemID.ANCIENT_ROBE_TOP);
        this.AncientItemHash.add(ItemID.TORVA_PLATEBODY);
        this.AncientItemHash.add(ItemID.VIRTUS_ROBE_TOP);
        this.AncientItemHash.add(ItemID.ANCIENT_CEREMONIAL_TOP);
        this.AncientItemHash.add(ItemID.ROBE_TOP_OF_DARKNESS);
        this.AncientItemHash.add(ItemID.ANCIENT_PLATELEGS);
        this.AncientItemHash.add(ItemID.ANCIENT_CHAPS);
        this.AncientItemHash.add(ItemID.ANCIENT_PLATESKIRT);
        this.AncientItemHash.add(ItemID.ANCIENT_ROBE_LEGS);
        this.AncientItemHash.add(ItemID.TORVA_PLATELEGS);
        this.AncientItemHash.add(ItemID.VIRTUS_ROBE_BOTTOM);
        this.AncientItemHash.add(ItemID.ANCIENT_CEREMONIAL_LEGS);
        this.AncientItemHash.add(ItemID.ROBE_BOTTOM_OF_DARKNESS);
        this.AncientItemHash.add(ItemID.ANCIENT_BRACERS);
        this.AncientItemHash.add(ItemID.ZARYTE_VAMBRACES);
        this.AncientItemHash.add(ItemID.ANCIENT_CEREMONIAL_GLOVES);
        this.AncientItemHash.add(ItemID.GLOVES_OF_DARKNESS);
        this.AncientItemHash.add(ItemID.ANCIENT_DHIDE_BOOTS);
        this.AncientItemHash.add(ItemID.ANCIENT_CEREMONIAL_BOOTS);
        this.AncientItemHash.add(ItemID.BOOTS_OF_DARKNESS);
        this.AncientItemHash.add(ItemID.ANCIENT_BLESSING);
        this.AncientItemHash.add(ItemID.ANCIENT_STOLE);
    }
    public boolean checkList(int Item)
    {
        return this.AncientItemHash.contains(Item);
    }

}
