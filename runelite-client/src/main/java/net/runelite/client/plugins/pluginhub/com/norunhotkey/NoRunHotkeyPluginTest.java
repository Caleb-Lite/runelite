package net.runelite.client.plugins.pluginhub.com.norunhotkey;

import net.runelite.client.externalplugins.ExternalPluginManager;

public class NoRunHotkeyPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(NoRunHotkeyPlugin.class);
		net.runelite.client.RuneLite.main(args);
	}
}

