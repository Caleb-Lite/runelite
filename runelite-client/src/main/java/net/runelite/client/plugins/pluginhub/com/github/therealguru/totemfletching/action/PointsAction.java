package net.runelite.client.plugins.pluginhub.com.github.therealguru.totemfletching.action;

import net.runelite.client.plugins.pluginhub.com.github.therealguru.totemfletching.model.Totem;
import net.runelite.client.plugins.pluginhub.com.github.therealguru.totemfletching.model.TotemVarbit;
import net.runelite.api.events.VarbitChanged;

public class PointsAction extends TotemAction {

    public PointsAction() {
        super(TotemVarbit.POINTS);
    }

    @Override
    public void onVarbitChanged(Totem totem, VarbitChanged varbitChanged) {
        totem.setPoints(varbitChanged.getValue());
    }
}
