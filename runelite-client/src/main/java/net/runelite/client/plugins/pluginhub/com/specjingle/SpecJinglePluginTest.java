package net.runelite.client.plugins.pluginhub.com.specjingle;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SpecJinglePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SpecJinglePlugin.class);
		RuneLite.main(args);
	}
}
