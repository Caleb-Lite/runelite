package net.runelite.client.plugins.pluginhub.com.mbf;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PluginLaucher
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(MoreFillPlugin.class);
		RuneLite.main(args);
	}
}