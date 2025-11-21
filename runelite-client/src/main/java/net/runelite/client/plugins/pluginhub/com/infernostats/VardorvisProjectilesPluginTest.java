package net.runelite.client.plugins.pluginhub.com.infernostats;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class VardorvisProjectilesPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(VardorvisProjectilesPlugin.class);
		RuneLite.main(args);
	}
}