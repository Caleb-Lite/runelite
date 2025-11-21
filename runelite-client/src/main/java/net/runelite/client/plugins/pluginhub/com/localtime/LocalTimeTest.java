package net.runelite.client.plugins.pluginhub.com.localtime;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class LocalTimeTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(LocalTimePlugin.class);
		RuneLite.main(args);
	}
}