package net.runelite.client.plugins.pluginhub.com.godwarsprot.items;

import net.runelite.api.ItemID;

import java.util.HashSet;

public class Saradomin {

    private final HashSet<Integer> SaradominItemHash;

    public Saradomin(){
        this.SaradominItemHash = new HashSet<>();
        this.SaradominItemHash.add(ItemID.SARADOMIN_GODSWORD);
        this.SaradominItemHash.add(ItemID.SARADOMIN_SWORD);
        this.SaradominItemHash.add(ItemID.RUNE_SCIMITAR_ORNAMENT_KIT_SARADOMIN);
        this.SaradominItemHash.add(ItemID.STAFF_OF_LIGHT);
        this.SaradominItemHash.add(ItemID.SARADOMINS_BLESSED_SWORD);
        this.SaradominItemHash.add(ItemID.SARADOMIN_STAFF);
        this.SaradominItemHash.add(ItemID.SARADOMIN_CROZIER);
        this.SaradominItemHash.add(ItemID.SARADOMIN_MJOLNIR);
        this.SaradominItemHash.add(ItemID.HALLOWED_HAMMER);
        this.SaradominItemHash.add(ItemID.HOLY_SCYTHE_OF_VITUR);
        this.SaradominItemHash.add(ItemID.HOLY_GHRAZI_RAPIER);
        this.SaradominItemHash.add(ItemID.HOLY_SANGUINESTI_STAFF);
        this.SaradominItemHash.add(ItemID.HOLY_BOOK);
        this.SaradominItemHash.add(ItemID.DAMAGED_BOOK);
        this.SaradominItemHash.add(ItemID.SARADOMIN_KITESHIELD);
        this.SaradominItemHash.add(ItemID.SARADOMIN_DHIDE_SHIELD);
        this.SaradominItemHash.add(ItemID.HALLOWED_FOCUS);
        this.SaradominItemHash.add(ItemID.SARADOMIN_COIF);
        this.SaradominItemHash.add(ItemID.SARADOMIN_MITRE);
        this.SaradominItemHash.add(ItemID.SARADOMIN_FULL_HELM);
        this.SaradominItemHash.add(ItemID.SARADOMIN_HALO);
        this.SaradominItemHash.add(ItemID.SARADOMIN_MAX_HOOD);
        this.SaradominItemHash.add(ItemID.IMBUED_SARADOMIN_MAX_HOOD);
        this.SaradominItemHash.add(ItemID.JUSTICIAR_FACEGUARD);
        this.SaradominItemHash.add(ItemID.SARADOMIN_CAPE);
        this.SaradominItemHash.add(ItemID.SARADOMIN_CLOAK);
        this.SaradominItemHash.add(ItemID.SARADOMIN_MAX_CAPE);
        this.SaradominItemHash.add(ItemID.IMBUED_SARADOMIN_CAPE);
        this.SaradominItemHash.add(ItemID.IMBUED_SARADOMIN_MAX_CAPE);
        this.SaradominItemHash.add(ItemID.SARADOMIN_ROBE_TOP);
        this.SaradominItemHash.add(ItemID.SARADOMIN_DHIDE_BODY);
        this.SaradominItemHash.add(ItemID.MONKS_ROBE_TOP);
        this.SaradominItemHash.add(ItemID.SARADOMIN_PLATEBODY);
        this.SaradominItemHash.add(ItemID.JUSTICIAR_CHESTGUARD);
        this.SaradominItemHash.add(ItemID.SARADOMIN_ROBE_LEGS);
        this.SaradominItemHash.add(ItemID.SARADOMIN_CHAPS);
        this.SaradominItemHash.add(ItemID.MONKS_ROBE);
        this.SaradominItemHash.add(ItemID.SARADOMIN_PLATELEGS);
        this.SaradominItemHash.add(ItemID.SARADOMIN_PLATESKIRT);
        this.SaradominItemHash.add(ItemID.JUSTICIAR_LEGGUARDS);
        this.SaradominItemHash.add(ItemID.HOLY_WRAPS);
        this.SaradominItemHash.add(ItemID.SARADOMIN_BRACERS);
        this.SaradominItemHash.add(ItemID.SARADOMIN_DHIDE_BOOTS);
        this.SaradominItemHash.add(ItemID.HOLY_SANDALS);
        this.SaradominItemHash.add(ItemID.DEVOUT_BOOTS);
        this.SaradominItemHash.add(ItemID.HOLY_BLESSING);
        this.SaradominItemHash.add(ItemID.HALLOWED_GRAPPLE);
        this.SaradominItemHash.add(ItemID.HOLY_SYMBOL);
        this.SaradominItemHash.add(ItemID.SARADOMIN_STOLE);
        this.SaradominItemHash.add(ItemID.HALLOWED_SYMBOL);
        this.SaradominItemHash.add(ItemID.HALLOWED_RING);
        this.SaradominItemHash.add(ItemID.RING_OF_ENDURANCE);


    }
    public boolean checkList(int Item)
    {
        return this.SaradominItemHash.contains(Item);
    }
}
