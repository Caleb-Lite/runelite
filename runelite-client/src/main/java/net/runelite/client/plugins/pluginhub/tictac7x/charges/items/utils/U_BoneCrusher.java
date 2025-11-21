package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.utils;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.api.Skill;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItemWithStatus;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnChatMessage;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnMenuEntryAdded;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnXpDrop;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class U_BoneCrusher extends ChargedItemWithStatus {
    public U_BoneCrusher(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.bonecrusher, ItemId.BONECRUSHER, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.BONECRUSHER),
            new TriggerItem(ItemId.BONECRUSHER_NECKLACE)
        };

        this.triggers = new TriggerBase[] {
            // Check.
            new OnChatMessage("The bonecrusher( necklace)? has no charges.").setFixedCharges(0),
            new OnChatMessage("The bonecrusher( necklace)? has one charge.").setFixedCharges(1),
            new OnChatMessage("(The|Your) bonecrusher( necklace)? has (?<charges>.+) charges?( left)?. It is active").setDynamicallyCharges().activate(),
            new OnChatMessage("(The|Your) bonecrusher( necklace)? has (?<charges>.+) charges?( left)?. It has been deactivated").setDynamicallyCharges().deactivate(),
            new OnChatMessage("(The|Your) bonecrusher( necklace)? has (?<charges>.+) charges?( left)?.").setDynamicallyCharges(),

            // Uncharge.
            new OnChatMessage("You remove all the charges from the bonecrusher( necklace)?.").setFixedCharges(0),

            // Ran out.
            new OnChatMessage("Your bonecrusher( necklace)? has run out of charges.").setFixedCharges(0),

            // Activate.
            new OnChatMessage("The bonecrusher( necklace)? has been deactivated").deactivate(),

            // Deactivate.
            new OnChatMessage("The bonecrusher( necklace)? is active").activate(),

            // Automatic bury.
            new OnXpDrop(Skill.PRAYER).isActivated().decreaseCharges(1),

            // Auto-charge.
            new OnChatMessage("The banker charges your Bonecrusher( necklace)? using (?<ectotoken>.+)x Ecto-token.").matcherConsumer(m -> {
                final int ectoTokens = Integer.parseInt(m.group("ectotoken"));
                increaseCharges(ectoTokens * 25);
            }),

            // Hide destroy.
            new OnMenuEntryAdded("Destroy").hide(),
        };
    }
}
