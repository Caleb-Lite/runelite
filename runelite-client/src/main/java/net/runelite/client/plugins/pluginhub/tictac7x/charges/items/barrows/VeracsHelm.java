package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.barrows;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class VeracsHelm extends _BarrowsItem {
    public VeracsHelm(final Provider provider) {
        super("Verac's helmet", ItemId.VERACS_HELM, provider);
        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.VERACS_HELM).fixedCharges(1000),
            new TriggerItem(ItemId.VERACS_HELM_100),
            new TriggerItem(ItemId.VERACS_HELM_75),
            new TriggerItem(ItemId.VERACS_HELM_50),
            new TriggerItem(ItemId.VERACS_HELM_25),
            new TriggerItem(ItemId.VERACS_HELM_0).fixedCharges(0)
        };
    }
}