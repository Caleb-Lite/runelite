package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.utils;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ChargeId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class U_EternalTeleportCrystal extends ChargedItem {
    public U_EternalTeleportCrystal(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.eternal_teleport_crystal, ItemId.ETERNAL_TELEPORT_CRYSTAL, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.ETERNAL_TELEPORT_CRYSTAL).fixedCharges(ChargeId.UNLIMITED),
        };
    }
}
