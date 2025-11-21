package net.runelite.client.plugins.pluginhub.com.npccontactfilter;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class NPCContactFilterPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(NPCContactFilterPlugin.class);
		RuneLite.main(args);
	}
}
