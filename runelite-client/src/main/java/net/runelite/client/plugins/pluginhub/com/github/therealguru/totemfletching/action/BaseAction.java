package net.runelite.client.plugins.pluginhub.com.github.therealguru.totemfletching.action;

import net.runelite.client.plugins.pluginhub.com.github.therealguru.totemfletching.model.Totem;
import net.runelite.client.plugins.pluginhub.com.github.therealguru.totemfletching.model.TotemVarbit;
import net.runelite.api.events.VarbitChanged;

public class BaseAction extends TotemAction {

    public BaseAction() {
        super(TotemVarbit.BASE);
    }

    @Override
    public void onVarbitChanged(Totem totem, VarbitChanged varbitChanged) {
        totem.setBase(varbitChanged.getValue());
    }
}
