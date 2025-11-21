package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.potions;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class P_ExtendedSuperAntifireMix extends _Potion {
    public P_ExtendedSuperAntifireMix(final Provider provider) {
        super("extended_super_antifire_mix", new TriggerItem[]{
            new TriggerItem(ItemId.EXTENDED_SUPER_ANTIFIRE_MIX_1).fixedCharges(1),
            new TriggerItem(ItemId.EXTENDED_SUPER_ANTIFIRE_MIX_2).fixedCharges(2),
        }, provider);
    }
}
