package net.runelite.client.plugins.pluginhub.io.banna.rl;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class NpcLabelsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(NpcLabelsPlugin.class);
		RuneLite.main(args);
	}
}