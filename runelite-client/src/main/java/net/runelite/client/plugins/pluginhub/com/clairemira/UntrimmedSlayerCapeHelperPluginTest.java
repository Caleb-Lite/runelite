package net.runelite.client.plugins.pluginhub.com.clairemira;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class UntrimmedSlayerCapeHelperPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(UntrimmedSlayerCapeHelperPlugin.class);
		RuneLite.main(args);
	}
}
