package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.potions;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class P_MagicEssence extends _Potion {
    public P_MagicEssence(final Provider provider) {
        super("magic_essence", new TriggerItem[]{
            new TriggerItem(ItemId.MAGIC_ESSENCE_1).fixedCharges(1),
            new TriggerItem(ItemId.MAGIC_ESSENCE_2).fixedCharges(2),
            new TriggerItem(ItemId.MAGIC_ESSENCE_3).fixedCharges(3),
            new TriggerItem(ItemId.MAGIC_ESSENCE_4).fixedCharges(4),
        }, provider);
    }
}
