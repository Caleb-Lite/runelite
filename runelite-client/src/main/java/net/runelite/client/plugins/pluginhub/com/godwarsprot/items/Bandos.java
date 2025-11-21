package net.runelite.client.plugins.pluginhub.com.godwarsprot.items;

import net.runelite.api.ItemID;

import java.util.HashSet;

public class Bandos {

    private final HashSet<Integer> BandosItemHash;

    public Bandos(){
        this.BandosItemHash = new HashSet<>();
        this.BandosItemHash.add(ItemID.ANCIENT_MACE);
        this.BandosItemHash.add(ItemID.BANDOS_GODSWORD);
        this.BandosItemHash.add(ItemID.BANDOS_CROZIER);
        this.BandosItemHash.add(ItemID.BOOK_OF_WAR);
        this.BandosItemHash.add(ItemID.DAMAGED_BOOK_12607);
        this.BandosItemHash.add(ItemID.BANDOS_KITESHIELD);
        this.BandosItemHash.add(ItemID.BANDOS_DHIDE_SHIELD);
        this.BandosItemHash.add(ItemID.BANDOS_FULL_HELM);
        this.BandosItemHash.add(ItemID.BANDOS_COIF);
        this.BandosItemHash.add(ItemID.BANDOS_MITRE);
        this.BandosItemHash.add(ItemID.BANDOS_HALO);
        this.BandosItemHash.add(ItemID.BANDOS_CLOAK);
        this.BandosItemHash.add(ItemID.BANDOS_CHESTPLATE);
        this.BandosItemHash.add(ItemID.BANDOS_PLATEBODY);
        this.BandosItemHash.add(ItemID.BANDOS_DHIDE_BODY);
        this.BandosItemHash.add(ItemID.BANDOS_ROBE_TOP);
        this.BandosItemHash.add(ItemID.BANDOS_TASSETS);
        this.BandosItemHash.add(ItemID.BANDOS_PLATELEGS);
        this.BandosItemHash.add(ItemID.BANDOS_PLATESKIRT);
        this.BandosItemHash.add(ItemID.BANDOS_ROBE_LEGS);
        this.BandosItemHash.add(ItemID.BANDOS_BRACERS);
        this.BandosItemHash.add(ItemID.BANDOS_DHIDE_BOOTS);
        this.BandosItemHash.add(ItemID.BANDOS_BOOTS);
        this.BandosItemHash.add(ItemID.GUARDIAN_BOOTS);
        this.BandosItemHash.add(ItemID.ECHO_BOOTS);
        this.BandosItemHash.add(ItemID.WAR_BLESSING);
        this.BandosItemHash.add(ItemID.BANDOS_STOLE);
        this.BandosItemHash.add(ItemID.BANDOS_CHAPS);
    }
    public boolean checkList(int Item)
    {
        return this.BandosItemHash.contains(Item);
    }

}
