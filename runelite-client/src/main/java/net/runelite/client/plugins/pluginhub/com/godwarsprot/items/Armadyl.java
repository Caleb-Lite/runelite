package net.runelite.client.plugins.pluginhub.com.godwarsprot.items;

import net.runelite.api.ItemID;

import java.util.HashSet;

public class Armadyl {
    private final HashSet<Integer> ArmadylItemHash;

    public Armadyl(){
        this.ArmadylItemHash = new HashSet<>();
        this.ArmadylItemHash.add(ItemID.ARMADYL_GODSWORD);
        this.ArmadylItemHash.add(ItemID.ARMADYL_GODSWORD_DEADMAN);
        this.ArmadylItemHash.add(ItemID.ARMADYL_CROSSBOW);
        this.ArmadylItemHash.add(ItemID.ARMADYL_CROZIER);
        this.ArmadylItemHash.add(ItemID.CRAWS_BOW);
        this.ArmadylItemHash.add(ItemID.WEBWEAVER_BOW);
        this.ArmadylItemHash.add(ItemID.BOOK_OF_LAW);
        this.ArmadylItemHash.add(ItemID.DAMAGED_BOOK_12609);
        this.ArmadylItemHash.add(ItemID.ARMADYL_KITESHIELD);
        this.ArmadylItemHash.add(ItemID.ARMADYL_DHIDE_SHIELD);
        this.ArmadylItemHash.add(ItemID.ARMADYL_HELMET);
        this.ArmadylItemHash.add(ItemID.ARMADYL_COIF);
        this.ArmadylItemHash.add(ItemID.ARMADYL_FULL_HELM);
        this.ArmadylItemHash.add(ItemID.ARMADYL_MITRE);
        this.ArmadylItemHash.add(ItemID.ARMADYL_HALO);
        this.ArmadylItemHash.add(ItemID.ARMADYL_CLOAK);
        this.ArmadylItemHash.add(ItemID.ARMADYL_CHESTPLATE);
        this.ArmadylItemHash.add(ItemID.ARMADYL_PLATEBODY);
        this.ArmadylItemHash.add(ItemID.ARMADYL_DHIDE_BODY);
        this.ArmadylItemHash.add(ItemID.ARMADYL_ROBE_TOP);
        this.ArmadylItemHash.add(ItemID.ARMADYL_CHAINSKIRT);
        this.ArmadylItemHash.add(ItemID.ARMADYL_PLATELEGS);
        this.ArmadylItemHash.add(ItemID.ARMADYL_ROBE_LEGS);
        this.ArmadylItemHash.add(ItemID.ARMADYL_BRACERS);
        this.ArmadylItemHash.add(ItemID.ARMADYL_DHIDE_BOOTS);
        this.ArmadylItemHash.add(ItemID.ARMADYL_PENDANT);
        this.ArmadylItemHash.add(ItemID.ARMADYL_STOLE);
        this.ArmadylItemHash.add(ItemID.HONOURABLE_BLESSING);
        this.ArmadylItemHash.add(ItemID.ARMADYL_CHAPS);
        this.ArmadylItemHash.add(ItemID.ARMADYL_PLATESKIRT);
    }

    public boolean checkList(int Item)
    {
        return this.ArmadylItemHash.contains(Item);
    }

}
