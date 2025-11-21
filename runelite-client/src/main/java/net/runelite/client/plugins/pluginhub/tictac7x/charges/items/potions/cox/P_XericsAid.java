package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.potions.cox;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.items.potions._Potion;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class P_XericsAid extends _Potion {
    public P_XericsAid(final Provider provider) {
        super("cox_xerics_aid", new TriggerItem[]{
            new TriggerItem(ItemId.COX_XERICS_AID_1).fixedCharges(1),
            new TriggerItem(ItemId.COX_XERICS_AID_2).fixedCharges(2),
            new TriggerItem(ItemId.COX_XERICS_AID_3).fixedCharges(3),
            new TriggerItem(ItemId.COX_XERICS_AID_4).fixedCharges(4),
        }, provider);
    }
}
