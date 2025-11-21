package net.runelite.client.plugins.pluginhub.com.cwjoshuak;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AttackTypesTests
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AttackTypesPlugin.class);
		RuneLite.main(args);
	}
}
