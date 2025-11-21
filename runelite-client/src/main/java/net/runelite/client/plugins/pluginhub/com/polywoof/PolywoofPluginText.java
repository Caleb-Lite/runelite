package net.runelite.client.plugins.pluginhub.com.polywoof;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PolywoofPluginText
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PolywoofPlugin.class);
		RuneLite.main(args);
	}
}