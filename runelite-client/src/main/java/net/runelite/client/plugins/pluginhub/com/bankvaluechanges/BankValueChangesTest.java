package net.runelite.client.plugins.pluginhub.com.bankvaluechanges;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class BankValueChangesTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(BankValueChangesPlugin.class);
		RuneLite.main(args);
	}
}
