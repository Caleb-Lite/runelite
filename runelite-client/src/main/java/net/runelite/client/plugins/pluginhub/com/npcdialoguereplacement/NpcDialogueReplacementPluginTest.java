package net.runelite.client.plugins.pluginhub.com.npcdialoguereplacement;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class NpcDialogueReplacementPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(NpcDialogueReplacementPlugin.class);
		RuneLite.main(args);
	}
}