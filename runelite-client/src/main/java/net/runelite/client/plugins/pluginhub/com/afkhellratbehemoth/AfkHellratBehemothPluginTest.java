package net.runelite.client.plugins.pluginhub.com.afkhellratbehemoth;
// package net.runelite.client.plugins.afkhellratbehemoth;


import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AfkHellratBehemothPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AfkHellratBehemothPlugin.class);
		RuneLite.main(args);
	}
}