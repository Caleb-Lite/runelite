package net.runelite.client.plugins.pluginhub.io.robrichardson.skillsorganizer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SkillsOrganizerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SkillsOrganizerPlugin.class);
		RuneLite.main(args);
	}
}
