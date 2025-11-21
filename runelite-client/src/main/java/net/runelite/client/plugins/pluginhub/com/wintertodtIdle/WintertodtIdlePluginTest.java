package net.runelite.client.plugins.pluginhub.com.wintertodtIdle;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class WintertodtIdlePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(WintertodtIdlePlugin.class);
		RuneLite.main(args);
	}
}