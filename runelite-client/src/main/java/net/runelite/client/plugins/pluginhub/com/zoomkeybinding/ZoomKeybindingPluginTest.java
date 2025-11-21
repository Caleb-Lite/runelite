package net.runelite.client.plugins.pluginhub.com.zoomkeybinding;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ZoomKeybindingPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ZoomKeybindingPlugin.class);
		RuneLite.main(args);
	}
}
