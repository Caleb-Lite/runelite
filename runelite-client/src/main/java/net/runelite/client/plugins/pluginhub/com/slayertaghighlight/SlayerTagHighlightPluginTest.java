package net.runelite.client.plugins.pluginhub.com.slayertaghighlight;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SlayerTagHighlightPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SlayerTagHighlightPlugin.class);
		RuneLite.main(args);
	}
}