package net.runelite.client.plugins.pluginhub.mmdts.trackhealers;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TrackHealersPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TrackHealersPlugin.class);
		RuneLite.main(args);
	}
}

// Special thanks to Miracle Nuts, RoteRosen, and Schizo Girl for helping me debug this plugin.
