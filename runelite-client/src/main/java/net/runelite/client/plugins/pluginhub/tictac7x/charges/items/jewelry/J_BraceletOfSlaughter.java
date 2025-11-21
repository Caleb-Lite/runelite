package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.jewelry;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnChatMessage;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class J_BraceletOfSlaughter extends ChargedItem {
    public J_BraceletOfSlaughter(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.bracelet_of_slaughter, ItemId.BRACELET_OF_SLAUGHTER, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.BRACELET_OF_SLAUGHTER).needsToBeEquipped(),
        };

        this.triggers = new TriggerBase[] {
            // Check.
            new OnChatMessage("Your bracelet of slaughter has (?<charges>.+) charges? left.").setDynamicallyCharges(),

            // Charge used.
            new OnChatMessage("Your bracelet of slaughter prevents your slayer count from decreasing. It has (?<charges>.+) charges? left.").setDynamicallyCharges(),

            // Bracelet fully used.
            new OnChatMessage("Your bracelet of slaughter prevents your slayer count from decreasing. It then crumbles to dust.").setFixedCharges(30),

            // Break.
            new OnChatMessage("The bracelet shatters. Your next bracelet of slaughter will start afresh from (?<charges>.+) charges.").setDynamicallyCharges(),
        };
    }
}
