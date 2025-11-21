package net.runelite.client.plugins.pluginhub.com.toggleitemstats.banking;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.WidgetClosed;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.WidgetID;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Bank Item Stats Toggle"
)
public class BankItemStatsTogglePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private BankItemStatsToggleConfig config;

	@Inject
	private ConfigManager configManager;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Bank Item Stats Toggle started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Bank Item Stats Toggle stopped!");
	}

	@Subscribe void onWidgetLoaded(WidgetLoaded event){
		if (event.getGroupId() == WidgetID.BANK_GROUP_ID)
		{
			if (config.equipmentStats())
			{
				configManager.setConfiguration("itemstat","equipmentStats",true);
			}

			if (config.consumableStats())
			{
				configManager.setConfiguration("itemstat","consumableStats",true);
			}
		}
	}

	@Subscribe void onWidgetClosed(WidgetClosed event){
		if(event.getGroupId() == WidgetID.BANK_GROUP_ID){
			if (config.equipmentStats())
			{
				configManager.setConfiguration("itemstat","equipmentStats",false);
			}

			if (config.consumableStats())
			{
				configManager.setConfiguration("itemstat","consumableStats",false);
			}
		}
	}

	@Provides
	BankItemStatsToggleConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BankItemStatsToggleConfig.class);
	}
}

