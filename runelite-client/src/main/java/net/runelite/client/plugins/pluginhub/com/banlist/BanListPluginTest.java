package net.runelite.client.plugins.pluginhub.com.banlist;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class BanListPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(BanListPlugin.class);
		RuneLite.main(args);
	}
}