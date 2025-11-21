package net.runelite.client.plugins.pluginhub.com.speaax;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ExamplePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DelveCalculatorPlugin.class);
		RuneLite.main(args);
	}
}
