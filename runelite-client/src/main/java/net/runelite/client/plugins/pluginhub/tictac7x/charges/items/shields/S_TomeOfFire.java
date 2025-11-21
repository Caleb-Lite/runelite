package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.shields;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnChatMessage;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnGraphicChanged;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class S_TomeOfFire extends ChargedItem {
    public S_TomeOfFire(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.tome_of_fire, ItemId.TOME_OF_FIRE, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.TOME_OF_FIRE_UNCHARGED).fixedCharges(0),
            new TriggerItem(ItemId.TOME_OF_FIRE).needsToBeEquipped(),
        };

        this.triggers = new TriggerBase[] {
            // Check.
            new OnChatMessage("Your tome has been charged with (Burnt|Searing) Pages. It currently holds (?<charges>.+) charges?.").setDynamicallyCharges().onItemClick(),

            // Attack with regular spellbook fire spells.
            new OnGraphicChanged(99, 126, 129, 155, 1464).isEquipped().decreaseCharges(1),

            // Auto-charge.
            new OnChatMessage("The banker charges your Tome of fire using (?<burntpage>.+)x Burnt page.").matcherConsumer(m -> {
                final int burntPages = Integer.parseInt(m.group("burntpage"));
                increaseCharges(burntPages * 20);
            }),
        };
    }
}
