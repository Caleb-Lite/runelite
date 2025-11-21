package net.runelite.client.plugins.pluginhub.com.gghammerreminder;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class GGHammerReminderPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(GGHammerReminderPlugin.class);
		RuneLite.main(args);
	}
}