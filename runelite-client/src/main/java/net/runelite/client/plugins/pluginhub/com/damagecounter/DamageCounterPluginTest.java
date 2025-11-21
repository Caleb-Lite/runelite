package net.runelite.client.plugins.pluginhub.com.damagecounter;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DamageCounterPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DamageCounterPlugin.class);
		RuneLite.main(args);
	}
}
