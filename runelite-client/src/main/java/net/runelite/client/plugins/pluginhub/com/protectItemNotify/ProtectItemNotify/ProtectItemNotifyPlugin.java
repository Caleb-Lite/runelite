package net.runelite.client.plugins.pluginhub.com.protectItemNotify.ProtectItemNotify;

import com.google.inject.Provides;
import net.runelite.client.plugins.pluginhub.com.protectItemNotify.ProtectItemNotify.ProtectItemNotifyOverlay;
import java.util.Optional;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemContainer;
import net.runelite.api.Prayer;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.api.Varbits;

@Slf4j
@PluginDescriptor(
	name = "Protect Item Notify"
)
public class ProtectItemNotifyPlugin extends Plugin
{
	@Inject private Client client;

	@Inject private ProtectItemNotifyConfig config;

	@Inject private ProtectItemNotifyOverlay protectItemNotifyOverlay;

	@Inject private OverlayManager overlayManager;

	private boolean protectItemOn = true;

	@Override
	protected void startUp() {
		overlayManager.add(protectItemNotifyOverlay);
	}

	@Override
	protected void shutDown() {
		overlayManager.remove(protectItemNotifyOverlay);
	}

	@Subscribe
	public void onGameTick(GameTick event) {
		this.protectItemOn = !client.isPrayerActive(Prayer.PROTECT_ITEM);
	}

	@Provides
	ProtectItemNotifyConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(ProtectItemNotifyConfig.class);
	}

	public boolean isInPVP() {
		return client.getVar(Varbits.PVP_SPEC_ORB) == 1;
	}

	public boolean isProtectItemOn() {
		return protectItemOn;
	}
}

