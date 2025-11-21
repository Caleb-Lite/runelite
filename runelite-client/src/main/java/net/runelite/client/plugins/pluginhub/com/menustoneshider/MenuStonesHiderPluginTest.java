package net.runelite.client.plugins.pluginhub.com.menustoneshider;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class MenuStonesHiderPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(MenuStonesHiderPlugin.class);
		RuneLite.main(args);
	}
}
