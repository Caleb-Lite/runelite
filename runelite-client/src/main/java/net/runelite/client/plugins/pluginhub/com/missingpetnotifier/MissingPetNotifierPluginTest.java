package net.runelite.client.plugins.pluginhub.com.missingpetnotifier;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class MissingPetNotifierPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(MissingPetNotifierPlugin.class);
		RuneLite.main(args);
	}
}
