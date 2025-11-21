package net.runelite.client.plugins.pluginhub.com.specinfo;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SpecInfoPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SpecInfoPlugin.class);
		RuneLite.main(args);
	}
}
