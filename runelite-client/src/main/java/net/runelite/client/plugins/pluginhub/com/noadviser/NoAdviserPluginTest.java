package net.runelite.client.plugins.pluginhub.com.noadviser;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class NoAdviserPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(NoAdviserPlugin.class);
		RuneLite.main(args);
	}
}