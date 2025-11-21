package net.runelite.client.plugins.pluginhub.com.potionbar;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PotionBarPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PotionBarPlugin.class);
		RuneLite.main(args);
	}
}
