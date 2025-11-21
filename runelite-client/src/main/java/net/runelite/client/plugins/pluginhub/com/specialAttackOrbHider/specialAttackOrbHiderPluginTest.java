package net.runelite.client.plugins.pluginhub.com.specialAttackOrbHider;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class specialAttackOrbHiderPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(specialAttackOrbHiderPlugin.class);
		RuneLite.main(args);
	}
}
