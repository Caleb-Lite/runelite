package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.jewelry;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnChatMessage;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnResetDaily;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ChargeId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class J_DesertAmulet extends ChargedItem {
    public J_DesertAmulet(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.desert_amulet, ItemId.DESERT_AMULET_2, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.DESERT_AMULET_2),
            new TriggerItem(ItemId.DESERT_AMULET_3),
            new TriggerItem(ItemId.DESERT_AMULET_4).fixedCharges(ChargeId.UNLIMITED),
        };

        this.triggers = new TriggerBase[]{
            new OnChatMessage("You have already used your available teleports for today.").setFixedCharges(0),
            new OnResetDaily().specificItem(ItemId.DESERT_AMULET_2).setFixedCharges(1),
            new OnResetDaily().specificItem(ItemId.DESERT_AMULET_3).setFixedCharges(1),
        };
    }
}
