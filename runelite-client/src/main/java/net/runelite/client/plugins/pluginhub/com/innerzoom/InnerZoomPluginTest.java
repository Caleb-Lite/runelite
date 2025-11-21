package net.runelite.client.plugins.pluginhub.com.innerzoom;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class InnerZoomPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(InnerZoomPlugin.class);
		RuneLite.main(args);
	}
}