package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.jewelry;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class J_PhoenixNecklace extends ChargedItem {
    public J_PhoenixNecklace(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.phoenix_necklace, ItemId.PHOENIX_NECKLACE, provider);
        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.PHOENIX_NECKLACE).fixedCharges(1).needsToBeEquipped(),
        };
    }
}
