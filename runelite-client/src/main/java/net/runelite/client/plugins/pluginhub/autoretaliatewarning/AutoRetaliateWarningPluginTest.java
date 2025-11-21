package net.runelite.client.plugins.pluginhub.autoretaliatewarning;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AutoRetaliateWarningPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AutoRetaliateWarningPlugin.class);
		RuneLite.main(args);
	}
}
