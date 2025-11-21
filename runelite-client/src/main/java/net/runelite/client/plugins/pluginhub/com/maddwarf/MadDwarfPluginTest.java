package net.runelite.client.plugins.pluginhub.com.maddwarf;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class MadDwarfPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(MadDwarfPlugin.class);
		RuneLite.main(args);
	}
}