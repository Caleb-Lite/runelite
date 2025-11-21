package net.runelite.client.plugins.pluginhub.com.cluehunterfinder;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ClueHunterFinderTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ClueHunterFinderPlugin.class);
		RuneLite.main(args);
	}
}
