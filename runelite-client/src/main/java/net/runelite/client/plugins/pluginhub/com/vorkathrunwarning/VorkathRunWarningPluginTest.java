package net.runelite.client.plugins.pluginhub.com.vorkathrunwarning;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class VorkathRunWarningPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(VorkathRunWarningPlugin.class);
		RuneLite.main(args);
	}
}
