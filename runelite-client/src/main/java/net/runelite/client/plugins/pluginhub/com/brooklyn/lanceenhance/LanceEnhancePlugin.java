package net.runelite.client.plugins.pluginhub.com.brooklyn.lanceenhance;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.events.ClientTick;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
	name = "Lance Enhance",
	description = "Run normally with a Dragon Hunter Lance",
	tags = "brooklyn, hub, dragon, pvm, combat, animation"
)
public class LanceEnhancePlugin extends Plugin
{
	@Inject
	private Client client;

	@Subscribe
	public void onClientTick(ClientTick event)
	{
		for (Player player : client.getPlayers())
		{
			if (player.getPoseAnimation() == 2563)
			{
				player.setPoseAnimation(824);
			}
		}
	}
}

