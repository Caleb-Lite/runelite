package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.barrows;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class DharoksGreataxe extends _BarrowsItem {
    public DharoksGreataxe(final Provider provider) {
        super("Dharok's weapon", ItemId.DHAROKS_GREATAXE, provider);
        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.DHAROKS_GREATAXE).fixedCharges(1000),
            new TriggerItem(ItemId.DHAROKS_GREATAXE_100),
            new TriggerItem(ItemId.DHAROKS_GREATAXE_75),
            new TriggerItem(ItemId.DHAROKS_GREATAXE_50),
            new TriggerItem(ItemId.DHAROKS_GREATAXE_25),
            new TriggerItem(ItemId.DHAROKS_GREATAXE_0).fixedCharges(0),
        };
    }
}