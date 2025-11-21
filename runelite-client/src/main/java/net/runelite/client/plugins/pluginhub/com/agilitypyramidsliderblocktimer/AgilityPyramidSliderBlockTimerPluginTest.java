package net.runelite.client.plugins.pluginhub.com.agilitypyramidsliderblocktimer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AgilityPyramidSliderBlockTimerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AgilityPyramidSliderBlockTimerPlugin.class);
		RuneLite.main(args);
	}
}
