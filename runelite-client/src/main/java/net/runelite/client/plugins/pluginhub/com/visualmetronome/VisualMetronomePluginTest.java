package net.runelite.client.plugins.pluginhub.com.visualmetronome;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class VisualMetronomePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(VisualMetronomePlugin.class);
		RuneLite.main(args);
	}
}

