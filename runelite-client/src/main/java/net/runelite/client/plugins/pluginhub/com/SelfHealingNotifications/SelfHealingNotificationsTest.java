package net.runelite.client.plugins.pluginhub.com.SelfHealingNotifications;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SelfHealingNotificationsTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SelfHealingNotificationsPlugin.class);
		RuneLite.main(args);
	}
}