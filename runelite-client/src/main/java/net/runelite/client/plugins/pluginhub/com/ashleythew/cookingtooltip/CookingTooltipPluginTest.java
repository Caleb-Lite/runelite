package net.runelite.client.plugins.pluginhub.com.ashleythew.cookingtooltip;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CookingTooltipPluginTest {
    public static void main(String[] args) throws Exception {
        ExternalPluginManager.loadBuiltin(CookingTooltipPlugin.class);
        RuneLite.main(args);
    }
}

