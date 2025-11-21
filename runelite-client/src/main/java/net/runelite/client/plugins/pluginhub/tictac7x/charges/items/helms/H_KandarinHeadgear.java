package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.helms;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnChatMessage;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnGraphicChanged;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnResetDaily;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ChargeId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class H_KandarinHeadgear extends ChargedItem {
    public H_KandarinHeadgear(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.kandarin_headgear, ItemId.KANDARIN_HEADGEAR_3, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.KANDARIN_HEADGEAR_3),
            new TriggerItem(ItemId.KANDARIN_HEADGEAR_4).fixedCharges(ChargeId.UNLIMITED),
        };

        this.triggers = new TriggerBase[] {
            // Try to teleport while empty.
            new OnChatMessage("You have already used your available teleports for today. Your headgear will recharge tomorrow.").onItemClick().setFixedCharges(0),

            // Teleport.
            new OnGraphicChanged(111).onItemClick().decreaseCharges(1),

            // Daily reset.
            new OnResetDaily().specificItem(ItemId.KANDARIN_HEADGEAR_3).setFixedCharges(1),
        };
    }
}
