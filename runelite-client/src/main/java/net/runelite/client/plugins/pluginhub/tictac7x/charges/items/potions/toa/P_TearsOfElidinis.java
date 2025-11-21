package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.potions.toa;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.items.potions._Potion;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class P_TearsOfElidinis extends _Potion {
    public P_TearsOfElidinis(final Provider provider) {
        super("toa_tears_of_elidinis", new TriggerItem[]{
            new TriggerItem(ItemId.TOA_TEARS_OF_ELIDINIS_1).fixedCharges(1),
            new TriggerItem(ItemId.TOA_TEARS_OF_ELIDINIS_2).fixedCharges(2),
            new TriggerItem(ItemId.TOA_TEARS_OF_ELIDINIS_3).fixedCharges(3),
            new TriggerItem(ItemId.TOA_TEARS_OF_ELIDINIS_4).fixedCharges(4),
        }, provider);
    }
}
