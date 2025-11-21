package net.runelite.client.plugins.pluginhub.com.brastasauce.turaelskipping;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TuraelSkippingTest {
    public static void main(String[] args) throws Exception {
        ExternalPluginManager.loadBuiltin(TuraelSkippingPlugin.class);
        RuneLite.main(args);
    }
}
