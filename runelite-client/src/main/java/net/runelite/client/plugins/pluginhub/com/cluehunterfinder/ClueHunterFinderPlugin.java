package net.runelite.client.plugins.pluginhub.com.cluehunterfinder;

import com.google.inject.Provides;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Item;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.CommandExecuted;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.worldmap.WorldMapPointManager;

@Slf4j
@PluginDescriptor(
	name = "Clue Hunter Finder"
)
public class ClueHunterFinderPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClueHunterFinderConfig config;

	@Inject
	private ItemManager itemManager;

	@Inject
	private WorldMapPointManager worldMapPointManager;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private ClueHunterFinderOverlay overlay;

	@Inject
	private CommandHandler handler;

	@Inject
	private ClueHunterFinderHintArrow hintArrow;

	@Getter
	private ClueHunterItem itemToFind;

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown()
	{
		itemToFind = null;
		hintArrow.clear();
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onItemContainerChanged(final ItemContainerChanged event)
	{
		final List<Item> items = Arrays.asList(event.getItemContainer().getItems());
		final WorldPoint playerLocation = client.getLocalPlayer().getWorldLocation();

		for (final ClueHunterItem item : ClueHunterItem.values())
		{
			if (playerLocation.equals(item.getLocation()) || items.contains(item.getId()))
			{
				itemToFind = null;
				hintArrow.clear();
				return;
			}
		}
	}

	@Subscribe
	void onCommandExecuted(final CommandExecuted command)
	{
		if (command.getCommand().contentEquals("find"))
		{
			final String[] arguments = command.getArguments();
			itemToFind = handler.getItemForCommand(arguments[0]);
			if (itemToFind == null)
			{
				hintArrow.clear();
			}
		}
	}

	// TODO: Find a less frequent trigger for this that still leads to an accurate arrow depiction
	@Subscribe
	public void onGameTick(final GameTick tick)
	{
		if (itemToFind != null)
		{
			hintArrow.setArrow(itemToFind.getLocation());
		}
	}

	@Provides
	ClueHunterFinderConfig provideConfig(final ConfigManager configManager)
	{
		return configManager.getConfig(ClueHunterFinderConfig.class);
	}
}
