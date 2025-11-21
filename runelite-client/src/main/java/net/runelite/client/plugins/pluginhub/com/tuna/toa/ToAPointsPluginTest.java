package net.runelite.client.plugins.pluginhub.com.tuna.toa;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ToAPointsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ToAPointsPlugin.class);
		RuneLite.main(args);
	}
}