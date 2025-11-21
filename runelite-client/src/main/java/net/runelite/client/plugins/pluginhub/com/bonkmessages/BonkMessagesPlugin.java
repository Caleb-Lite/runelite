package net.runelite.client.plugins.pluginhub.com.bonkmessages;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.Random;

@Slf4j
@PluginDescriptor(
	name = "Bonk Messages"
)
public class BonkMessagesPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private BonkMessagesConfig config;

	private final int MESSAGE_DURATION = 20*2; // Display message for 2 ticks

	private String randomString(String[] strings){
		int rnd = new Random().nextInt(strings.length);
		return strings[rnd];
	}

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied event){
		Hitsplat hs = event.getHitsplat();
		Actor actor = event.getActor();
		String[] messages = config.messages().split(",");
		if(hs.isMine() && !(actor instanceof Player) && hs.getAmount() >= config.damageThreshold()){
			actor.setOverheadCycle(MESSAGE_DURATION);
			actor.setOverheadText(randomString(messages));
		}
	}

	@Provides
	BonkMessagesConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BonkMessagesConfig.class);
	}
}
