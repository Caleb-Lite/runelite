package net.runelite.client.plugins.pluginhub.de0;

import net.runelite.client.plugins.pluginhub.de0.nmtimers.NightmarePlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class NightmarePluginTest {

  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws Exception {
    ExternalPluginManager.loadBuiltin(NightmarePlugin.class);
    RuneLite.main(args);
  }

}

