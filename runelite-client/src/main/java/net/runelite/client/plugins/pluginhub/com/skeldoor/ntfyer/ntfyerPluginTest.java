package net.runelite.client.plugins.pluginhub.com.skeldoor.ntfyer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ntfyerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ntfyerPlugin.class);
		RuneLite.main(args);
	}
}