package net.runelite.client.plugins.pluginhub.com.gecalc;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class GECalcPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(GECalcPlugin.class);
		RuneLite.main(args);
	}
}