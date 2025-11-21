package net.runelite.client.plugins.pluginhub.com.geel.hamstorerooms;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class HAMStoreRoomsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(StoreRoomsPlugin.class);
		RuneLite.main(args);
	}
}