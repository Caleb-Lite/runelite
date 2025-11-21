package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.utils;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnMenuEntryAdded;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ChargeId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;

public class U_RoyalSeedPod extends ChargedItem {
    public U_RoyalSeedPod(Provider provider) {
        super(TicTac7xChargesImprovedConfig.royal_seed_pod, ItemId.ROYAL_SEED_POD, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.ROYAL_SEED_POD).fixedCharges(ChargeId.UNLIMITED),
        };

        this.triggers = new TriggerBase[]{
            // Unify teleport.
            new OnMenuEntryAdded("Commune").replaceOption("Teleport"),

            // Hide destroy.
            new OnMenuEntryAdded("Destroy").hide(),
        };
    }
}
