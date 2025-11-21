package net.runelite.client.plugins.pluginhub.cloudAspect.hypercam2;

import com.google.inject.Provides;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@PluginDescriptor(
		name = "Unregistered Hypercam 2",
		description = "Shows the Unregistered Hypercam 2 logo as an overlay",
		tags = {"overlay", "meme", "fun", "cosmetic"}
)
public class UnregisteredHypercam2Plugin extends Plugin {
	
	@Inject
	private OverlayManager overlayManager;
	
	@Inject
	private UnregisteredHypercam2Overlay overlay;
	
	
	
	@Override
	protected void startUp() throws Exception {
		overlayManager.add(overlay);
	}
	
	@Override
	protected void shutDown() throws Exception {
		overlayManager.remove(overlay);
	}
}
