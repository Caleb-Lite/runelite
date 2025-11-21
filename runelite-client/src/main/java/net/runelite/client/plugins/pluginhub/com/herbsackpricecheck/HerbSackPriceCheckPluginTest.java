package net.runelite.client.plugins.pluginhub.com.herbsackpricecheck;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class HerbSackPriceCheckPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HerbSackPriceCheckPlugin.class);
		RuneLite.main(args);
	}
}