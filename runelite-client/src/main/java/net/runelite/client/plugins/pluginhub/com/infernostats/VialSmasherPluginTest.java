package net.runelite.client.plugins.pluginhub.com.infernostats;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class VialSmasherPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(VialSmasherPlugin.class);
		RuneLite.main(args);
	}
}