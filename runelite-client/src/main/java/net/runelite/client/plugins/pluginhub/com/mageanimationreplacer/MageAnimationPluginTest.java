package net.runelite.client.plugins.pluginhub.com.mageanimationreplacer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class MageAnimationPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(MageAnimationPlugin.class);
		RuneLite.main(args);
	}
}
