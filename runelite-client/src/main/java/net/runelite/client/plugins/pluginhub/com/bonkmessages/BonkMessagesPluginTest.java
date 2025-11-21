package net.runelite.client.plugins.pluginhub.com.bonkmessages;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class BonkMessagesPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(BonkMessagesPlugin.class);
		RuneLite.main(args);
	}
}
