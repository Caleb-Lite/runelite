package net.runelite.client.plugins.pluginhub.com.spectralclanmgmt;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SpectralClanMgmtPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SpectralClanMgmtPlugin.class);
		RuneLite.main(args);
	}
}
