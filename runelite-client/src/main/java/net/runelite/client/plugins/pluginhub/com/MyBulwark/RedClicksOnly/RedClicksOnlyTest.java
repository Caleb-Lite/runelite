package net.runelite.client.plugins.pluginhub.com.MyBulwark.RedClicksOnly;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class RedClicksOnlyTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(RedClicksOnlyPlugin.class);
		RuneLite.main(args);
	}
}