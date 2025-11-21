package net.runelite.client.plugins.pluginhub.com.orbhider;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class OrbHiderPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(OrbHiderPlugin.class);
		RuneLite.main(args);
	}
}