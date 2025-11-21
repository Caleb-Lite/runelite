package net.runelite.client.plugins.pluginhub.com.toa;

import net.runelite.client.plugins.pluginhub.Toacito.ToacitoPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ToaPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ToacitoPlugin.class);
		RuneLite.main(args);
	}
}