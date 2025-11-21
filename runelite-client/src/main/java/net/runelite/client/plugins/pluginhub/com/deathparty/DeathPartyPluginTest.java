package net.runelite.client.plugins.pluginhub.com.deathparty;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DeathPartyPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DeathPartyPlugin.class);
		RuneLite.main(args);
	}
}