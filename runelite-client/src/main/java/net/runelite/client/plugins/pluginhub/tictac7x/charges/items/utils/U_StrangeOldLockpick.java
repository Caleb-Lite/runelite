package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.utils;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnChatMessage;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class U_StrangeOldLockpick extends ChargedItem {
    public U_StrangeOldLockpick(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.strange_old_lockpick, ItemId.STRANGE_OLD_LOCKPICK, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.STRANGE_OLD_LOCKPICK).fixedCharges(50),
            new TriggerItem(ItemId.STRANGE_OLD_LOCKPICK_DEGRADED),
        };

        this.triggers = new TriggerBase[] {
            new OnChatMessage("Your Strange old lockpick( now)? has (?<charges>.+) charges? remaining.").setDynamicallyCharges(),
            new OnChatMessage("The Strange old lockpick crumbles to dust as you use it one last time."),
        };
    }
}
