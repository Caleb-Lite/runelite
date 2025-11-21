package net.runelite.client.plugins.pluginhub.com.ToACustomPresets;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ToACustomPresets
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ToACustomPresetsPlugin.class);
		RuneLite.main(args);
	}
}
