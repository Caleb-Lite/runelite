package net.runelite.client.plugins.pluginhub.castle_war_afk;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CastleWarAfkPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CastleWarAfkPlugin.class);
		RuneLite.main(args);
	}
}