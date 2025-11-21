package net.runelite.client.plugins.pluginhub.com.neur0tox1n_.customvitalbars;


import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CustomVitalBarsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CustomVitalBarsPlugin.class);
		RuneLite.main(args);
	}
}
