package net.runelite.client.plugins.pluginhub.com.NatureRCUtil;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class NatureRCUtilPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(NatureRCUtilPlugin.class);
		RuneLite.main(args);
	}
}
