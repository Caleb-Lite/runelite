package net.runelite.client.plugins.pluginhub.com.minimaphider;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class MinimapHiderPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(MinimapHiderPlugin.class);
		RuneLite.main(args);
	}
}
