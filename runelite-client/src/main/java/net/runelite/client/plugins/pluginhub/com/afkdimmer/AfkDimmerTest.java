package net.runelite.client.plugins.pluginhub.com.afkdimmer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AfkDimmerTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AfkDimmerPlugin.class);
		RuneLite.main(args);
	}
}
