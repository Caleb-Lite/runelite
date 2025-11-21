package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.potions;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

public class P_ZamorakBrew extends _Potion {
    public P_ZamorakBrew(final Provider provider) {
        super("zamorak_brew", new TriggerItem[]{
            new TriggerItem(ItemId.ZAMORAK_BREW_1).fixedCharges(1),
            new TriggerItem(ItemId.ZAMORAK_BREW_2).fixedCharges(2),
            new TriggerItem(ItemId.ZAMORAK_BREW_3).fixedCharges(3),
            new TriggerItem(ItemId.ZAMORAK_BREW_4).fixedCharges(4),
        }, provider);
    }
}
