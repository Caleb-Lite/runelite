package net.runelite.client.plugins.pluginhub.com.valuabledroprecolor;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ValuableDropRecolorPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ValuableDropRecolorPlugin.class);
		RuneLite.main(args);
	}
}