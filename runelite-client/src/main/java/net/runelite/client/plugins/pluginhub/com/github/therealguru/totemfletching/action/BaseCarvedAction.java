package net.runelite.client.plugins.pluginhub.com.github.therealguru.totemfletching.action;

import net.runelite.client.plugins.pluginhub.com.github.therealguru.totemfletching.model.Totem;
import net.runelite.client.plugins.pluginhub.com.github.therealguru.totemfletching.model.TotemVarbit;
import net.runelite.api.events.VarbitChanged;

public class BaseCarvedAction extends TotemAction {

    public BaseCarvedAction() {
        super(TotemVarbit.BASE_CARVED);
    }

    @Override
    public void onVarbitChanged(Totem totem, VarbitChanged varbitChanged) {
        totem.setCarved(varbitChanged.getValue() == 1);
    }
}
