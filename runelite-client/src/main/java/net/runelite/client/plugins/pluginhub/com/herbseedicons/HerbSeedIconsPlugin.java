package net.runelite.client.plugins.pluginhub.com.herbseedicons;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
		name = "Recolored Herb Seed Icons",
		description = "Recolors herb seed icons to match their respective herb. Plugin by Mike-U5, Icons by /u/Narsuaq",
		tags = {"seeds", "cosmetic", "inventory", "icon"}
)
public class HerbSeedIconsPlugin extends Plugin
{
	@Inject
	private HerbSeedOverlay overlay;
	@Inject
	private OverlayManager overlayManager;
	@Inject
	private HerbSeedIconsPlugin config;

	@Override
	protected void startUp() {
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() {
		overlayManager.remove(overlay);
	}
}

