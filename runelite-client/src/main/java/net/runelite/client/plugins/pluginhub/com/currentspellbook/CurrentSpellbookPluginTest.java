package net.runelite.client.plugins.pluginhub.com.currentspellbook;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CurrentSpellbookPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CurrentSpellbookPlugin.class);
		RuneLite.main(args);
	}
}
