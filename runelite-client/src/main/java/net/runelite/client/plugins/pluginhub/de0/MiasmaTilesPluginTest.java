package net.runelite.client.plugins.pluginhub.de0;

import net.runelite.client.plugins.pluginhub.de0.miasmatiles.MiasmaTilesPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class MiasmaTilesPluginTest {

  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws Exception {
    ExternalPluginManager.loadBuiltin(MiasmaTilesPlugin.class);
    RuneLite.main(args);
  }

}
