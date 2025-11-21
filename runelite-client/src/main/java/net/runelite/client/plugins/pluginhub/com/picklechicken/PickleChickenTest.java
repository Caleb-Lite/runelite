package net.runelite.client.plugins.pluginhub.com.picklechicken;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PickleChickenTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PickleChickenPlugin.class);
		RuneLite.main(args);
	}
}