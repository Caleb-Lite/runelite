package net.runelite.client.plugins.pluginhub.com.coopermor.lairentry;

import com.google.inject.Provides;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Actor;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.events.AnimationChanged;
import net.runelite.api.gameval.AnimationID;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;

@Slf4j
@PluginDescriptor(
	name = "Lair Entry Alarm"
)
public class LairEntryAlarmPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private LairEntryAlarmConfig config;

	@Inject
	private Notifier notifier;

	private List<String> blacklist = new CopyOnWriteArrayList<>();

	@Override
	public void startUp()
	{
		blacklist = Text.fromCSV(config.blacklist());
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (event.getGroup().equals("lairentryalarm"))
		{
			blacklist = Text.fromCSV(config.blacklist());
		}
	}

	@Subscribe
	public void onAnimationChanged(final AnimationChanged event){
		final Actor actor = event.getActor();
		if (!(actor instanceof Player))
		{
			return;
		}
		if(actor.getAnimation() != AnimationID.GODWARS_HUMAN_CRAWLING)
		{
			return;
		}
		if(config.blacklistEnabled())
		{
			if (blacklist.contains(actor.getName()))
			{
				return;
			}
		}
		notifier.notify(config.lairEntryNotification(), "Player entered lair!");
	}

	@Provides
	LairEntryAlarmConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(LairEntryAlarmConfig.class);
	}
}
