package net.runelite.client.plugins.pluginhub.com.yamareminder;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class YamaReminderPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(YamaReminderPlugin.class);
		RuneLite.main(args);
	}
}
