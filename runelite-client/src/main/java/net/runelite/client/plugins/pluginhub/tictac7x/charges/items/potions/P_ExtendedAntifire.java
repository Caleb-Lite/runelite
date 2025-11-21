package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.potions;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class P_ExtendedAntifire extends _Potion {
    public P_ExtendedAntifire(final Provider provider) {
        super("extended_antifire", new TriggerItem[]{
            new TriggerItem(ItemId.EXTENDED_ANTIFIRE_1).fixedCharges(1),
            new TriggerItem(ItemId.EXTENDED_ANTIFIRE_2).fixedCharges(2),
            new TriggerItem(ItemId.EXTENDED_ANTIFIRE_3).fixedCharges(3),
            new TriggerItem(ItemId.EXTENDED_ANTIFIRE_4).fixedCharges(4),
        }, provider);
    }
}
