package net.runelite.client.plugins.pluginhub.com.ent;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class EntPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(EntPlugin.class);
		RuneLite.main(args);
	}
}