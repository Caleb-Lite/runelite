package net.runelite.client.plugins.pluginhub.com.towncrier;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TownCrierPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TownCrierPlugin.class);
		RuneLite.main(args);
	}
}