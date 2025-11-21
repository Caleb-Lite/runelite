package net.runelite.client.plugins.pluginhub.com.vardorvishealth;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class VardorvisHealthCheck
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(VardorvisHealTrackerPlugin.class);
		RuneLite.main(args);
	}
}