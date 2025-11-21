package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.potions;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class P_MoonlightMothMix extends _Potion {
    public P_MoonlightMothMix(final Provider provider) {
        super("moonlight_moth_mix", new TriggerItem[]{
            new TriggerItem(ItemId.MOONLIGHT_MOTH_MIX_1).fixedCharges(1),
            new TriggerItem(ItemId.MOONLIGHT_MOTH_MIX_2).fixedCharges(2),
        }, provider);
    }
}
