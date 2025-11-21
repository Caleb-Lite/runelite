package net.runelite.client.plugins.pluginhub.com.thralldamagecounter;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ThrallDamageCounterPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ThrallDamageCounterPlugin.class);
		RuneLite.main(args);
	}
}