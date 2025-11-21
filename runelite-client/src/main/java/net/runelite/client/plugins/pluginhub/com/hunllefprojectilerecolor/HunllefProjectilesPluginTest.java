package net.runelite.client.plugins.pluginhub.com.hunllefprojectilerecolor;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class HunllefProjectilesPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HunllefProjectilesPlugin.class);
		RuneLite.main(args);
	}
}