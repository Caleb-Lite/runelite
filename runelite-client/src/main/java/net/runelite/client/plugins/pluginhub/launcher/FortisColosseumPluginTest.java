package net.runelite.client.plugins.pluginhub.launcher;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.fortis.FortisColosseumPlugin;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.fortis.debugplugins.FortisColosseumDebugPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class FortisColosseumPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(FortisColosseumPlugin.class, FortisColosseumDebugPlugin.class);
		RuneLite.main(args);
	}
}
