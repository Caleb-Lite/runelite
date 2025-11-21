package net.runelite.client.plugins.pluginhub.codepanter.anotherbronzemanmode;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AnotherBronzemanModePluginTest
{
    public static void main(String[] args) throws Exception
    {
        ExternalPluginManager.loadBuiltin(AnotherBronzemanModePlugin.class);
        RuneLite.main(args);
    }
}

