package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.barrows;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class AhrimsStaff extends _BarrowsItem {
    public AhrimsStaff(final Provider provider) {
        super("Ahrim's weapon", ItemId.AHRIMS_STAFF, provider);
        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.AHRIMS_STAFF).fixedCharges(1000),
            new TriggerItem(ItemId.AHRIMS_STAFF_100),
            new TriggerItem(ItemId.AHRIMS_STAFF_75),
            new TriggerItem(ItemId.AHRIMS_STAFF_50),
            new TriggerItem(ItemId.AHRIMS_STAFF_25),
            new TriggerItem(ItemId.AHRIMS_STAFF_0).fixedCharges(0),
        };
    }
}