package net.runelite.client.plugins.pluginhub.com.doinkoink;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class MapPinPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(MapPinPlugin.class);
		RuneLite.main(args);
	}
}