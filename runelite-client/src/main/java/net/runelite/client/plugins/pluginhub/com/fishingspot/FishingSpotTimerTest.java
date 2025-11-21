package net.runelite.client.plugins.pluginhub.com.fishingspot;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class FishingSpotTimerTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(FishingSpotTimerPlugin.class);
		RuneLite.main(args);
	}
}
