package net.runelite.client.plugins.pluginhub.com.simonflapse.osrs;

import net.runelite.client.plugins.pluginhub.com.simonflapse.osrs.venom.VenomTimerPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;
import org.junit.jupiter.api.Disabled;

@Disabled("Intended as a main entry point for locally running RuneLite with the plugin enabled")
public class StartRunelite
{
	@SuppressWarnings({"unchecked", "varargs"})
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(VenomTimerPlugin.class);
		RuneLite.main(args);
	}
}