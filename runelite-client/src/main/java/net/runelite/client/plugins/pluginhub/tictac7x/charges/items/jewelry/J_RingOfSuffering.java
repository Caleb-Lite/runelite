package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.jewelry;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.enums.HitsplatGroup;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItemWithStatus;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnChatMessage;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnHitsplatApplied;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.enums.HitsplatTarget;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class J_RingOfSuffering extends ChargedItemWithStatus {
    public J_RingOfSuffering(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.ring_of_suffering, ItemId.RING_OF_SUFFERING_UNCHARGED, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.RING_OF_SUFFERING_UNCHARGED).fixedCharges(0),
            new TriggerItem(ItemId.RING_OF_SUFFERING_UNCHARGED_IMBUED_NMZ).fixedCharges(0),
            new TriggerItem(ItemId.RING_OF_SUFFERING_UNCHARGED_IMBUED_SW).fixedCharges(0),
            new TriggerItem(ItemId.RING_OF_SUFFERING_UNCHARGED_IMBUED_PVP).fixedCharges(0),
            new TriggerItem(ItemId.RING_OF_SUFFERING).needsToBeEquipped(),
            new TriggerItem(ItemId.RING_OF_SUFFERING_IMBUED_NMZ).needsToBeEquipped(),
            new TriggerItem(ItemId.RING_OF_SUFFERING_IMBUED_SW).needsToBeEquipped(),
            new TriggerItem(ItemId.RING_OF_SUFFERING_IMBUED_PVP).needsToBeEquipped(),
        };

        this.triggers = new TriggerBase[]{
            // Check
            new OnChatMessage("Your ring currently has (?<charges>.+) recoil charges? remaining. The recoil effect is currently enabled.").setDynamicallyCharges().onItemClick().activate(),
            new OnChatMessage("Your ring currently has (?<charges>.+) recoil charges? remaining. The recoil effect is currently disabled.").setDynamicallyCharges().onItemClick().deactivate(),

            // Charge
            new OnChatMessage("You load your ring with .+ rings? of recoil. It now has (?<charges>.+) recoil charges.").setDynamicallyCharges(),

            // Get hit.
            new OnHitsplatApplied(HitsplatTarget.SELF, HitsplatGroup.SUCCESSFUL).moreThanZeroDamage().isEquipped().isActivated().decreaseCharges(1),

            // Disable.
            new OnChatMessage("You disable the recoil effect of your ring.").deactivate(),

            // Enable.
            new OnChatMessage("You enable the recoil effect of your ring.").activate(),

            // Auto-charge.
            new OnChatMessage("The banker charges your Ring of suffering.* using (?<ringofrecoil>.+)x Ring of recoil.").matcherConsumer(m -> {
                final int ringOfRecoils = Integer.parseInt(m.group("ringofrecoil"));
                increaseCharges(ringOfRecoils * 40);
            }),
        };
    }
}
