package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.weapons;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnChatMessage;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnGraphicChanged;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class W_IbansStaff extends ChargedItem {
    public W_IbansStaff(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.ibans_staff, ItemId.IBANS_STAFF, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.IBANS_STAFF),
            new TriggerItem(ItemId.IBANS_STAFF_BROKEN),
            new TriggerItem(ItemId.IBANS_STAFF_UPGRADED),
        };

        this.triggers = new TriggerBase[]{
            // Check.
            new OnChatMessage("You have (?<charges>.+) charges left on the staff.").setDynamicallyCharges().onItemClick(),

            // Attack.
            new OnGraphicChanged(87).isEquipped().decreaseCharges(1),
        };
    }
}
