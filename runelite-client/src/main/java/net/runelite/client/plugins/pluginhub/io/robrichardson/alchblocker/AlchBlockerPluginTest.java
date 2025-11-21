package net.runelite.client.plugins.pluginhub.io.robrichardson.alchblocker;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AlchBlockerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AlchBlockerPlugin.class);
		RuneLite.main(args);
	}
}