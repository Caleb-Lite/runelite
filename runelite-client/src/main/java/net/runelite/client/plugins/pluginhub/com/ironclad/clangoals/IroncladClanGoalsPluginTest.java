package net.runelite.client.plugins.pluginhub.com.ironclad.clangoals;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class IroncladClanGoalsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(IroncladClanGoalsPlugin.class);
		RuneLite.main(args);
	}
}