package net.runelite.client.plugins.pluginhub.com.attacksound;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AttackSoundPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AttackSoundPlugin.class);
		RuneLite.main(args);
	}
}