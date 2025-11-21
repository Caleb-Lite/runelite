package net.runelite.client.plugins.pluginhub.cloudAspect.hypercam2;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class UnregisteredHypercam2PluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(UnregisteredHypercam2Plugin.class);
		RuneLite.main(args);
	}
}