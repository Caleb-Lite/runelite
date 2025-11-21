package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.jewelry;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnChatMessage;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnWidgetLoaded;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class J_DodgyNecklace extends ChargedItem {
    public J_DodgyNecklace(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.dodgy_necklace, ItemId.DODGY_NECKLACE, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.DODGY_NECKLACE).needsToBeEquipped(),
        };
        
        this.triggers = new TriggerBase[] {
            // Check.
            new OnChatMessage("Your dodgy necklace has (?<charges>.+) charges? left.").setDynamicallyCharges(),

            // Protects.
            new OnChatMessage("Your dodgy necklace protects you. It has (?<charges>.+) charges? left.").setDynamicallyCharges(),

            // Breaks.
            new OnChatMessage("Your dodgy necklace protects you. It then crumbles to dust.").setFixedCharges(10),

            // Break.
            new OnChatMessage("The necklace shatters. Your next dodgy necklace will start afresh from (?<charges>.+) charges.").setDynamicallyCharges(),

            new OnWidgetLoaded(219, 1, 0).text("Status: (?<charges>.+) charges? left.").setDynamically().onItemClick(),
        };
    }
}
