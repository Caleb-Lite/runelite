package net.runelite.client.plugins.pluginhub.com.toofifty.easyblastfurnace.methods;

import net.runelite.client.plugins.pluginhub.com.toofifty.easyblastfurnace.steps.MethodStep;
import net.runelite.client.plugins.pluginhub.com.toofifty.easyblastfurnace.utils.CoalPer;
import net.runelite.client.plugins.pluginhub.com.toofifty.easyblastfurnace.utils.Strings;
import net.runelite.api.gameval.ItemID;

public class SteelBarMethod extends MetalBarMethod
{
    @Override
    protected MethodStep[] withdrawOre()
    {
        return withdrawIronOre;
    }

    @Override
    public int oreItem()
    {
        return ItemID.IRON_ORE;
    }

    @Override
    protected int barItem()
    {
        return ItemID.STEEL_BAR;
    }

    @Override
    protected int coalPer()
    {
        return CoalPer.IRON.getValue();
    }

    @Override
    public String getName()
    {
        return Strings.STEEL;
    }
}
