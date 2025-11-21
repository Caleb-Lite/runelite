package net.runelite.client.plugins.pluginhub.com.ControlRun;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ControlRunTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ControlRunPlugin.class);
		RuneLite.main(args);
	}
}