package net.runelite.client.plugins.pluginhub.com.patchtimer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PatchTimerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PatchTimerPlugin.class);
		RuneLite.main(args);
	}
}
