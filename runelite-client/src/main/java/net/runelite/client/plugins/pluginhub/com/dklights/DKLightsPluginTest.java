package net.runelite.client.plugins.pluginhub.com.dklights;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DKLightsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DKLightsPlugin.class);
		RuneLite.main(args);
	}
}
