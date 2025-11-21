package net.runelite.client.plugins.pluginhub.gim.bank.discord;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class GimBankDiscordPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(GimBankDiscordPlugin.class);
		RuneLite.main(args);
	}
}