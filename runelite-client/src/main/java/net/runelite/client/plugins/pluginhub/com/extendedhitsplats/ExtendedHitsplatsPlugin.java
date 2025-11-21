package net.runelite.client.plugins.pluginhub.com.extendedhitsplats;

import net.runelite.client.plugins.pluginhub.com.extendedhitsplats.overlays.ExtendedHitsplatsOverlay;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Hitsplat;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@PluginDescriptor(
	name = "Extended Hitsplats",description = "This plugin will allow you to see more than four hitsplats on a character",enabledByDefault = true, tags = {"extended", "hitsplat"}
)
public class ExtendedHitsplatsPlugin extends Plugin
{
	@Inject
	private Client client;
	@Inject
	private ExtendedHitsplatsConfig config;
	@Inject
	private OverlayManager overlayManager;
	@Inject
	private ExtendedHitsplatsOverlay overlay;
	public static List<HitsplatApplied> appliedHitsplatList = new CopyOnWriteArrayList<>();

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
		log.info("Extended Hitsplats started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		log.info("Extended Hitsplats stopped!");
	}

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied hitsplatApplied){
		Hitsplat hitsplat = hitsplatApplied.getHitsplat();
		appliedHitsplatList.add(hitsplatApplied);
	}

	@Subscribe
	public void onGameTick(GameTick gameTick){
		int clientGameCycle = client.getGameCycle();
		if (appliedHitsplatList == null){
			return;
		}
		if (appliedHitsplatList.size() == 0){
			return;
		}
		for (HitsplatApplied hitsplatApplied : appliedHitsplatList){
			int disappear = hitsplatApplied.getHitsplat().getDisappearsOnGameCycle();
			if (clientGameCycle > disappear){
				appliedHitsplatList.remove(hitsplatApplied);
			}
		}
	}

	@Provides
	ExtendedHitsplatsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ExtendedHitsplatsConfig.class);
	}
}
