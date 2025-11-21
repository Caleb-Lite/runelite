package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.barrows;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class GuthansWarspear extends _BarrowsItem {
    public GuthansWarspear(final Provider provider) {
        super("Guthan's weapon", ItemId.GUTHANS_WARSPEAR, provider);
        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.GUTHANS_WARSPEAR).fixedCharges(1000),
            new TriggerItem(ItemId.GUTHANS_WARSPEAR_100),
            new TriggerItem(ItemId.GUTHANS_WARSPEAR_75),
            new TriggerItem(ItemId.GUTHANS_WARSPEAR_50),
            new TriggerItem(ItemId.GUTHANS_WARSPEAR_25),
            new TriggerItem(ItemId.GUTHANS_WARSPEAR_0).fixedCharges(0),
        };
    }
}