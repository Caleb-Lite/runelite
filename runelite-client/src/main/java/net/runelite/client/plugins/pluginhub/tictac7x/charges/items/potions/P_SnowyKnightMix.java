package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.potions;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class P_SnowyKnightMix extends _Potion {
    public P_SnowyKnightMix(final Provider provider) {
        super("snowy_knight_mix", new TriggerItem[]{
            new TriggerItem(ItemId.SNOWY_KNIGHT_MIX_1).fixedCharges(1),
            new TriggerItem(ItemId.SNOWY_KNIGHT_MIX_2).fixedCharges(2),
        }, provider);
    }
}
