package net.runelite.client.plugins.pluginhub.com.towncrier;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.ThreadLocalRandom;
import net.runelite.api.Actor;
import java.util.ArrayList;
import net.runelite.api.Client;
import net.runelite.api.events.OverheadTextChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Town Crier's Cries"
)
public class TownCrierPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private TownCrierConfig config;

	@Subscribe
	public void onOverheadTextChanged(OverheadTextChanged event)
	{
		Actor actor = event.getActor();
		if (actor.getName() != null && actor.getName().equals("Town Crier")){
			String message = getMessage();
			actor.setOverheadText(message);
		}
	}

	public String getMessage(){
		ArrayList<String> messages = new ArrayList<String>();
		if(!config.cry1().isEmpty())
		{
			messages.add(config.cry1());
		}
		if(!config.cry2().isEmpty())
		{
			messages.add(config.cry2());
		}
		if(!config.cry3().isEmpty())
		{
			messages.add(config.cry3());
		}
		int rand = ThreadLocalRandom.current().nextInt(0, messages.size());
		return messages.get(rand);
	}

	@Provides
	TownCrierConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TownCrierConfig.class);
	}
}
