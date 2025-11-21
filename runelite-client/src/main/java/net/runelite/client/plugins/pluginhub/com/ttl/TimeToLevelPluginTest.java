package net.runelite.client.plugins.pluginhub.com.ttl;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TimeToLevelPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TimeToLevelPlugin.class);
		RuneLite.main(args);
	}
}