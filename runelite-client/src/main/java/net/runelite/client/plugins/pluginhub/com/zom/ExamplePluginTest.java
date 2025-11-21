package net.runelite.client.plugins.pluginhub.com.zom;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ExamplePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TOAKerisCamPlugin.class);
		RuneLite.main(args);
	}
}
