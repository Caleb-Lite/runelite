package net.runelite.client.plugins.pluginhub.com.monsterstats;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class MonsterStatsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(MonsterStatsPlugin.class);
		RuneLite.main(args);
	}
}
