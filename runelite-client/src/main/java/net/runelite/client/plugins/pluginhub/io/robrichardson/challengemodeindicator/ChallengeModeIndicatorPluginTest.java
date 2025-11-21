package net.runelite.client.plugins.pluginhub.io.robrichardson.challengemodeindicator;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ChallengeModeIndicatorPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ChallengeModeIndicatorPlugin.class);
		RuneLite.main(args);
	}
}