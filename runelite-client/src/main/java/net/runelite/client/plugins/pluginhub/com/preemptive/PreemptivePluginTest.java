package net.runelite.client.plugins.pluginhub.com.preemptive;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PreemptivePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PreemptivePlugin.class);
		RuneLite.main(args);
	}
}