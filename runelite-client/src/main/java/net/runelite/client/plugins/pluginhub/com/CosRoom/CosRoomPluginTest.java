package net.runelite.client.plugins.pluginhub.com.CosRoom;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CosRoomPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CosRoomPlugin.class);
		RuneLite.main(args);
	}
}