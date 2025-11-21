package net.runelite.client.plugins.pluginhub.com.example;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class WorldCyclePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(WorldCyclePlugin.class);
		RuneLite.main(args);
	}
}
