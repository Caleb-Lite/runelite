package net.runelite.client.plugins.pluginhub.com.prison_sentence;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PrisonSentencePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PrisonSentencePlugin.class);
		RuneLite.main(args);
	}
}
