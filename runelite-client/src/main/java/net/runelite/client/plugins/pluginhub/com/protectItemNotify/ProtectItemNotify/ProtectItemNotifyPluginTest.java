package net.runelite.client.plugins.pluginhub.com.protectItemNotify.ProtectItemNotify;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ProtectItemNotifyPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ProtectItemNotifyPlugin.class);
		RuneLite.main(args);
	}
}