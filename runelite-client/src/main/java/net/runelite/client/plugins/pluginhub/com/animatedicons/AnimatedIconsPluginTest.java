package net.runelite.client.plugins.pluginhub.com.animatedicons;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AnimatedIconsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AnimatedIconsPlugin.class);
		RuneLite.main(args);
	}
}