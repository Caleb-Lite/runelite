package net.runelite.client.plugins.pluginhub.com.AverageTotalLevel;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AverageTotalLevelTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AverageTotalLevelPlugin.class);
		RuneLite.main(args);
	}
}
