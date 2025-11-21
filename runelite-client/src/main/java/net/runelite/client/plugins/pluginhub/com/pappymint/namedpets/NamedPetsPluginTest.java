package net.runelite.client.plugins.pluginhub.com.pappymint.namedpets;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class NamedPetsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(NamedPetsPlugin.class);
		RuneLite.main(args);
	}
}
