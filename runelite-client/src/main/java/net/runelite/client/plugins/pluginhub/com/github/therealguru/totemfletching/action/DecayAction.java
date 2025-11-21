package net.runelite.client.plugins.pluginhub.com.github.therealguru.totemfletching.action;

import net.runelite.client.plugins.pluginhub.com.github.therealguru.totemfletching.model.Totem;
import net.runelite.client.plugins.pluginhub.com.github.therealguru.totemfletching.model.TotemVarbit;
import net.runelite.api.events.VarbitChanged;

public class DecayAction extends TotemAction {

    public DecayAction() {
        super(TotemVarbit.DECAY);
    }

    @Override
    public void onVarbitChanged(Totem totem, VarbitChanged varbitChanged) {
        totem.setDecay(varbitChanged.getValue());
    }
}
