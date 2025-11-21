package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.capes;

import tictac7x.charges.item.triggers.*;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class C_MagicCape extends ChargedItem {
    public C_MagicCape(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.magic_cape, ItemId.MAGIC_CAPE, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.MAGIC_CAPE),
            new TriggerItem(ItemId.MAGIC_CAPE_TRIMMED)
        };

        this.triggers = new TriggerBase[] {
            // After spellbook swap.
            new OnChatMessage("You have changed your spellbook (?<used>.+)/(?<total>.+) times today.").setDifferenceCharges(),

            // Spellbook swap widget.
            new OnWidgetLoaded(219, 1, 0).text("Choose spellbook: \\((?<charges>.+)/5 left\\)").setDynamically(),

            // Daily reset.
            new OnResetDaily().setFixedCharges(5),
        };
    }
}
