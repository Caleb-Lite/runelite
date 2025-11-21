package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.jewelry;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnChatMessage;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnItemContainerChanged;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemContainerId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class J_BraceletOfClay extends ChargedItem {
    public J_BraceletOfClay(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.bracelet_of_clay, ItemId.BRACELET_OF_CLAY, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.BRACELET_OF_CLAY).needsToBeEquipped(),
        };

        this.triggers = new TriggerBase[] {
            // Check.
            new OnChatMessage("You can mine (?<charges>.+) more pieces? of soft clay before your bracelet crumbles to dust.").setDynamicallyCharges(),

            // Mine clay.
            new OnItemContainerChanged(ItemContainerId.INVENTORY).isEquipped().onMenuOption("Mine").onMenuTarget("Clay rocks").consumer(() -> {
                final int clayBefore = provider.store.getPreviousInventoryItemQuantity(ItemId.SOFT_CLAY);
                final int clayAfter = provider.store.getInventoryItemQuantity(ItemId.SOFT_CLAY);
                decreaseCharges(clayAfter - clayBefore);
            }),

            // Mine soft clay.
            new OnItemContainerChanged(ItemContainerId.INVENTORY).isEquipped().onMenuOption("Mine").onMenuTarget("Soft clay rocks").consumer(() -> {
                final int clayBefore = provider.store.getPreviousInventoryItemQuantity(ItemId.SOFT_CLAY);
                final int clayAfter = provider.store.getInventoryItemQuantity(ItemId.SOFT_CLAY);

                // At least 2 soft clay was mined.
                if (clayAfter - clayBefore >= 2) {
                    decreaseCharges(1);
                }
            }),

            // Crumbles.
            new OnChatMessage("Your bracelet of clay crumbles to dust.").setFixedCharges(28),
        };
    }
}
