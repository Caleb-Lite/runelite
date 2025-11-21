package net.runelite.client.plugins.pluginhub.com.PartialSets;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PartialSetsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PartialSetsPlugin.class);
		RuneLite.main(args);
	}
}
