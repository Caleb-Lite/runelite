package net.runelite.client.plugins.pluginhub.com.example;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.SpriteManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

@Slf4j
@PluginDescriptor(
	name = "Worldhop counter"
)
public class WorldhopCounterPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	InfoBoxManager myInfoboxManager;
	@Inject
	public SpriteManager mySpriteManager;

	public Integer myAmountOfHops;

	WorldHopCounterInfobox myInfobox;

	@Override
	protected void startUp() throws Exception
	{
		myAmountOfHops = 0;
	}

	@Override
	protected void shutDown() throws Exception
	{
		if (myInfobox != null)
		{
			myInfoboxManager.removeInfoBox(myInfobox);
		}
		myInfobox = null;
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.HOPPING)
		{
			myAmountOfHops++;
		}

		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			if (myAmountOfHops > 0)
			{
				if (myInfobox == null)
				{
					myInfobox = new WorldHopCounterInfobox(this);
					myInfoboxManager.addInfoBox(myInfobox);
				}
			}
		}
	}
}

