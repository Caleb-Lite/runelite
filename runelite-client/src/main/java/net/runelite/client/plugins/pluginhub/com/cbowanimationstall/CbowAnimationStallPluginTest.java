package net.runelite.client.plugins.pluginhub.com.cbowanimationstall;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CbowAnimationStallPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CbowAnimationStallPlugin.class);
		RuneLite.main(args);
	}
}