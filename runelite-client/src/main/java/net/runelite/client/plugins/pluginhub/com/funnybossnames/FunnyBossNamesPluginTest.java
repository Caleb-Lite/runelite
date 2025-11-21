package net.runelite.client.plugins.pluginhub.com.funnybossnames;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class FunnyBossNamesPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(FunnyBossNamesPlugin.class);
		RuneLite.main(args);
	}
}
