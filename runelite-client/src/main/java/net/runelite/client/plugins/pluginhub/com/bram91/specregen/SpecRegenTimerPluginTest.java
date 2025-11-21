package net.runelite.client.plugins.pluginhub.com.bram91.specregen;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SpecRegenTimerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SpecRegenTimerPlugin.class);
		RuneLite.main(args);
	}
}
