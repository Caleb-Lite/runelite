package net.runelite.client.plugins.pluginhub.com.AccountValueOverlay;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AccountValueOverlayPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AccountValuePlugin.class);
		RuneLite.main(args);
	}
}
