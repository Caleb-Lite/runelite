package net.runelite.client.plugins.pluginhub.com.InfernoAutoSplitter;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class InfernoAutoSplitterTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(InfernoAutoSplitterPlugin.class);
		RuneLite.main(args);
	}
}