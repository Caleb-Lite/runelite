package net.runelite.client.plugins.pluginhub.io.hydrox.quickprayerpreview;

import net.runelite.api.Client;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.RuneLiteConfig;
import net.runelite.client.config.TooltipPositionType;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.ComponentOrientation;
import net.runelite.client.ui.overlay.components.ImageComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import javax.inject.Inject;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

public class QuickPrayerPreviewOverlay extends Overlay
{
	private static final int UNDER_OFFSET = 24;

	private final Client client;
	private final QuickPrayerPreviewPlugin plugin;
	private final RuneLiteConfig runeLiteConfig;

	private final PanelComponent panelComponent = new PanelComponent();

	@Inject
	public QuickPrayerPreviewOverlay(Client client, QuickPrayerPreviewPlugin plugin, final RuneLiteConfig runeLiteConfig)
	{
		this.client = client;
		this.plugin = plugin;
		this.runeLiteConfig = runeLiteConfig;
		setPosition(OverlayPosition.TOOLTIP);
		setLayer(OverlayLayer.ALWAYS_ON_TOP);
		setPriority(Overlay.PRIORITY_HIGH);
		panelComponent.setOrientation(ComponentOrientation.HORIZONTAL);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		panelComponent.getChildren().clear();
		Widget orb = client.getWidget(ComponentID.MINIMAP_PRAYER_ORB);
		List<Prayer> prayers = plugin.getQuickPrayers();
		if (prayers == null || orb == null || orb.isHidden() || orb.isSelfHidden())
		{
			return null;
		}
		net.runelite.api.Point mouseCanvasPosition = client.getMouseCanvasPosition();

		final int canvasWidth = client.getCanvasWidth();
		final int canvasHeight = client.getCanvasHeight();
		final Rectangle prevBounds = getBounds();

		final int tooltipX = Math.min(canvasWidth - prevBounds.width, mouseCanvasPosition.getX());
		final int tooltipY = runeLiteConfig.tooltipPosition() == TooltipPositionType.UNDER_CURSOR
		? Math.max(0, mouseCanvasPosition.getY() - 2 - prevBounds.height)
		: Math.min(canvasHeight - prevBounds.height, mouseCanvasPosition.getY() + UNDER_OFFSET);

		if (!orb.getBounds().contains(new Point(mouseCanvasPosition.getX(), mouseCanvasPosition.getY())))
		{
			return null;
		}

		panelComponent.setPreferredLocation(new Point(tooltipX, tooltipY));
		for (Prayer p : prayers)
		{
			BufferedImage img = plugin.getSprite(p);
			if (img != null)
			{
				panelComponent.getChildren().add(new ImageComponent(img));
			}
		}

		panelComponent.setBackgroundColor(runeLiteConfig.overlayBackgroundColor());

		return panelComponent.render(graphics);
	}
}
