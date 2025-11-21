package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.utils;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class U_OgreBellows extends ChargedItem {
    public U_OgreBellows(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.ogre_bellows, ItemId.OGRE_BELLOWS_0, provider);
        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.OGRE_BELLOWS_0).fixedCharges(0),
            new TriggerItem(ItemId.OGRE_BELLOWS_1).fixedCharges(1),
            new TriggerItem(ItemId.OGRE_BELLOWS_2).fixedCharges(2),
            new TriggerItem(ItemId.OGRE_BELLOWS_3).fixedCharges(3),
        };
    }
}
