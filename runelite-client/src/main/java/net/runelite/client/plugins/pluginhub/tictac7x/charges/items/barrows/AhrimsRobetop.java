package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.barrows;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class AhrimsRobetop extends _BarrowsItem {
    public AhrimsRobetop(final Provider provider) {
        super("Ahrim's body", ItemId.AHRIMS_ROBETOP, provider);
        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.AHRIMS_ROBETOP).fixedCharges(1000),
            new TriggerItem(ItemId.AHRIMS_ROBETOP_100),
            new TriggerItem(ItemId.AHRIMS_ROBETOP_75),
            new TriggerItem(ItemId.AHRIMS_ROBETOP_50),
            new TriggerItem(ItemId.AHRIMS_ROBETOP_25),
            new TriggerItem(ItemId.AHRIMS_ROBETOP_0).fixedCharges(0),
        };
    }
}