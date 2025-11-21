package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.jewelry;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnMenuEntryAdded;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class J_BurningAmulet extends ChargedItem {
    public J_BurningAmulet(
        final Provider provider
    ) {
        super(TicTac7xChargesImprovedConfig.burning_amulet, ItemId.BURNING_AMULET_1, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.BURNING_AMULET_1).fixedCharges(1),
            new TriggerItem(ItemId.BURNING_AMULET_2).fixedCharges(2),
            new TriggerItem(ItemId.BURNING_AMULET_3).fixedCharges(3),
            new TriggerItem(ItemId.BURNING_AMULET_4).fixedCharges(4),
            new TriggerItem(ItemId.BURNING_AMULET_5).fixedCharges(5),
        };

        this.triggers = new TriggerBase[]{
            new OnMenuEntryAdded("Rub").replaceOption("Teleport"),
        };
    }
}