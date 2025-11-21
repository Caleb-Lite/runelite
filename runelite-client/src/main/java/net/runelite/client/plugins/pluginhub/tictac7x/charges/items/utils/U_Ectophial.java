package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.utils;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnMenuEntryAdded;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;

public class U_Ectophial extends ChargedItem {
    public U_Ectophial(Provider provider) {
        super(TicTac7xChargesImprovedConfig.ectophial, ItemId.ECTOPHIAL, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.ECTOPHIAL_UNCHARGED).fixedCharges(0),
            new TriggerItem(ItemId.ECTOPHIAL).fixedCharges(1),
        };

        this.triggers = new TriggerBase[]{
            // Unify teleport.
            new OnMenuEntryAdded("Empty").replaceOption("Teleport"),
        };
    }
}
