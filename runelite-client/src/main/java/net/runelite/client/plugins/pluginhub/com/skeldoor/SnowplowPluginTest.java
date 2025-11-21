package net.runelite.client.plugins.pluginhub.com.skeldoor;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SnowplowPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SnowplowPlugin.class);
		RuneLite.main(args);
	}
}
