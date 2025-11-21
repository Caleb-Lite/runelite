package net.runelite.client.plugins.pluginhub.com.clanKDR;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ClanKDRPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ClanKDRPlugin.class);
		RuneLite.main(args);
	}
}