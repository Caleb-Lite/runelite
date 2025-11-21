package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.barrows;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class VeracsPlateskirt extends _BarrowsItem {
    public VeracsPlateskirt(final Provider provider) {
        super("Verac's skirt", ItemId.VERACS_PLATESKIRT, provider);
        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.VERACS_PLATESKIRT).fixedCharges(1000),
            new TriggerItem(ItemId.VERACS_PLATESKIRT_100),
            new TriggerItem(ItemId.VERACS_PLATESKIRT_75),
            new TriggerItem(ItemId.VERACS_PLATESKIRT_50),
            new TriggerItem(ItemId.VERACS_PLATESKIRT_25),
            new TriggerItem(ItemId.VERACS_PLATESKIRT_0).fixedCharges(0)
        };
    }
}