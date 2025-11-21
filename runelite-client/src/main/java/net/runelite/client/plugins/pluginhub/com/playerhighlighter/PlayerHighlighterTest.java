package net.runelite.client.plugins.pluginhub.com.playerhighlighter;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PlayerHighlighterTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PlayerHighlighterPlugin.class);
		RuneLite.main(args);
	}
}