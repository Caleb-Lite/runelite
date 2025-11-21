package net.runelite.client.plugins.pluginhub.com.toggleitemstats.banking;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class BankItemStatsTogglePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(BankItemStatsTogglePlugin.class);
		RuneLite.main(args);
	}
}