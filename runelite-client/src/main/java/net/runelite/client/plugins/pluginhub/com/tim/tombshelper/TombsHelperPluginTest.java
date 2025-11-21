package net.runelite.client.plugins.pluginhub.com.tim.tombshelper;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TombsHelperPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TombsHelperPlugin.class);
		RuneLite.main(args);
	}
}