package net.runelite.client.plugins.pluginhub.com.crabstuntimer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CrabStunTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CrabStunPlugin.class);
		RuneLite.main(args);
	}
}

