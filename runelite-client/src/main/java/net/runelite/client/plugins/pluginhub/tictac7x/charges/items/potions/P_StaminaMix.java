package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.potions;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class P_StaminaMix extends _Potion {
    public P_StaminaMix(final Provider provider) {
        super("stamina_mix", new TriggerItem[]{
            new TriggerItem(ItemId.STAMINA_MIX_1).fixedCharges(1),
            new TriggerItem(ItemId.STAMINA_MIX_2).fixedCharges(2),
        }, provider);
    }
}
