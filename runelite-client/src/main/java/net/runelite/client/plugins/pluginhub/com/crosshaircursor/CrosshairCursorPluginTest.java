package net.runelite.client.plugins.pluginhub.com.crosshaircursor;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CrosshairCursorPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CrosshairCursorPlugin.class);
		RuneLite.main(args);
	}
}
