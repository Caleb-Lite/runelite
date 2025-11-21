package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.moons;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnChatMessage;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnCombat;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class BlueMoonHelm extends _MoonItem {
    public BlueMoonHelm(
        final Provider provider
    ) {
        super("blue_helm", ItemId.BLUE_MOON_HELM, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.BLUE_MOON_HELM).fixedCharges(3000),
            new TriggerItem(ItemId.BLUE_MOON_HELM_DEGRADED),
            new TriggerItem(ItemId.BLUE_MOON_HELM_BROKEN).fixedCharges(0),
        };

        this.triggers = new TriggerBase[]{
            // Check.
            new OnChatMessage("Your Blue moon helm has (?<charges>.+) charges? remaining.").setDynamicallyCharges(),

            // In combat.
            new OnCombat(90).isEquipped().decreaseCharges(1),
        };
    }
}