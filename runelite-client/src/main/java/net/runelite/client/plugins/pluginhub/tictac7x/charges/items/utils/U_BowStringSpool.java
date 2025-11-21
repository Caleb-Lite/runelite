package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.utils;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnVarbitChanged;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.VarbitId;

public class U_BowStringSpool extends ChargedItem {
    public U_BowStringSpool(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.bow_string_spool, ItemId.BOW_STRING_SPOOL, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.BOW_STRING_SPOOL)
        };

        this.triggers = new TriggerBase[]{
            new OnVarbitChanged(VarbitId.BOW_STRING_SPOOL_CHARGES).setDynamically(),
        };
    }
}
