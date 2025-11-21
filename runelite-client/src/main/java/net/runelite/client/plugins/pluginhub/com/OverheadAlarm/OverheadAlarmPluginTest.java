package net.runelite.client.plugins.pluginhub.com.OverheadAlarm;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class OverheadAlarmPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(OverheadAlarmPlugin.class);
		RuneLite.main(args);
	}
}
