package net.runelite.client.plugins.pluginhub.bbp.equipmentscreenshot;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class EquipmentScreenshotPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(EquipmentScreenshotPlugin.class);
		RuneLite.main(args);
	}
}

