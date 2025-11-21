package net.runelite.client.plugins.pluginhub.com.anderzenn.dorgeshkaanagilitycourse;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DorgeshKaanAgilityCourseTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DorgeshKaanAgilityCourse.class);
		RuneLite.main(args);
	}
}
