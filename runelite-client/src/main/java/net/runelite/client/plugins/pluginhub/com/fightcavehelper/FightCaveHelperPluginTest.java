package net.runelite.client.plugins.pluginhub.com.fightcavehelper;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class FightCaveHelperPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(FightCaveHelperPlugin.class);
		RuneLite.main(args);
	}
}