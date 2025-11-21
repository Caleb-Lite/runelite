package net.runelite.client.plugins.pluginhub.com.hotcoldhelper;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class HotColdHelperTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HotColdHelperPlugin.class);
		RuneLite.main(args);
	}
}
