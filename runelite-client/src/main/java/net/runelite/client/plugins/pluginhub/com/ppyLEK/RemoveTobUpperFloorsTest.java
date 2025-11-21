package net.runelite.client.plugins.pluginhub.com.ppyLEK;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class RemoveTobUpperFloorsTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(RemoveUpperFloorsTobPlugin.class);
		RuneLite.main(args);
	}
}
