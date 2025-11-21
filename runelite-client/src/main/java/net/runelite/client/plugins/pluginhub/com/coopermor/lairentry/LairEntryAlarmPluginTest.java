package net.runelite.client.plugins.pluginhub.com.coopermor.lairentry;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class LairEntryAlarmPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(LairEntryAlarmPlugin.class);
		RuneLite.main(args);
	}
}
