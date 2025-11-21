package net.runelite.client.plugins.pluginhub.com.github.dappermickie.wildernessmaplocations;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class WildernessMapLocationsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(WildernessMapLocationsPlugin.class);
		RuneLite.main(args);
	}
}
