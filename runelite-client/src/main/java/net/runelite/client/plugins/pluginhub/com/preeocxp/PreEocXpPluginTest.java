package net.runelite.client.plugins.pluginhub.com.preeocxp;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PreEocXpPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PreEocXpPlugin.class);
		RuneLite.main(args);
	}
}
