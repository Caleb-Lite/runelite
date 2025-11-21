package net.runelite.client.plugins.pluginhub.com.templetracker;

import net.runelite.client.plugins.pluginhub.com.templetracker.menuentryswapper.MenuSwapperPlugin;
import net.runelite.client.plugins.pluginhub.com.templetracker.overlay.TempleTrackerPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class RunClientAndPlugin
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TempleTrackerPlugin.class, MenuSwapperPlugin.class);
		RuneLite.main(args);
	}
}