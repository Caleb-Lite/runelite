package net.runelite.client.plugins.pluginhub.sky.cmAutoSplitter;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class autoSplitterTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CoxCMAutoSplitter.class);
		RuneLite.main(args);
	}
}

/*
 * Plugin for automating LiveSplits for Cox Challenge Mode.
 * Based on De0's CoxTimers.
 */
