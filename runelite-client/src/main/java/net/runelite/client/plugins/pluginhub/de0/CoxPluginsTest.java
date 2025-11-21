package net.runelite.client.plugins.pluginhub.de0;

import net.runelite.client.plugins.pluginhub.de0.coxthieving.CoxThievingPlugin;
import net.runelite.client.plugins.pluginhub.de0.coxtimers.CoxTimersPlugin;
import net.runelite.client.plugins.pluginhub.de0.coxvanguards.CoxVanguardsPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CoxPluginsTest {

  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws Exception {
    ExternalPluginManager.loadBuiltin(CoxTimersPlugin.class,
        CoxVanguardsPlugin.class, CoxThievingPlugin.class);
    RuneLite.main(args);
  }

}

