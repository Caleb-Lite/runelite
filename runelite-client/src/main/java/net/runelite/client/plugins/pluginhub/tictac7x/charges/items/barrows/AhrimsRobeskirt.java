package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.barrows;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class AhrimsRobeskirt extends _BarrowsItem {
    public AhrimsRobeskirt(final Provider provider) {
        super("Ahrim's skirt", ItemId.AHRIMS_ROBESKIRT, provider);
        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.AHRIMS_ROBESKIRT).fixedCharges(1000),
            new TriggerItem(ItemId.AHRIMS_ROBESKIRT_100),
            new TriggerItem(ItemId.AHRIMS_ROBESKIRT_75),
            new TriggerItem(ItemId.AHRIMS_ROBESKIRT_50),
            new TriggerItem(ItemId.AHRIMS_ROBESKIRT_25),
            new TriggerItem(ItemId.AHRIMS_ROBESKIRT_0).fixedCharges(0),
        };
    }
}