package net.runelite.client.plugins.pluginhub.com.combatroll;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CombatRollPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CombatRollPlugin.class);
		RuneLite.main(args);
	}
}