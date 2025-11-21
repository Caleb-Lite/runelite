package net.runelite.client.plugins.pluginhub.com.worldmaprunes;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PluginLauncher
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(WorldMapRunes.class);
		RuneLite.main(args);
	}
}
