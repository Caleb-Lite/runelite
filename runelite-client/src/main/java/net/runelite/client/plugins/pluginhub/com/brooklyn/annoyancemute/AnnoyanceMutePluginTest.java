package net.runelite.client.plugins.pluginhub.com.brooklyn.annoyancemute;

import net.runelite.client.plugins.pluginhub.com.brooklyn.annoyancemute.debug.AnnoyanceMutePluginDebug;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AnnoyanceMutePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AnnoyanceMutePlugin.class, AnnoyanceMutePluginDebug.class);
		RuneLite.main(args);
	}
}