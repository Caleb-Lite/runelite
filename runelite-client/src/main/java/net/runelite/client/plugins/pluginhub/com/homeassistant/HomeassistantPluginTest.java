package net.runelite.client.plugins.pluginhub.com.homeassistant;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;
import net.runelite.client.plugins.pluginhub.com.homeassistant.HomeassistantPlugin;

public class HomeassistantPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HomeassistantPlugin.class);
		RuneLite.main(args);
	}
}
