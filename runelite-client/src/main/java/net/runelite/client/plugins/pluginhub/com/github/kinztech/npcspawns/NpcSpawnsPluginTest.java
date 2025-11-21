package net.runelite.client.plugins.pluginhub.com.github.kinztech.npcspawns;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class NpcSpawnsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(NpcSpawnsPlugin.class);
		RuneLite.main(args);
	}
}