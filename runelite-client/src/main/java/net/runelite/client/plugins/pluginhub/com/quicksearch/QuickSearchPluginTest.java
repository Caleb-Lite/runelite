package net.runelite.client.plugins.pluginhub.com.quicksearch;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class QuickSearchPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(QuickSearchPlugin.class);
		RuneLite.main(args);
	}
}
