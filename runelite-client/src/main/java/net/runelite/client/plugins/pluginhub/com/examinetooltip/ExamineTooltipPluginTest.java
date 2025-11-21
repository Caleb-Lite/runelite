package net.runelite.client.plugins.pluginhub.com.examinetooltip;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ExamineTooltipPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ExamineTooltipPlugin.class);
		RuneLite.main(args);
	}
}
