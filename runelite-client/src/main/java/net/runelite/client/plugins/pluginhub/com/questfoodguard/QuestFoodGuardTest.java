package net.runelite.client.plugins.pluginhub.com.questfoodguard;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class QuestFoodGuardTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(QuestFoodGuardPlugin.class);
		RuneLite.main(args);
	}
}
