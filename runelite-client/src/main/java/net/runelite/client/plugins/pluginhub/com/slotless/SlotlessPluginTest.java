package net.runelite.client.plugins.pluginhub.com.slotless;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SlotlessPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SlotlessPlugin.class);
		RuneLite.main(args);
	}
}