package net.runelite.client.plugins.pluginhub.com.animatedicons;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
		name = "Animated Icons",
		description = "Display animated gif icons for items that have them",
		tags = {"animation", "animated", "bank", "inventory", "icon"}
)
public class AnimatedIconsPlugin extends Plugin
{
	@Inject
	private AnimatedIconsOverlay overlay;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private AnimatedIconsPlugin config;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}
}
