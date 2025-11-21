package net.runelite.client.plugins.pluginhub.com.yama;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PhaseReminderPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PhaseReminderPlugin.class);
		RuneLite.main(args);
	}
}
