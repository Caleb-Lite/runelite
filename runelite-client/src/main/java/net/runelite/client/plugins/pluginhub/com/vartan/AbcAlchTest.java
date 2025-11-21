package net.runelite.client.plugins.pluginhub.com.vartan;

import net.runelite.client.plugins.pluginhub.com.vartan.abc.AbcAlchPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AbcAlchTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AbcAlchPlugin.class);
		RuneLite.main(args);
	}
}