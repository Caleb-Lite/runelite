package net.runelite.client.plugins.pluginhub.com.simplekc;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class KcPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(KcPlugin.class);
		RuneLite.main(args);
	}
}
