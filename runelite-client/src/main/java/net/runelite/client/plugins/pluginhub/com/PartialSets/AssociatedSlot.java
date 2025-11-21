package net.runelite.client.plugins.pluginhub.com.PartialSets;

import net.runelite.api.ItemContainer;
import net.runelite.client.game.ItemManager;

public class AssociatedSlot extends ItemSlot
{
    AssociatedSlot(PartialSetsPlugin aPlugin, Integer... aItems)
    {
        super(aPlugin, aItems);
    }

    @Override
    EquipStatus IsEquipped(ItemContainer aEquipment, ItemManager aManager)
    {
        if (!myPlugin.myConfig.Associated())
        {
            return EquipStatus.NotRequired;
        }

        switch (super.IsEquipped(aEquipment, aManager))
        {
            case Equipped:
            case EquippedNoWarning:
                return EquipStatus.EquippedNoWarning;
            case NotPresent:
                return EquipStatus.NotPresent;
            case NotRequired:
                return EquipStatus.NotRequired;
        }

        return EquipStatus.NotPresent;
    }

    @Override
    public String toColoredString(ItemManager aItemManager)
    {
        if (myPlugin.myConfig.Associated())
            return super.toColoredString(aItemManager);

        return "";
    }
}
