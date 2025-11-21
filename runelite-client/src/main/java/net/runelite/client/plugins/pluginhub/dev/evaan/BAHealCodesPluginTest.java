package net.runelite.client.plugins.pluginhub.dev.evaan;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class BAHealCodesPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(BAHealCodesPlugin.class);
		RuneLite.main(args);
	}
}
