package net.runelite.client.plugins.pluginhub.org.RaidSpellChecker;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PluginLauncher
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(RaidSpellCheckerPlugin.class);
		RuneLite.main(args);
	}
}
