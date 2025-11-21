package net.runelite.client.plugins.pluginhub.com.iplank.fakestats;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class FakeStatsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(FakeStatsPlugin.class);
		RuneLite.main(args);
	}
}
