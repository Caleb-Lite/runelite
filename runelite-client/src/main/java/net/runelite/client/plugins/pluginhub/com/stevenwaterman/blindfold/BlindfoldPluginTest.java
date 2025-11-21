package net.runelite.client.plugins.pluginhub.com.stevenwaterman.blindfold;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class BlindfoldPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(BlindfoldPlugin.class);
		RuneLite.main(args);
	}
}
