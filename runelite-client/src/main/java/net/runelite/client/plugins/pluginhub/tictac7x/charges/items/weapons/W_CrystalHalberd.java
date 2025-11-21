package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.weapons;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnAnimationChanged;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.AnimationId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnChatMessage;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnHitsplatApplied;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

import static tictac7x.charges.store.enums.HitsplatTarget.ENEMY;

public class W_CrystalHalberd extends ChargedItem {
    public W_CrystalHalberd(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.crystal_halberd, ItemId.CRYSTAL_HALBERD, provider);
        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.CRYSTAL_HALBERD_UNCHARGED).fixedCharges(0),
            new TriggerItem(ItemId.CRYSTAL_HALBERD),
            new TriggerItem(ItemId.CRYSTAL_HALBERD_FULL).fixedCharges(2500),
        };
        this.triggers = new TriggerBase[]{
            // Check.
            new OnChatMessage("Your crystal halberd has (?<charges>.+) charges? remaining.").setDynamicallyCharges(),

            // Attack with stab.
            new OnAnimationChanged(AnimationId.HUMAN_SPEAR_SPIKE).isEquipped().decreaseCharges(1),

            // Attack with slash.
            new OnAnimationChanged(AnimationId.HUMAN_SCYTHE_SWEEP).isEquipped().decreaseCharges(1),
        };
    }
}
