package net.runelite.client.plugins.pluginhub.com.ericversteeg;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ProximityPromptPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ProximityPromptPlugin.class);
		RuneLite.main(args);
	}
}