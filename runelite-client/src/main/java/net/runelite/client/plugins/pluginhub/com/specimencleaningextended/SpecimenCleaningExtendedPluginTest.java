package net.runelite.client.plugins.pluginhub.com.specimencleaningextended;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SpecimenCleaningExtendedPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SpecimenCleaningExtendedPlugin.class);
		RuneLite.main(args);
	}

}