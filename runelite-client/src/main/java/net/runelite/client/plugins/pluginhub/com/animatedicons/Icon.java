package net.runelite.client.plugins.pluginhub.com.animatedicons;

import com.google.common.collect.Sets;
import java.util.Set;
import lombok.Getter;
import lombok.ToString;
import net.runelite.api.ItemID;

@Getter
@ToString
public enum Icon
{
    DRAMEN_BRANCH(ItemID.DRAMEN_BRANCH),
    FIRE_CAPE(ItemID.FIRE_CAPE, ItemID.FIRE_CAPE_L, ItemID.FIRE_CAPE_10566),
    INFERNAL_CAPE(ItemID.INFERNAL_CAPE, ItemID.INFERNAL_CAPE_L, ItemID.INFERNAL_CAPE_21297, ItemID.INFERNAL_CAPE_23622),
    INFERNAL_MAX_CAPE(ItemID.INFERNAL_MAX_CAPE, ItemID.INFERNAL_MAX_CAPE_L, ItemID.INFERNAL_MAX_CAPE_21285),
    MAGIC_LOGS(ItemID.MAGIC_LOGS),
    MAGIC_PYRE_LOGS(ItemID.MAGIC_PYRE_LOGS),
    VOLCANIC_WHIP(ItemID.VOLCANIC_ABYSSAL_WHIP),
    VOLCANIC_WHIP_MIX(ItemID.VOLCANIC_WHIP_MIX);

    private final Set<Integer> itemIDs;
    private final int defaultID;

    Icon(Integer... ids)
    {
        this.defaultID = ids[0];
        this.itemIDs = Sets.newHashSet(ids);
    }

    Icon(int defaultID)
    {
        this.defaultID = defaultID;
        this.itemIDs = Sets.newHashSet(defaultID);
    }

    static Icon getAnimatedIcon(int id)
    {
        for (Icon icon : values())
        {
            if (icon.itemIDs.contains(id))
            {
                return icon;
            }
        }

        return null;
    }
}

