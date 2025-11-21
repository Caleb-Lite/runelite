package net.runelite.client.plugins.pluginhub.com.bossvoicer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class BossVoicerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(BossVoicerPlugin.class);
		RuneLite.main(args);
	}
}