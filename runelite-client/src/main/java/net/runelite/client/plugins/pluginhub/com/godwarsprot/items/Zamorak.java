package net.runelite.client.plugins.pluginhub.com.godwarsprot.items;

import net.runelite.api.ItemID;

import java.util.HashSet;

public class Zamorak {

    private final HashSet<Integer> ZamorakItemHash;

    public Zamorak(){
        this.ZamorakItemHash = new HashSet<>();
        this.ZamorakItemHash.add(ItemID.ZAMORAK_GODSWORD);
        this.ZamorakItemHash.add(ItemID.ZAMORAKIAN_SPEAR);
        this.ZamorakItemHash.add(ItemID.ZAMORAKIAN_HASTA);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_STAFF);
        this.ZamorakItemHash.add(ItemID.RUNE_SCIMITAR_ORNAMENT_KIT_ZAMORAK);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_CROZIER);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_MJOLNIR);
        this.ZamorakItemHash.add(ItemID.STAFF_OF_THE_DEAD);
        this.ZamorakItemHash.add(ItemID.STAFF_OF_LIGHT);
        this.ZamorakItemHash.add(ItemID.STAFF_OF_BALANCE);
        this.ZamorakItemHash.add(ItemID.TOXIC_STAFF_OF_THE_DEAD);
        this.ZamorakItemHash.add(ItemID.DRAGON_HUNTER_LANCE);
        this.ZamorakItemHash.add(ItemID.THAMMARONS_SCEPTRE_A);
        this.ZamorakItemHash.add(ItemID.THAMMARONS_SCEPTRE);
        this.ZamorakItemHash.add(ItemID.ACCURSED_SCEPTRE_A); 
        this.ZamorakItemHash.add(ItemID.ACCURSED_SCEPTRE);
        this.ZamorakItemHash.add(ItemID.VIGGORAS_CHAINMACE);
        this.ZamorakItemHash.add(ItemID.URSINE_CHAINMACE);
        this.ZamorakItemHash.add(ItemID.INQUISITORS_MACE);
        this.ZamorakItemHash.add(ItemID.UNHOLY_BOOK);
        this.ZamorakItemHash.add(ItemID.DAMAGED_BOOK_3841);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_KITESHIELD);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_DHIDE_SHIELD);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_COIF);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_MITRE);
        this.ZamorakItemHash.add(ItemID.ELDER_CHAOS_HOOD);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_FULL_HELM);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_HALO);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_MAX_HOOD);
        this.ZamorakItemHash.add(ItemID.IMBUED_ZAMORAK_MAX_HOOD);
        this.ZamorakItemHash.add(ItemID.INQUISITORS_GREAT_HELM);
        this.ZamorakItemHash.add(ItemID.OATHPLATE_HELM);
        this.ZamorakItemHash.add(ItemID.DAGONHAI_HAT);
        this.ZamorakItemHash.add(ItemID.ELITE_BLACK_FULL_HELM);
        this.ZamorakItemHash.add(ItemID.DARK_SQUALL_HOOD);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_CAPE);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_CLOAK);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_MAX_CAPE);
        this.ZamorakItemHash.add(ItemID.IMBUED_ZAMORAK_CAPE);
        this.ZamorakItemHash.add(ItemID.IMBUED_ZAMORAK_MAX_CAPE);
        this.ZamorakItemHash.add(ItemID.IMBUED_ZAMORAK_CAPE_DEADMAN);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_ROBE_TOP);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_MONK_TOP);
        this.ZamorakItemHash.add(ItemID.ELDER_CHAOS_TOP);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_DHIDE_BODY);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_PLATEBODY);
        this.ZamorakItemHash.add(ItemID.INQUISITORS_HAUBERK);
        this.ZamorakItemHash.add(ItemID.OATHPLATE_CHEST);
        this.ZamorakItemHash.add(ItemID.DAGONHAI_ROBE_TOP);
        this.ZamorakItemHash.add(ItemID.ELITE_BLACK_PLATEBODY);
        this.ZamorakItemHash.add(ItemID.DARK_SQUALL_ROBE_TOP);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_ROBE_LEGS);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_MONK_BOTTOM);
        this.ZamorakItemHash.add(ItemID.ELDER_CHAOS_ROBE);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_CHAPS);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_PLATELEGS);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_PLATESKIRT);
        this.ZamorakItemHash.add(ItemID.INQUISITORS_PLATESKIRT);
        this.ZamorakItemHash.add(ItemID.OATHPLATE_LEGS);
        this.ZamorakItemHash.add(ItemID.DAGONHAI_ROBE_BOTTOM);
        this.ZamorakItemHash.add(ItemID.ELITE_BLACK_PLATELEGS);
        this.ZamorakItemHash.add(ItemID.DARK_SQUALL_ROBE_BOTTOM);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_BRACERS);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_DHIDE_BOOTS);
        this.ZamorakItemHash.add(ItemID.UNHOLY_BLESSING);
        this.ZamorakItemHash.add(ItemID.UNHOLY_SYMBOL);
        this.ZamorakItemHash.add(ItemID.ZAMORAK_STOLE);
        



    }
    public boolean checkList(int Item)
    {
        return this.ZamorakItemHash.contains(Item);
    }

}
