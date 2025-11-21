package net.runelite.client.plugins.pluginhub.com.speshkitty.giantsfoundryitemvalues;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ItemBarValuesEntryTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ItemBarValuesPlugin.class);
		RuneLite.main(args);
	}
}