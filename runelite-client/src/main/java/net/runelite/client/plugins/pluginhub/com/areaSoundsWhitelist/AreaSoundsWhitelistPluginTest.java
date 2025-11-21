package net.runelite.client.plugins.pluginhub.com.areaSoundsWhitelist;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AreaSoundsWhitelistPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AreaSoundsWhitelistPlugin.class);
		RuneLite.main(args);
	}
}
