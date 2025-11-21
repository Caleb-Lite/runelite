package net.runelite.client.plugins.pluginhub.com.nucleon.porttasks;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PortTasksPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PortTasksPlugin.class);
		RuneLite.main(args);
	}
}
