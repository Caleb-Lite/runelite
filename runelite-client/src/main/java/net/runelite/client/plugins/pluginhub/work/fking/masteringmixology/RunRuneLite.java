package net.runelite.client.plugins.pluginhub.work.fking.masteringmixology;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class RunRuneLite {

    public static void main(String[] args) throws Exception {
        ExternalPluginManager.loadBuiltin(MasteringMixologyPlugin.class);
        RuneLite.main(args);
    }
}
