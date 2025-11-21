package net.runelite.client.plugins.pluginhub.com.acoldoneclogging;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AColdOneCloggingPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AColdOneCloggingPlugin.class);
		RuneLite.main(args);
	}
}
