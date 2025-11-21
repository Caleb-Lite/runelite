package net.runelite.client.plugins.pluginhub.com.prayervolumecontrol;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PrayerVolumeControlPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PrayerVolumeControlPlugin.class);
		RuneLite.main(args);
	}
}