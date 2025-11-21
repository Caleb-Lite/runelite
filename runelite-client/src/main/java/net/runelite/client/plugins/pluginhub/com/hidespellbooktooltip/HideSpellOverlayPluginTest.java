package net.runelite.client.plugins.pluginhub.com.hidespellbooktooltip;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class HideSpellOverlayPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HideSpellbookTooltipPlugin.class);
		RuneLite.main(args);
	}
}
