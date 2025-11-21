package net.runelite.client.plugins.pluginhub.com.itemreminders;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ReminderPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ReminderPlugin.class);
		RuneLite.main(args);
	}
}