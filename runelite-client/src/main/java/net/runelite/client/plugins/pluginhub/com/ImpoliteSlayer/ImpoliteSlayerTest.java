package net.runelite.client.plugins.pluginhub.com.ImpoliteSlayer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ImpoliteSlayerTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ImpoliteSlayerPlugin.class);
		RuneLite.main(args);
	}
}