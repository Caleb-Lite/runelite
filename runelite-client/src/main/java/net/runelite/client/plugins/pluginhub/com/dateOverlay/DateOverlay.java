package net.runelite.client.plugins.pluginhub.com.dateOverlay;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DateOverlay
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DateOverlayPlugin.class);
		RuneLite.main(args);
	}
}