package net.runelite.client.plugins.pluginhub.com.afkcrabhelper;

import net.runelite.client.plugins.pluginhub.com.afkcrabhelper.AfkCrabHelperPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AfkCrabHelperPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AfkCrabHelperPlugin.class);
		RuneLite.main(args);
	}
}

