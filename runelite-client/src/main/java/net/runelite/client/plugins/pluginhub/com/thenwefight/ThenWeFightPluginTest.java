package net.runelite.client.plugins.pluginhub.com.thenwefight;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ThenWeFightPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ThenWeFightPlugin.class);
		RuneLite.main(args);
	}
}