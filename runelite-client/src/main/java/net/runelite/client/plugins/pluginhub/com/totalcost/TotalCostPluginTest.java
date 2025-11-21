package net.runelite.client.plugins.pluginhub.com.totalcost;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TotalCostPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TotalCostPlugin.class);
		RuneLite.main(args);
	}
}