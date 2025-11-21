package net.runelite.client.plugins.pluginhub.com.pwootage.runelite;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class MetronomeOffsetPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(MultiMetronomePlugin.class);
		RuneLite.main(args);
	}
}