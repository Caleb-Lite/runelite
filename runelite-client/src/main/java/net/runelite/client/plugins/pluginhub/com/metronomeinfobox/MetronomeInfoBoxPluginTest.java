package net.runelite.client.plugins.pluginhub.com.metronomeinfobox;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class MetronomeInfoBoxPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(MetronomeInfoBoxPlugin.class);
		RuneLite.main(args);
	}
}