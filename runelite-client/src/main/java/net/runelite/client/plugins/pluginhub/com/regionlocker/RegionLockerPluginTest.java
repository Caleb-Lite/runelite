package net.runelite.client.plugins.pluginhub.com.regionlocker;

import net.runelite.client.plugins.pluginhub.com.goaltracker.GoalTrackerPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;
import net.runelite.client.plugins.pluginhub.com.gpu.RegionLockerGpuPlugin;

public class RegionLockerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(RegionLockerPlugin.class, RegionLockerGpuPlugin.class, GoalTrackerPlugin.class);
		RuneLite.main(args);
	}
}

