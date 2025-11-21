package net.runelite.client.plugins.pluginhub.com.tobinfoboxes;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TobInfoboxesPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TobInfoboxesPlugin.class);
		RuneLite.main(args);
	}
}