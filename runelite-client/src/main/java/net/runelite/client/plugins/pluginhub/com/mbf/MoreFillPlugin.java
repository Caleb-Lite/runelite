package net.runelite.client.plugins.pluginhub.com.mbf;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.ItemComposition;
import net.runelite.api.ItemID;
import net.runelite.api.events.PostItemComposition;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
	name = "More Bank Fillers"
)
public class MoreFillPlugin extends Plugin
{

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private MoreFillOverlay overlay;


	@Override
	protected void startUp() throws Exception
	{
		resetCaches();
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		resetCaches();
		overlayManager.remove(overlay);
	}

	// Ref unidentified-herbs by hex-agon
	@Subscribe
	public void onPostItemComposition(PostItemComposition event) {
		ItemComposition itemComposition = event.getItemComposition();
		if (itemComposition.getId() == ItemID.BANK_FILLER) {
			itemComposition.setInventoryModel(-1);
		}
	}

	@Provides
	MoreFillConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(MoreFillConfig.class);
	}


	// Ref unidentified-herbs by hex-agon
	private void resetCaches() {
		clientThread.invokeLater(() -> {
			client.getItemCompositionCache().reset();
			client.getItemModelCache().reset();
			client.getItemSpriteCache().reset();
		});
	}


}
