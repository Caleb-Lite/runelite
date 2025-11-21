package net.runelite.client.plugins.pluginhub.com.raidsclipboard;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class RunClientAndPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(RaidsClipboardPlugin.class);
		RuneLite.main(args);
	}
}
