package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.barrows;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class KarilsCrossbow extends _BarrowsItem {
    public KarilsCrossbow(final Provider provider) {
        super("Karil's weapon", ItemId.KARILS_CROSSBOW, provider);
        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.KARILS_CROSSBOW).fixedCharges(1000),
            new TriggerItem(ItemId.KARILS_CROSSBOW_100),
            new TriggerItem(ItemId.KARILS_CROSSBOW_75),
            new TriggerItem(ItemId.KARILS_CROSSBOW_50),
            new TriggerItem(ItemId.KARILS_CROSSBOW_25),
            new TriggerItem(ItemId.KARILS_CROSSBOW_0).fixedCharges(0)
        };
    }
}