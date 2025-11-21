package net.runelite.client.plugins.pluginhub.com.PublicChatAndNPCOverheadRemover;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PublicChatAndNPCOverheadRemoverPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PublicChatAndNPCOverheadRemoverPlugin.class);
		RuneLite.main(args);
	}
}
