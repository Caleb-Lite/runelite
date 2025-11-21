package net.runelite.client.plugins.pluginhub.com.toaspellbookreminder;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ToaSpellbookReminderPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ToaSpellbookReminderPlugin.class);
		RuneLite.main(args);
	}
}