package net.runelite.client.plugins.pluginhub.com.tobqolimprovements;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TobQolImprovementsPluginTest
{
    public static void main(String[] args) throws Exception
    {
        ExternalPluginManager.loadBuiltin(ToBQoLImprovementsPlugin.class);
        RuneLite.main(args);
    }
}
