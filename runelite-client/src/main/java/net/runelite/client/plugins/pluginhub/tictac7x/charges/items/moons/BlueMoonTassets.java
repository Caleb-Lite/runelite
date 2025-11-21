package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.moons;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnChatMessage;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnCombat;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class BlueMoonTassets extends _MoonItem {
    public BlueMoonTassets(
        final Provider provider
    ) {
        super("blue_tassets", ItemId.BLUE_MOON_TASSETS, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.BLUE_MOON_TASSETS).fixedCharges(3000),
            new TriggerItem(ItemId.BLUE_MOON_TASSETS_DEGRADED),
            new TriggerItem(ItemId.BLUE_MOON_TASSETS_BROKEN).fixedCharges(0),
        };

        this.triggers = new TriggerBase[]{
            // Check.
            new OnChatMessage("Your Blue moon tassets has (?<charges>.+) charges? remaining.").setDynamicallyCharges(),

            // In combat.
            new OnCombat(90).isEquipped().decreaseCharges(1),
        };
    }
}