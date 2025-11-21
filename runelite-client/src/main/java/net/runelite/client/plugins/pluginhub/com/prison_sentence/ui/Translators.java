package net.runelite.client.plugins.pluginhub.com.prison_sentence.ui;

import net.runelite.client.plugins.pluginhub.com.prison_sentence.enums.PrisonType;
import net.runelite.client.plugins.pluginhub.com.prison_sentence.enums.TimePeriod;
import net.runelite.api.ItemID;
import net.runelite.api.VarPlayer;
import net.runelite.api.Varbits;

public class Translators {

    public static int IconFromPrisonType(PrisonType aPrisonType)
    {
        switch (aPrisonType)
        {
            case GAUNTLET:          return ItemID.YOUNGLLEF;
            case CORRUPTED_GAUNTLET: return ItemID.CORRUPTED_YOUNGLLEF;
            case CHAMBERS_OF_XERIC:   return ItemID.OLMLET;
            //case THEATRE_OF_BLOOD:    return ItemID.LIL_ZIK;
            //case TOOMBS_OF_AMASCUT:   return ItemID.TUMEKENS_GUARDIAN;

            default:                return ItemID.CABBAGE;
        }
    }

    public static Integer VarpIdFromPrisonType(PrisonType aPrisonType)
    {
        switch (aPrisonType)
        {
            case GAUNTLET:            return 2353;
            case CORRUPTED_GAUNTLET:  return 2354;
            case CHAMBERS_OF_XERIC:   return 1532;
            //case THEATRE_OF_BLOOD:    return -1; // TODO
            //case TOOMBS_OF_AMASCUT:   return -1; // TODO

            default:                return -1;
        }
    }

    public static String FriendlyName(PrisonType aPrisonType)
    {
        switch (aPrisonType)
        {
            case GAUNTLET:              return "Gauntlet";
            case CORRUPTED_GAUNTLET:    return "Corrupted Guantlet";
            case CHAMBERS_OF_XERIC:     return "Chambers of Xeric";
            //case THEATRE_OF_BLOOD:    return -1; // TODO
            //case TOOMBS_OF_AMASCUT:   return -1; // TODO

            default:                    return "Unkown";
        }
    }

    public static String FriendlyName(TimePeriod aTimePeriod)
    {
        switch (aTimePeriod)
        {
            case DAILY:     return "daily";
            case WEEKLY:    return "weekly";
            case MONTHLY:   return "monthly";

            default:        return "Unkown";
        }
    }
}
