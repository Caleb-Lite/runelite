package net.runelite.client.plugins.pluginhub.com.hpabovehead;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class HPAboveHeadTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HPAboveHeadPlugin.class);
		RuneLite.main(args);
	}
}
