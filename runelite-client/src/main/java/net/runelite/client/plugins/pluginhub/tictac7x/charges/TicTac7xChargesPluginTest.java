package net.runelite.client.plugins.pluginhub.tictac7x.charges;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TicTac7xChargesPluginTest {
	public static void main(String[] args) throws Exception {
		ExternalPluginManager.loadBuiltin(TicTac7xChargesImprovedPlugin.class);
		RuneLite.main(args);
	}
}
