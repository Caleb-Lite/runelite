package net.runelite.client.plugins.pluginhub.com.toofifty.easyblastfurnace.methods;

import net.runelite.client.plugins.pluginhub.com.toofifty.easyblastfurnace.steps.MethodStep;
import net.runelite.client.plugins.pluginhub.com.toofifty.easyblastfurnace.utils.CoalPer;
import net.runelite.client.plugins.pluginhub.com.toofifty.easyblastfurnace.utils.Strings;
import net.runelite.api.gameval.ItemID;

public class AdamantiteSilverHybridMethod extends SilverHybridMethod
{
    @Override
    protected MethodStep[] withdrawOre()
    {
        return withdrawAdamantiteOre;
    }

    @Override
    public int oreItem()
    {
        return ItemID.ADAMANTITE_ORE;
    }

    @Override
    protected int barItem()
    {
        return ItemID.ADAMANTITE_BAR;
    }

    @Override
    protected int coalPer()
    {
        return CoalPer.ADAMANTITE.getValue();
    }

    @Override
    public String getName()
    {
        return Strings.ADAMANTITE_SILVER_HYBRID;
    }
}
