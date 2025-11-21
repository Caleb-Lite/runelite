package net.runelite.client.plugins.pluginhub.com.telegramnotifier;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

@SuppressWarnings("unchecked")
public class TelegramNotifierPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TelegramNotifierPlugin.class);
		RuneLite.main(args);
	}
}
