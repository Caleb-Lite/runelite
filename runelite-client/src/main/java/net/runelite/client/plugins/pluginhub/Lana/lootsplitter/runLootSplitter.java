package net.runelite.client.plugins.pluginhub.Lana.lootsplitter;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class runLootSplitter
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(LootSplitterPlugin.class);
		RuneLite.main(args);
	}
}