package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.comborunes;

import com.google.inject.Provides;
import java.util.Arrays;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.Player;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "ComboRunesOnly",
	tags = {"rc", "runecraft", "runes", "gotr", "guardians", "rift"}
)
@Singleton
public class ComboRunesOnlyPlugin extends Plugin
{

	@Inject
	private Client client;

	@Inject
	private ComboRunesOnlyConfig config;

	@Inject
	private UpdateNotifier updateNotifier;

	private RemoveMode removeMode;
	private boolean inAltar = false;

	@Override
	public void startUp() throws Exception
	{
		Altar.refreshAllConfig(config);
		removeMode = config.removeMode();
		inAltar = false;
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged e)
	{
		if (ComboRunesOnlyConfig.CONFIG_GROUP.equals(e.getGroup()))
		{
			Altar.refreshAllConfig(config);
			removeMode = config.removeMode();
		}
	}

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded e)
	{
		if (inAltar && isCraftRuneEntry(e))
		{
			client.setMenuEntries(
				removeMode.processMenuEntries(client.getMenuEntries())
			);
		}
	}

	@Subscribe
	public void onGameTick(GameTick g)
	{
		// use game tick to cache whether player is in altar
		Player p = client.getLocalPlayer();
		if (p == null)
		{
			return;
		}

		WorldPoint wp = p.getWorldLocation();
		boolean wasInAltar = this.inAltar;
		this.inAltar = Arrays.stream(Altar.values())
			.filter(Altar::isConfigEnabled)
			.anyMatch(a -> a.inAltar(wp));

		if (inAltar && !wasInAltar) // rising edge
		{
			updateNotifier.checkAndNotify();
		}
	}

	@Provides
	public ComboRunesOnlyConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ComboRunesOnlyConfig.class);
	}

	public static boolean isCraftRuneEntry(MenuEntryAdded entry)
	{
		return isCraftRuneEntry(entry.getTarget(), entry.getOption());
	}

	public static boolean isCraftRuneEntry(MenuEntry entry)
	{
		return isCraftRuneEntry(entry.getTarget(), entry.getOption());
	}

	private static boolean isCraftRuneEntry(String target, String option)
	{
		return target != null &&
			target.contains("Altar") &&
			"Craft-rune".equals(option);
	}

}
