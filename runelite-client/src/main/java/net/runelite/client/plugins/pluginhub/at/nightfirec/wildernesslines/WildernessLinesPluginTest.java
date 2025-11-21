package net.runelite.client.plugins.pluginhub.at.nightfirec.wildernesslines;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class WildernessLinesPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(WildernessLinesPlugin.class);
		RuneLite.main(args);
	}
}

