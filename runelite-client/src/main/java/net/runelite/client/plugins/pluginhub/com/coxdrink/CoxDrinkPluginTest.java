package net.runelite.client.plugins.pluginhub.com.coxdrink;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CoxDrinkPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CoXDrinkPlugin.class);
		RuneLite.main(args);
	}
}