package net.runelite.client.plugins.pluginhub.com.PartialSets;

import net.runelite.api.ItemID;

import java.util.ArrayList;
import java.util.List;

public class Sets {
    public static List<EquipmentSet> All(PartialSetsPlugin aPlugin, PartialSetsConfig aConfig)
    {
        ArrayList<EquipmentSet> sets = new ArrayList<>();

        sets.addAll(Misc(aPlugin, aConfig));
        sets.addAll(Barrows(aPlugin, aConfig));
        sets.addAll(Skills(aPlugin, aConfig));
        sets.addAll(Moons(aPlugin, aConfig));

        return sets;
    }

    private static List<EquipmentSet> Misc(PartialSetsPlugin aPlugin, PartialSetsConfig aConfig)
    {
        ArrayList<EquipmentSet> sets = new ArrayList<>();

        if (aConfig.VoidKnight()) {
            sets.add(new EquipmentSet("Void Knight Equipment", ItemID.ELITE_VOID_TOP,
                    new ItemSlot(aPlugin,
                            ItemID.VOID_MELEE_HELM, ItemID.VOID_MELEE_HELM_L, ItemID.VOID_MELEE_HELM_LOR, ItemID.VOID_MELEE_HELM_OR,
                            ItemID.VOID_MAGE_HELM, ItemID.VOID_MAGE_HELM_L, ItemID.VOID_MAGE_HELM_LOR, ItemID.VOID_MAGE_HELM_OR,
                            ItemID.VOID_RANGER_HELM, ItemID.VOID_RANGER_HELM_L, ItemID.VOID_RANGER_HELM_LOR, ItemID.VOID_RANGER_HELM_OR),
                    new ItemSlot(aPlugin,
                            ItemID.ELITE_VOID_TOP, ItemID.ELITE_VOID_TOP_L, ItemID.ELITE_VOID_TOP_LOR, ItemID.ELITE_VOID_TOP_OR,
                            ItemID.VOID_KNIGHT_TOP, ItemID.VOID_KNIGHT_TOP_L, ItemID.VOID_KNIGHT_TOP_LOR, ItemID.VOID_KNIGHT_TOP_OR),
                    new ItemSlot(aPlugin,
                            ItemID.ELITE_VOID_ROBE, ItemID.ELITE_VOID_ROBE_L, ItemID.ELITE_VOID_ROBE_LOR, ItemID.ELITE_VOID_ROBE_OR,
                            ItemID.VOID_KNIGHT_ROBE, ItemID.VOID_KNIGHT_ROBE_L, ItemID.VOID_KNIGHT_ROBE_LOR, ItemID.VOID_KNIGHT_ROBE_OR),
                    new ItemSlot(aPlugin,
                            ItemID.VOID_KNIGHT_GLOVES, ItemID.VOID_KNIGHT_GLOVES_L, ItemID.VOID_KNIGHT_GLOVES_LOR, ItemID.VOID_KNIGHT_GLOVES_OR)
            ));
        }
        if (aConfig.Obsidian()) {
            sets.add(new EquipmentSet("Obsidian", ItemID.OBSIDIAN_HELMET,
                    new ItemSlot(aPlugin, ItemID.OBSIDIAN_HELMET),
                    new ItemSlot(aPlugin, ItemID.OBSIDIAN_PLATEBODY),
                    new ItemSlot(aPlugin, ItemID.OBSIDIAN_PLATELEGS),
                    new AssociatedSlot(aPlugin, ItemID.BERSERKER_NECKLACE, ItemID.BERSERKER_NECKLACE_OR),
                    new AssociatedSlot(aPlugin,
                            ItemID.TOKTZKETXIL,
                            ItemID.TOKTZXILAK,
                            ItemID.TOKTZXILAK_20554,
                            ItemID.TZHAARKETEM,
                            ItemID.TZHAARKETOM,
                            ItemID.TZHAARKETOM_T,
                            ItemID.TOKTZKETXIL,
                            ItemID.TOKTZMEJTAL)
            ));
        }
        if (aConfig.Crystal()) {
            sets.add(new EquipmentSet("Crystal armour", ItemID.CRYSTAL_HELM,
                    new ItemSlot(aPlugin,
                            ItemID.CRYSTAL_HELM, 		ItemID.CRYSTAL_HELM_27705, ItemID.CRYSTAL_HELM_27717, ItemID.CRYSTAL_HELM_27729,
                            ItemID.CRYSTAL_HELM_27741, 	ItemID.CRYSTAL_HELM_27753, ItemID.CRYSTAL_HELM_27765, ItemID.CRYSTAL_HELM_27777),
                    new ItemSlot(aPlugin,
                            ItemID.CRYSTAL_BODY, 		ItemID.CRYSTAL_BODY_27697, ItemID.CRYSTAL_BODY_27709, ItemID.CRYSTAL_BODY_27721,
                            ItemID.CRYSTAL_BODY_27733, 	ItemID.CRYSTAL_BODY_27745, ItemID.CRYSTAL_BODY_27757, ItemID.CRYSTAL_BODY_27769),
                    new ItemSlot(aPlugin,
                            ItemID.CRYSTAL_LEGS, 		ItemID.CRYSTAL_LEGS_27701, ItemID.CRYSTAL_LEGS_27713, ItemID.CRYSTAL_LEGS_27725,
                            ItemID.CRYSTAL_LEGS_27737, 	ItemID.CRYSTAL_LEGS_27749, ItemID.CRYSTAL_LEGS_27761, ItemID.CRYSTAL_LEGS_27773)
                    //new AssociatedSlot(aPlugin, ) there are way too many version of these items, i will add them only if someone complains :p
            ));
        }
        if (aConfig.Shayzien()) {
            sets.add(new EquipmentSet("Shayzien tier 1", ItemID.SHAYZIEN_HELM_1,
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_HELM_1),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_PLATEBODY_1),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_GREAVES_1),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_GLOVES_1),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_BOOTS_1)
            ));
            sets.add(new EquipmentSet("Shayzien tier 2", ItemID.SHAYZIEN_HELM_2,
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_HELM_2),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_PLATEBODY_2),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_GREAVES_2),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_GLOVES_2),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_BOOTS_2)
            ));
            sets.add(new EquipmentSet("Shayzien tier 3", ItemID.SHAYZIEN_HELM_3,
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_HELM_3),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_PLATEBODY_3),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_GREAVES_3),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_GLOVES_3),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_BOOTS_3)
            ));
            sets.add(new EquipmentSet("Shayzien tier 4", ItemID.SHAYZIEN_HELM_4,
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_HELM_4),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_PLATEBODY_4),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_GREAVES_4),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_GLOVES_4),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_BOOTS_4)
            ));
            sets.add(new EquipmentSet("Shayzien tier 5", ItemID.SHAYZIEN_HELM_5,
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_HELM_5)
                            .NonWarningAlternatives(
                                    ItemID.SLAYER_HELMET, ItemID.SLAYER_HELMET_I, ItemID.BLACK_SLAYER_HELMET,
                                    ItemID.BLACK_SLAYER_HELMET_I, ItemID.GREEN_SLAYER_HELMET, ItemID.GREEN_SLAYER_HELMET_I,
                                    ItemID.RED_SLAYER_HELMET, ItemID.RED_SLAYER_HELMET_I, ItemID.PURPLE_SLAYER_HELMET,
                                    ItemID.PURPLE_SLAYER_HELMET_I, ItemID.TURQUOISE_SLAYER_HELMET, ItemID.TURQUOISE_SLAYER_HELMET_I,
                                    ItemID.HYDRA_SLAYER_HELMET, ItemID.HYDRA_SLAYER_HELMET_I, ItemID.TWISTED_SLAYER_HELMET,
                                    ItemID.TWISTED_SLAYER_HELMET_I, ItemID.SLAYER_HELMET_I_25177, ItemID.BLACK_SLAYER_HELMET_I_25179,
                                    ItemID.GREEN_SLAYER_HELMET_I_25181, ItemID.RED_SLAYER_HELMET_I_25183, ItemID.PURPLE_SLAYER_HELMET_I_25185,
                                    ItemID.TURQUOISE_SLAYER_HELMET_I_25187, ItemID.HYDRA_SLAYER_HELMET_I_25189, ItemID.TWISTED_SLAYER_HELMET_I_25191,
                                    ItemID.TZTOK_SLAYER_HELMET, ItemID.TZTOK_SLAYER_HELMET_I, ItemID.TZTOK_SLAYER_HELMET_I_25902,
                                    ItemID.VAMPYRIC_SLAYER_HELMET, ItemID.VAMPYRIC_SLAYER_HELMET_I, ItemID.VAMPYRIC_SLAYER_HELMET_I_25908,
                                    ItemID.TZKAL_SLAYER_HELMET, ItemID.TZKAL_SLAYER_HELMET_I, ItemID.TZKAL_SLAYER_HELMET_I_25914,
                                    ItemID.SLAYER_HELMET_I_26674, ItemID.BLACK_SLAYER_HELMET_I_26675, ItemID.GREEN_SLAYER_HELMET_I_26676,
                                    ItemID.RED_SLAYER_HELMET_I_26677, ItemID.PURPLE_SLAYER_HELMET_I_26678, ItemID.TURQUOISE_SLAYER_HELMET_I_26679,
                                    ItemID.HYDRA_SLAYER_HELMET_I_26680, ItemID.TWISTED_SLAYER_HELMET_I_26681, ItemID.TZTOK_SLAYER_HELMET_I_26682,
                                    ItemID.VAMPYRIC_SLAYER_HELMET_I_26683, ItemID.TZKAL_SLAYER_HELMET_I_26684),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_BODY_5),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_GREAVES_5),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_GLOVES_5),
                    new ItemSlot(aPlugin, ItemID.SHAYZIEN_BOOTS_5)
            ));
        }
        if (aConfig.Justicar()) {
            sets.add(new EquipmentSet("Justicar Armour", ItemID.JUSTICIAR_FACEGUARD,
                    new ItemSlot(aPlugin, ItemID.JUSTICIAR_FACEGUARD),
                    new ItemSlot(aPlugin, ItemID.JUSTICIAR_CHESTGUARD),
                    new ItemSlot(aPlugin, ItemID.JUSTICIAR_LEGGUARDS)
            ));
        }
        if (aConfig.Virtus()) {
            sets.add(new EquipmentSet("Virtus Robes", ItemID.VIRTUS_MASK,
                    new ItemSlot(aPlugin, ItemID.VIRTUS_MASK),
                    new ItemSlot(aPlugin, ItemID.VIRTUS_ROBE_TOP),
                    new ItemSlot(aPlugin, ItemID.VIRTUS_ROBE_BOTTOM)
            ));
        }
        if (aConfig.Swampbark()) {
            sets.add(new EquipmentSet("Swampbark Armour", ItemID.SWAMPBARK_HELM,
                    new ItemSlot(aPlugin, ItemID.SWAMPBARK_HELM),
                    new ItemSlot(aPlugin, ItemID.SWAMPBARK_BODY),
                    new ItemSlot(aPlugin, ItemID.SWAMPBARK_LEGS),
                    new ItemSlot(aPlugin, ItemID.SWAMPBARK_BOOTS),
                    new ItemSlot(aPlugin, ItemID.SWAMPBARK_GAUNTLETS)
            ));
        }
        if (aConfig.Bloodbark()) {
            sets.add(new EquipmentSet("Bloodbark Armour", ItemID.BLOODBARK_HELM,
                    new ItemSlot(aPlugin, ItemID.BLOODBARK_HELM),
                    new ItemSlot(aPlugin, ItemID.BLOODBARK_BODY),
                    new ItemSlot(aPlugin, ItemID.BLOODBARK_LEGS),
                    new ItemSlot(aPlugin, ItemID.BLOODBARK_BOOTS),
                    new ItemSlot(aPlugin, ItemID.BLOODBARK_GAUNTLETS)
            ));
        }
        if (aConfig.Inquisitor()) {
            sets.add(new EquipmentSet("Inquisitors Armour", ItemID.INQUISITORS_GREAT_HELM,
                    new ItemSlot(aPlugin, ItemID.INQUISITORS_GREAT_HELM, ItemID.INQUISITORS_GREAT_HELM_27195),
                    new ItemSlot(aPlugin, ItemID.INQUISITORS_HAUBERK, ItemID.INQUISITORS_HAUBERK_27196),
                    new ItemSlot(aPlugin, ItemID.INQUISITORS_PLATESKIRT, ItemID.INQUISITORS_PLATESKIRT_27197),
                    new AssociatedSlot(aPlugin, ItemID.INQUISITORS_MACE, ItemID.INQUISITORS_MACE_27198)
            ));
        }
        if (aConfig.Graceful()) {

            sets.add(new EquipmentSet("Graceful", ItemID.GRACEFUL_HOOD,
                    new ItemSlot(aPlugin, "Graceful hood"),
                    new ItemSlot(aPlugin, "Graceful top"),
                    new ItemSlot(aPlugin, "Graceful legs"),
                    new ItemSlot(aPlugin, "Graceful gloves"),
                    new ItemSlot(aPlugin, "Graceful boots"),
                    new ItemSlot(aPlugin, "Graceful cape")
                            .NonWarningAlternatives(ItemID.AGILITY_CAPE, ItemID.AGILITY_CAPET, ItemID.MAX_CAPE, ItemID.MAX_CAPE_13342)
            ));
        }
        if (aConfig.Gauntlet()) {
            sets.add(new EquipmentSet("Gauntlet T1 armour", ItemID.CRYSTAL_BODY_BASIC,
                    new ItemSlot(aPlugin, ItemID.CRYSTAL_HELM_BASIC, ItemID.CRYSTAL_HELM_ATTUNED, ItemID.CRYSTAL_HELM_PERFECTED),
                    new ItemSlot(aPlugin, ItemID.CRYSTAL_BODY_BASIC, ItemID.CRYSTAL_BODY_ATTUNED, ItemID.CRYSTAL_BODY_PERFECTED),
                    new ItemSlot(aPlugin, ItemID.CRYSTAL_LEGS_BASIC, ItemID.CRYSTAL_LEGS_ATTUNED, ItemID.CRYSTAL_LEGS_PERFECTED),
                    new AssociatedSlot(aPlugin,
                            ItemID.CRYSTAL_BOW_BASIC, ItemID.CRYSTAL_HALBERD_BASIC, ItemID.CRYSTAL_STAFF_BASIC,
                            ItemID.CRYSTAL_BOW_ATTUNED, ItemID.CRYSTAL_HALBERD_ATTUNED, ItemID.CRYSTAL_STAFF_ATTUNED,
                            ItemID.CRYSTAL_BOW_PERFECTED, ItemID.CRYSTAL_HALBERD_PERFECTED, ItemID.CRYSTAL_STAFF_PERFECTED)
            ));
            sets.add(new EquipmentSet("Corrupted Gauntlet T1 armour", ItemID.CORRUPTED_BODY_BASIC,
                    new ItemSlot(aPlugin, ItemID.CORRUPTED_HELM_BASIC, ItemID.CORRUPTED_HELM_ATTUNED, ItemID.CORRUPTED_HELM_PERFECTED),
                    new ItemSlot(aPlugin, ItemID.CORRUPTED_BODY_BASIC, ItemID.CORRUPTED_BODY_ATTUNED, ItemID.CORRUPTED_BODY_PERFECTED),
                    new ItemSlot(aPlugin, ItemID.CORRUPTED_LEGS_BASIC, ItemID.CORRUPTED_LEGS_ATTUNED, ItemID.CORRUPTED_LEGS_PERFECTED),
                    new AssociatedSlot(aPlugin,
                            ItemID.CORRUPTED_BOW_BASIC, ItemID.CORRUPTED_HALBERD_BASIC, ItemID.CORRUPTED_STAFF_BASIC,
                            ItemID.CORRUPTED_BOW_ATTUNED, ItemID.CORRUPTED_HALBERD_ATTUNED, ItemID.CORRUPTED_STAFF_ATTUNED,
                            ItemID.CORRUPTED_BOW_PERFECTED, ItemID.CORRUPTED_HALBERD_PERFECTED, ItemID.CORRUPTED_STAFF_PERFECTED)
            ));

            sets.add(new EquipmentSet("Gauntlet T2 armour", ItemID.CRYSTAL_BODY_ATTUNED,
                    new ItemSlot(aPlugin, ItemID.CRYSTAL_HELM_ATTUNED, ItemID.CRYSTAL_HELM_PERFECTED),
                    new ItemSlot(aPlugin, ItemID.CRYSTAL_BODY_ATTUNED, ItemID.CRYSTAL_BODY_PERFECTED),
                    new ItemSlot(aPlugin, ItemID.CRYSTAL_LEGS_ATTUNED, ItemID.CRYSTAL_LEGS_PERFECTED),
                    new AssociatedSlot(aPlugin,
                            ItemID.CRYSTAL_BOW_ATTUNED, ItemID.CRYSTAL_HALBERD_ATTUNED, ItemID.CRYSTAL_STAFF_ATTUNED,
                            ItemID.CRYSTAL_BOW_PERFECTED, ItemID.CRYSTAL_HALBERD_PERFECTED, ItemID.CRYSTAL_STAFF_PERFECTED)
            ));
            sets.add(new EquipmentSet("Corrupted Gauntlet T2 armour", ItemID.CORRUPTED_BODY_ATTUNED,
                    new ItemSlot(aPlugin, ItemID.CORRUPTED_HELM_ATTUNED, ItemID.CORRUPTED_HELM_PERFECTED),
                    new ItemSlot(aPlugin, ItemID.CORRUPTED_BODY_ATTUNED, ItemID.CORRUPTED_BODY_PERFECTED),
                    new ItemSlot(aPlugin, ItemID.CORRUPTED_LEGS_ATTUNED, ItemID.CORRUPTED_LEGS_PERFECTED),
                    new AssociatedSlot(aPlugin,
                            ItemID.CORRUPTED_BOW_ATTUNED, ItemID.CORRUPTED_HALBERD_ATTUNED, ItemID.CORRUPTED_STAFF_ATTUNED,
                            ItemID.CORRUPTED_BOW_PERFECTED, ItemID.CORRUPTED_HALBERD_PERFECTED, ItemID.CORRUPTED_STAFF_PERFECTED)
            ));

            sets.add(new EquipmentSet("Gauntlet T3 armour", ItemID.CRYSTAL_BODY_PERFECTED,
                    new ItemSlot(aPlugin, ItemID.CRYSTAL_HELM_PERFECTED),
                    new ItemSlot(aPlugin, ItemID.CRYSTAL_BODY_PERFECTED),
                    new ItemSlot(aPlugin, ItemID.CRYSTAL_LEGS_PERFECTED),
                    new AssociatedSlot(aPlugin,
                            ItemID.CRYSTAL_BOW_PERFECTED, ItemID.CRYSTAL_HALBERD_PERFECTED, ItemID.CRYSTAL_STAFF_PERFECTED)
            ));
            sets.add(new EquipmentSet("Corrupted Gauntlet T3 armour", ItemID.CORRUPTED_BODY_PERFECTED,
                    new ItemSlot(aPlugin, ItemID.CORRUPTED_HELM_PERFECTED),
                    new ItemSlot(aPlugin, ItemID.CORRUPTED_BODY_PERFECTED),
                    new ItemSlot(aPlugin, ItemID.CORRUPTED_LEGS_PERFECTED),
                    new AssociatedSlot(aPlugin,
                            ItemID.CORRUPTED_BOW_PERFECTED, ItemID.CORRUPTED_HALBERD_PERFECTED, ItemID.CORRUPTED_STAFF_PERFECTED)
            ));
        }

        return sets;
    }

    private static List<EquipmentSet> Barrows(PartialSetsPlugin aPlugin, PartialSetsConfig aConfig)
    {
        ArrayList<EquipmentSet> sets = new ArrayList<>();

        if (aConfig.Ahrims()) {
            sets.add(new EquipmentSet("Ahrims Robes", ItemID.AHRIMS_HOOD,
                    new ItemSlot(aPlugin, ItemID.AHRIMS_HOOD, ItemID.AHRIMS_HOOD_100, ItemID.AHRIMS_HOOD_75, ItemID.AHRIMS_HOOD_50, ItemID.AHRIMS_HOOD_25),
                    new ItemSlot(aPlugin, ItemID.AHRIMS_ROBETOP, ItemID.AHRIMS_ROBETOP_100, ItemID.AHRIMS_ROBETOP_75, ItemID.AHRIMS_ROBETOP_50, ItemID.AHRIMS_ROBETOP_25),
                    new ItemSlot(aPlugin, ItemID.AHRIMS_ROBESKIRT, ItemID.AHRIMS_ROBESKIRT_100, ItemID.AHRIMS_ROBESKIRT_75, ItemID.AHRIMS_ROBESKIRT_50, ItemID.AHRIMS_ROBESKIRT_25),
                    new AssociatedSlot(aPlugin, ItemID.AHRIMS_STAFF, ItemID.AHRIMS_STAFF_100, ItemID.AHRIMS_STAFF_75, ItemID.AHRIMS_STAFF_50, ItemID.AHRIMS_STAFF_25, ItemID.AHRIMS_STAFF_23653)
            ));
        }
        if (aConfig.Dharoks()) {
            sets.add(new EquipmentSet("Dharoks Armour", ItemID.DHAROKS_HELM,
                    new ItemSlot(aPlugin, ItemID.DHAROKS_HELM, ItemID.DHAROKS_HELM_100, ItemID.DHAROKS_HELM_75, ItemID.DHAROKS_HELM_50, ItemID.DHAROKS_HELM_25),
                    new ItemSlot(aPlugin, ItemID.DHAROKS_PLATEBODY, ItemID.DHAROKS_PLATEBODY_100, ItemID.DHAROKS_PLATEBODY_75, ItemID.DHAROKS_PLATEBODY_50, ItemID.DHAROKS_PLATEBODY_25),
                    new ItemSlot(aPlugin, ItemID.DHAROKS_PLATELEGS, ItemID.DHAROKS_PLATELEGS_100, ItemID.DHAROKS_PLATELEGS_75, ItemID.DHAROKS_PLATELEGS_50, ItemID.DHAROKS_PLATELEGS_25),
                    new AssociatedSlot(aPlugin, ItemID.DHAROKS_GREATAXE, ItemID.DHAROKS_GREATAXE_100, ItemID.DHAROKS_GREATAXE_75, ItemID.DHAROKS_GREATAXE_50, ItemID.DHAROKS_GREATAXE_25)
            ));
        }
        if (aConfig.Guthans()) {
            sets.add(new EquipmentSet("Guthans Armour", ItemID.GUTHANS_HELM,
                    new ItemSlot(aPlugin, ItemID.GUTHANS_HELM, ItemID.GUTHANS_HELM_100, ItemID.GUTHANS_HELM_75, ItemID.GUTHANS_HELM_50, ItemID.GUTHANS_HELM_25),
                    new ItemSlot(aPlugin, ItemID.GUTHANS_PLATEBODY, ItemID.GUTHANS_PLATEBODY_100, ItemID.GUTHANS_PLATEBODY_75, ItemID.GUTHANS_PLATEBODY_50, ItemID.GUTHANS_PLATEBODY_25),
                    new ItemSlot(aPlugin, ItemID.GUTHANS_CHAINSKIRT, ItemID.GUTHANS_CHAINSKIRT_100, ItemID.GUTHANS_CHAINSKIRT_75, ItemID.GUTHANS_CHAINSKIRT_50, ItemID.GUTHANS_CHAINSKIRT_25),
                    new AssociatedSlot(aPlugin, ItemID.GUTHANS_WARSPEAR, ItemID.GUTHANS_WARSPEAR_100, ItemID.GUTHANS_WARSPEAR_75, ItemID.GUTHANS_WARSPEAR_50, ItemID.GUTHANS_WARSPEAR_25)
            ));
        }
        if (aConfig.Karils()) {
            sets.add(new EquipmentSet("Karils Armour", ItemID.KARILS_COIF,
                    new ItemSlot(aPlugin, ItemID.KARILS_COIF, ItemID.KARILS_COIF_100, ItemID.KARILS_COIF_75, ItemID.KARILS_COIF_50, ItemID.KARILS_COIF_25),
                    new ItemSlot(aPlugin, ItemID.KARILS_LEATHERTOP, ItemID.KARILS_LEATHERTOP_100, ItemID.KARILS_LEATHERTOP_75, ItemID.KARILS_LEATHERTOP_50, ItemID.KARILS_LEATHERTOP_25),
                    new ItemSlot(aPlugin, ItemID.KARILS_LEATHERSKIRT, ItemID.KARILS_LEATHERSKIRT_100, ItemID.KARILS_LEATHERSKIRT_75, ItemID.KARILS_LEATHERSKIRT_50, ItemID.KARILS_LEATHERSKIRT_25),
                    new AssociatedSlot(aPlugin, ItemID.KARILS_CROSSBOW, ItemID.KARILS_CROSSBOW_100, ItemID.KARILS_CROSSBOW_75, ItemID.KARILS_CROSSBOW_50, ItemID.KARILS_CROSSBOW_25)
            ));
        }
        if (aConfig.Torags()) {
            sets.add(new EquipmentSet("Torags Armour", ItemID.TORAGS_HELM,
                    new ItemSlot(aPlugin, ItemID.TORAGS_HELM, ItemID.TORAGS_HELM_100, ItemID.TORAGS_HELM_75, ItemID.TORAGS_HELM_50, ItemID.TORAGS_HELM_25),
                    new ItemSlot(aPlugin, ItemID.TORAGS_PLATEBODY, ItemID.TORAGS_PLATEBODY_100, ItemID.TORAGS_PLATEBODY_75, ItemID.TORAGS_PLATEBODY_50, ItemID.TORAGS_PLATEBODY_25),
                    new ItemSlot(aPlugin, ItemID.TORAGS_PLATELEGS, ItemID.TORAGS_PLATELEGS_100, ItemID.TORAGS_PLATELEGS_75, ItemID.TORAGS_PLATELEGS_50, ItemID.TORAGS_PLATELEGS_25),
                    new AssociatedSlot(aPlugin, ItemID.TORAGS_HAMMERS, ItemID.TORAGS_HAMMERS_100, ItemID.TORAGS_HAMMERS_75, ItemID.TORAGS_HAMMERS_50, ItemID.TORAGS_HAMMERS_25)
            ));
        }
        if (aConfig.Veracs()) {
            sets.add(new EquipmentSet("Veracs Armour", ItemID.VERACS_HELM,
                    new ItemSlot(aPlugin, ItemID.VERACS_HELM, ItemID.VERACS_HELM_100, ItemID.VERACS_HELM_75, ItemID.VERACS_HELM_50, ItemID.VERACS_HELM_25),
                    new ItemSlot(aPlugin, ItemID.VERACS_BRASSARD, ItemID.VERACS_BRASSARD_100, ItemID.VERACS_BRASSARD_75, ItemID.VERACS_BRASSARD_50, ItemID.VERACS_BRASSARD_25),
                    new ItemSlot(aPlugin, ItemID.VERACS_PLATESKIRT, ItemID.VERACS_PLATESKIRT_100, ItemID.VERACS_PLATESKIRT_75, ItemID.VERACS_PLATESKIRT_50, ItemID.VERACS_PLATESKIRT_25),
                    new AssociatedSlot(aPlugin, ItemID.VERACS_FLAIL, ItemID.VERACS_FLAIL_100, ItemID.VERACS_FLAIL_75, ItemID.VERACS_FLAIL_50, ItemID.VERACS_FLAIL_25)
            ));
        }

        return sets;
    }

    private static List<EquipmentSet> Skills(PartialSetsPlugin aPlugin, PartialSetsConfig aConfig)
    {
        ArrayList<EquipmentSet> sets = new ArrayList<>();

        if (aConfig.Farming()) {
            sets.add(new EquipmentSet("Farming Set", ItemID.FARMERS_STRAWHAT,
                    new ItemSlot(aPlugin, ItemID.FARMERS_STRAWHAT, ItemID.FARMERS_STRAWHAT_13647),
                    new ItemSlot(aPlugin, ItemID.FARMERS_JACKET, ItemID.FARMERS_SHIRT),
                    new ItemSlot(aPlugin, ItemID.FARMERS_BORO_TROUSERS, ItemID.FARMERS_BORO_TROUSERS_13641),
                    new ItemSlot(aPlugin, ItemID.FARMERS_BOOTS, ItemID.FARMERS_BOOTS_13645),
                    new AssociatedSlot(aPlugin, ItemID.MAGIC_SECATEURS)
            ));
        }
        if (aConfig.Rogue()) {
            sets.add(new EquipmentSet("Rogue Equipment", ItemID.ROGUE_MASK,
                    new ItemSlot(aPlugin, ItemID.ROGUE_MASK),
                    new ItemSlot(aPlugin, ItemID.ROGUE_TOP),
                    new ItemSlot(aPlugin, ItemID.ROGUE_GLOVES),
                    new ItemSlot(aPlugin, ItemID.ROGUE_TROUSERS),
                    new ItemSlot(aPlugin, ItemID.ROGUE_BOOTS),
                    new AssociatedSlot(aPlugin, ItemID.DODGY_NECKLACE)
            ));
        }
        if (aConfig.Hunter()) {
            sets.add(new EquipmentSet("Hunter Outfit", ItemID.GUILD_HUNTER_HEADWEAR,
                    new ItemSlot(aPlugin, ItemID.GUILD_HUNTER_HEADWEAR),
                    new ItemSlot(aPlugin, ItemID.GUILD_HUNTER_TOP),
                    new ItemSlot(aPlugin, ItemID.GUILD_HUNTER_LEGS),
                    new ItemSlot(aPlugin, ItemID.GUILD_HUNTER_BOOTS)
            ));
        }
        if (aConfig.Lumberjack()) {
            sets.add(new EquipmentSet("Lumberjack Outfit", ItemID.LUMBERJACK_TOP,
                    new ItemSlot(aPlugin, ItemID.LUMBERJACK_HAT),
                    new ItemSlot(aPlugin, ItemID.LUMBERJACK_TOP),
                    new ItemSlot(aPlugin, ItemID.LUMBERJACK_LEGS),
                    new ItemSlot(aPlugin, ItemID.LUMBERJACK_BOOTS)
            ));
        }
        if (aConfig.Carpenter()) {
            sets.add(new EquipmentSet("Carpenters Outfit", ItemID.CARPENTERS_HELMET,
                    new ItemSlot(aPlugin, ItemID.CARPENTERS_HELMET),
                    new ItemSlot(aPlugin, ItemID.CARPENTERS_SHIRT),
                    new ItemSlot(aPlugin, ItemID.CARPENTERS_TROUSERS),
                    new ItemSlot(aPlugin, ItemID.CARPENTERS_BOOTS),
                    new AssociatedSlot(aPlugin, ItemID.AMYS_SAW, ItemID.IMCANDO_HAMMER),
                    new AssociatedSlot(aPlugin, ItemID.AMYS_SAW_OFFHAND, ItemID.IMCANDO_HAMMER_OFFHAND)
            ));
        }
        if (aConfig.Runecrafting()) {
            sets.add(new EquipmentSet("Raiments of the eye", ItemID.HAT_OF_THE_EYE,
                    new ItemSlot(aPlugin, ItemID.HAT_OF_THE_EYE, ItemID.HAT_OF_THE_EYE_BLUE, ItemID.HAT_OF_THE_EYE_RED, ItemID.HAT_OF_THE_EYE_GREEN),
                    new ItemSlot(aPlugin, ItemID.ROBE_TOP_OF_THE_EYE, ItemID.ROBE_TOP_OF_THE_EYE_BLUE, ItemID.ROBE_TOP_OF_THE_EYE_RED, ItemID.ROBE_TOP_OF_THE_EYE_GREEN),
                    new ItemSlot(aPlugin, ItemID.ROBE_BOTTOMS_OF_THE_EYE, ItemID.ROBE_BOTTOMS_OF_THE_EYE_BLUE, ItemID.ROBE_BOTTOMS_OF_THE_EYE_RED, ItemID.ROBE_BOTTOMS_OF_THE_EYE_GREEN),
                    new ItemSlot(aPlugin, ItemID.BOOTS_OF_THE_EYE)
            ));
        }
        if (aConfig.Fishing()) {
            sets.add(new EquipmentSet("Anglers outift", ItemID.ANGLER_BOOTS,
                    new ItemSlot(aPlugin, ItemID.ANGLER_HAT, ItemID.SPIRIT_ANGLER_HEADBAND),
                    new ItemSlot(aPlugin, ItemID.ANGLER_TOP, ItemID.SPIRIT_ANGLER_TOP),
                    new ItemSlot(aPlugin, ItemID.ANGLER_WADERS, ItemID.SPIRIT_ANGLER_WADERS),
                    new ItemSlot(aPlugin, ItemID.ANGLER_BOOTS, ItemID.SPIRIT_ANGLER_BOOTS),
                    new AssociatedSlot(aPlugin, ItemID.RADAS_BLESSING_1, ItemID.RADAS_BLESSING, ItemID.RADAS_BLESSING_2, ItemID.RADAS_BLESSING_3, ItemID.RADAS_BLESSING_4)
            ));
        }
        if (aConfig.Zealot()) {
            sets.add(new EquipmentSet("Zealots robes", ItemID.ZEALOTS_HELM,
                    new ItemSlot(aPlugin, ItemID.ZEALOTS_HELM),
                    new ItemSlot(aPlugin, ItemID.ZEALOTS_ROBE_TOP),
                    new ItemSlot(aPlugin, ItemID.ZEALOTS_ROBE_BOTTOM),
                    new ItemSlot(aPlugin, ItemID.ZEALOTS_BOOTS)
            ));
        }
        if (aConfig.Pyromancer()) {
            sets.add(new EquipmentSet("Pyromancers outfit", ItemID.PYROMANCER_ROBE,
                    new ItemSlot(aPlugin, ItemID.PYROMANCER_HOOD),
                    new ItemSlot(aPlugin, ItemID.PYROMANCER_GARB),
                    new ItemSlot(aPlugin, ItemID.PYROMANCER_ROBE),
                    new ItemSlot(aPlugin, ItemID.PYROMANCER_BOOTS),
                    new AssociatedSlot(aPlugin, ItemID.BRUMA_TORCH, ItemID.BRUMA_TORCH_OFFHAND)
            ));
        }

        return sets;
    }

    private static List<EquipmentSet> Moons(PartialSetsPlugin aPlugin, PartialSetsConfig aConfig)
    {
        ArrayList<EquipmentSet> sets = new ArrayList<>();

        if (aConfig.BloodMoon()) {
            sets.add(new EquipmentSet("Blood Moon Armour", ItemID.BLOOD_MOON_CHESTPLATE,
                    new ItemSlot(aPlugin, ItemID.BLOOD_MOON_CHESTPLATE, ItemID.BLOOD_MOON_CHESTPLATE_29043),
                    new ItemSlot(aPlugin, ItemID.BLOOD_MOON_HELM, ItemID.BLOOD_MOON_HELM_29047),
                    new ItemSlot(aPlugin, ItemID.BLOOD_MOON_TASSETS, ItemID.BLOOD_MOON_TASSETS_29045),
                    new AssociatedSlot(aPlugin, ItemID.DUAL_MACUAHUITL, ItemID.DUAL_MACUAHUITL_29850)
            ));
        }
        if (aConfig.BlueMoon()) {
            sets.add(new EquipmentSet("Blue Moon Armour", ItemID.BLUE_MOON_CHESTPLATE,
                    new ItemSlot(aPlugin, ItemID.BLUE_MOON_CHESTPLATE, ItemID.BLUE_MOON_CHESTPLATE_29037),
                    new ItemSlot(aPlugin, ItemID.BLUE_MOON_HELM, ItemID.BLUE_MOON_HELM_29041),
                    new ItemSlot(aPlugin, ItemID.BLUE_MOON_TASSETS, ItemID.BLUE_MOON_TASSETS_29039),
                    new AssociatedSlot(aPlugin, ItemID.BLUE_MOON_SPEAR, ItemID.BLUE_MOON_SPEAR_29849)
            ));
        }
        if (aConfig.EclipseMoon()) {
            sets.add(new EquipmentSet("Eclipse Moon Armour", ItemID.ECLIPSE_MOON_CHESTPLATE,
                    new ItemSlot(aPlugin, ItemID.ECLIPSE_MOON_CHESTPLATE, ItemID.ECLIPSE_MOON_CHESTPLATE_29031),
                    new ItemSlot(aPlugin, ItemID.ECLIPSE_MOON_HELM, ItemID.ECLIPSE_MOON_HELM_29035),
                    new ItemSlot(aPlugin, ItemID.ECLIPSE_MOON_TASSETS, ItemID.ECLIPSE_MOON_TASSETS_29033),
                    new AssociatedSlot(aPlugin, ItemID.ECLIPSE_ATLATL, ItemID.ECLIPSE_ATLATL_29851)
            ));
        }

        return sets;
    }
}
