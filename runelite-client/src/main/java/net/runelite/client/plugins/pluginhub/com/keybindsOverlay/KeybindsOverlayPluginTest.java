package net.runelite.client.plugins.pluginhub.com.keybindsOverlay;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class KeybindsOverlayPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(KeybindsOverlayPlugin.class);
		RuneLite.main(args);
	}
}