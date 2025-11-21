package net.runelite.client.plugins.pluginhub.com.pleae;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PleaePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PleaePlugin.class);
		RuneLite.main(args);
	}
}
