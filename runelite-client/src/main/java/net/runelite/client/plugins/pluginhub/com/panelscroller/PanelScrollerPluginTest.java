package net.runelite.client.plugins.pluginhub.com.panelscroller;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PanelScrollerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PanelScrollerPlugin.class);
		RuneLite.main(args);
	}
}