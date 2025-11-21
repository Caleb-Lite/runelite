package net.runelite.client.plugins.pluginhub.com.gauntletcalculator;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class GauntletCalculatorPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(GauntletCalculatorPlugin.class);
		RuneLite.main(args);
	}
}