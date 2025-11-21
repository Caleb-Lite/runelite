package net.runelite.client.plugins.pluginhub.shortestpath;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ShortestPathPluginTest {
    public static void main(String[] args) throws Exception {
        ExternalPluginManager.loadBuiltin(ShortestPathPlugin.class);
        RuneLite.main(args);
    }
}
