package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.weapons;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.AnimationId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnAnimationChanged;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnChatMessage;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class W_CrystalBow extends ChargedItem {
    public W_CrystalBow(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.crystal_bow, ItemId.CRYSTAL_BOW, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.CRYSTAL_BOW_UNCHARGED).fixedCharges(0),
            new TriggerItem(ItemId.CRYSTAL_BOW),
            new TriggerItem(ItemId.CRYSTAL_BOW_FULL).fixedCharges(2500),
        };

        this.triggers = new TriggerBase[] {
            // Check.
            new OnChatMessage("Your crystal bow has (?<charges>.+) charges? remaining.").setDynamicallyCharges(),

            // Attack.
            new OnAnimationChanged(AnimationId.HUMAN_BOW).isEquipped().decreaseCharges(1),
        };
    }
}
