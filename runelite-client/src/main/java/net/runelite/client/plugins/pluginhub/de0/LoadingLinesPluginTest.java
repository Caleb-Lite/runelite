package net.runelite.client.plugins.pluginhub.de0;

import net.runelite.client.plugins.pluginhub.de0.loadinglines.LoadingLinesPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class LoadingLinesPluginTest {

  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws Exception {
    ExternalPluginManager.loadBuiltin(LoadingLinesPlugin.class);
    RuneLite.main(args);
  }

}
