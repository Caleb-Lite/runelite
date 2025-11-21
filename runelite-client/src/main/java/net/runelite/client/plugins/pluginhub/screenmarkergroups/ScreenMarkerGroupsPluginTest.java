package net.runelite.client.plugins.pluginhub.screenmarkergroups;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ScreenMarkerGroupsPluginTest {
    public static void main(String[] args) throws Exception {
        ExternalPluginManager.loadBuiltin(ScreenMarkerGroupsPlugin.class);
        RuneLite.main(args);
    }
}

