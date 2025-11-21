package net.runelite.client.plugins.pluginhub.com.ninetyninenotifier;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class NinetynineNotifierPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(NinetynineNotifierPlugin.class);
		RuneLite.main(args);
	}
}
