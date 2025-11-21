package net.runelite.client.plugins.pluginhub.bingohelper;

import net.runelite.client.plugins.pluginhub.bingohelper.BingoHelperPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class BingoHelperTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(BingoHelperPlugin.class);
		RuneLite.main(args);
	}
}