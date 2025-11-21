package net.runelite.client.plugins.pluginhub.com.damagetakenlogger;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DamageTakenLoggerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DamageTakenLoggerPlugin.class);
		RuneLite.main(args);
	}
}