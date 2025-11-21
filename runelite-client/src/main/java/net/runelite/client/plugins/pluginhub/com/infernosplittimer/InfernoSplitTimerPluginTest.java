package net.runelite.client.plugins.pluginhub.com.infernosplittimer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class InfernoSplitTimerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(InfernoSplitTimerPlugin.class);
		RuneLite.main(args);
	}
}