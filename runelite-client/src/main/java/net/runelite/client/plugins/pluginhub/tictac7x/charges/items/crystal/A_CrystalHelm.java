package net.runelite.client.plugins.pluginhub.tictac7x.charges.items.crystal;

import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.enums.HitsplatGroup;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.ids.ItemId;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.TicTac7xChargesImprovedConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.ChargedItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnChatMessage;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.OnHitsplatApplied;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerBase;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.item.triggers.TriggerItem;
import net.runelite.client.plugins.pluginhub.tictac7x.charges.store.Provider;

import static tictac7x.charges.store.enums.HitsplatTarget.SELF;

public class A_CrystalHelm extends ChargedItem {
    public A_CrystalHelm(final Provider provider) {
        super(TicTac7xChargesImprovedConfig.crystal_helm, ItemId.CRYSTAL_HELM, provider);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemId.CRYSTAL_HELM),
            new TriggerItem(ItemId.CRYSTAL_HELM_HEFIN),
            new TriggerItem(ItemId.CRYSTAL_HELM_ITHELL),
            new TriggerItem(ItemId.CRYSTAL_HELM_IORWERTH),
            new TriggerItem(ItemId.CRYSTAL_HELM_TRAHAEARN),
            new TriggerItem(ItemId.CRYSTAL_HELM_CADARN),
            new TriggerItem(ItemId.CRYSTAL_HELM_CRWYS),
            new TriggerItem(ItemId.CRYSTAL_HELM_AMLODD),
            new TriggerItem(ItemId.CRYSTAL_HELM_INACTIVE).fixedCharges(0),
            new TriggerItem(ItemId.CRYSTAL_HELM_HEFIN_INACTIVE).fixedCharges(0),
            new TriggerItem(ItemId.CRYSTAL_HELM_ITHELL_INACTIVE).fixedCharges(0),
            new TriggerItem(ItemId.CRYSTAL_HELM_IORWERTH_INACTIVE).fixedCharges(0),
            new TriggerItem(ItemId.CRYSTAL_HELM_TRAHAEARN_INACTIVE).fixedCharges(0),
            new TriggerItem(ItemId.CRYSTAL_HELM_CADARN_INACTIVE).fixedCharges(0),
            new TriggerItem(ItemId.CRYSTAL_HELM_CRWYS_INACTIVE).fixedCharges(0),
            new TriggerItem(ItemId.CRYSTAL_HELM_AMLODD_INACTIVE).fixedCharges(0),
        };

        this.triggers = new TriggerBase[]{
            new OnChatMessage("Your crystal helm has (?<charges>.+) charges? remaining").setDynamicallyCharges().onItemClick(),
            new OnHitsplatApplied(SELF, HitsplatGroup.SUCCESSFUL).isEquipped().decreaseCharges(1)
        };
    }
}
