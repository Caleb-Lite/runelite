package net.runelite.client.plugins.pluginhub.com.pollscreenshot;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PollScreenshotPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PollScreenshotPlugin.class);
		RuneLite.main(args);
	}
}