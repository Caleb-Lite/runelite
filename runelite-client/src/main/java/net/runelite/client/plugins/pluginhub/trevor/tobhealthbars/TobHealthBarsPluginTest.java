package net.runelite.client.plugins.pluginhub.trevor.tobhealthbars;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TobHealthBarsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TobHealthBarsPlugin.class);
		RuneLite.main(args);
	}
}