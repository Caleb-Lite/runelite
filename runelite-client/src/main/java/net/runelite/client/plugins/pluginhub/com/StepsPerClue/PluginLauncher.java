package net.runelite.client.plugins.pluginhub.com.StepsPerClue;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PluginLauncher
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(StepsPerCluePlugin.class);
		RuneLite.main(args);
	}
}
