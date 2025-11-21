package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.potions;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class P_FishingMix extends _Potion {
    public P_FishingMix(final Provider provider) {
        super("fishing_mix", new TriggerItem[]{
            new TriggerItem(ItemId.FISHING_MIX_1).fixedCharges(1),
            new TriggerItem(ItemId.FISHING_MIX_2).fixedCharges(2),
        }, provider);
    }
}
