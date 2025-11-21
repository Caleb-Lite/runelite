package net.runelite.client.plugins.pluginhub.com.guestindicators;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.runelite.api.Player;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.OverlayUtil;

import static com.guestindicators.OrigConfig.*;

@Singleton
public class GuestIndicatorsMinimapOverlay extends Overlay
{
	private final GuestIndicatorsService guestIndicatorsService;
	private final GuestIndicatorsConfig config;
	private final ConfigManager configManager;

	@Inject
	private GuestIndicatorsMinimapOverlay(GuestIndicatorsConfig config, GuestIndicatorsService guestIndicatorsService, ConfigManager configManager)
	{
		this.config = config;
		this.guestIndicatorsService = guestIndicatorsService;
		this.configManager = configManager;
		setLayer(OverlayLayer.ABOVE_WIDGETS);
		setPosition(OverlayPosition.DYNAMIC);
		setPriority(OverlayPriority.HIGHEST);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		guestIndicatorsService.forEachPlayer((player, color) -> renderPlayerOverlay(graphics, player, color));
		return null;
	}

	private void renderPlayerOverlay(Graphics2D graphics, Player actor, Color color)
	{
		final String name = actor.getName().replace('\u00A0', ' ');


		if (configManager.getConfiguration(groupName, DRAW_MINIMAP_NAMES, Boolean.class))
		{
			final net.runelite.api.Point minimapLocation = actor.getMinimapLocation();

			if (minimapLocation != null)
			{
				OverlayUtil.renderTextLocation(graphics, minimapLocation, name, color);
			}
		}
	}
}
