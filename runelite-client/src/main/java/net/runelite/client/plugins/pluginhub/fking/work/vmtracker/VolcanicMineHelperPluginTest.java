package net.runelite.client.plugins.pluginhub.fking.work.vmtracker;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class VolcanicMineHelperPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(VolcanicMineTrackerPlugin.class);
		RuneLite.main(args);
	}
}