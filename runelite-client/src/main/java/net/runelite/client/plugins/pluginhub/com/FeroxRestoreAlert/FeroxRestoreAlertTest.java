package net.runelite.client.plugins.pluginhub.com.FeroxRestoreAlert;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class FeroxRestoreAlertTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(FeroxRestoreAlertPlugin.class);
		RuneLite.main(args);
	}
}