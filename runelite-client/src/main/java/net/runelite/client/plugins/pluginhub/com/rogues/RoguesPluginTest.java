package net.runelite.client.plugins.pluginhub.com.rogues;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class RoguesPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(RoguesPlugin.class);
		RuneLite.main(args);
	}
}