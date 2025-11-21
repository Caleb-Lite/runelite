package net.runelite.client.plugins.pluginhub.com.spawnmarker;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SpawnMarkerPluginTest {
	public static void main(String[] args) throws Exception {
		ExternalPluginManager.loadBuiltin(SpawnMarkerPlugin.class);
		RuneLite.main(args);
	}
}