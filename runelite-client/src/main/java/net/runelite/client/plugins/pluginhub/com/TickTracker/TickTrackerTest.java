package net.runelite.client.plugins.pluginhub.com.TickTracker;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TickTrackerTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TickTrackerPlugin.class);
		RuneLite.main(args);
	}
}
