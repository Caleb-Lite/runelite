package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.launcher;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.TombsOfAmascutPlugin;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.launcher.debugplugins.ToaDebugPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TombsOfAmascutPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TombsOfAmascutPlugin.class, ToaDebugPlugin.class);
		RuneLite.main(args);
	}
}