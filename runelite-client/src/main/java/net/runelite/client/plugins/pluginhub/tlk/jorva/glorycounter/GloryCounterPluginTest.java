package net.runelite.client.plugins.pluginhub.tlk.jorva.glorycounter;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class GloryCounterPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(GloryCounterPlugin.class);
		RuneLite.main(args);
	}
}