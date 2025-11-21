package net.runelite.client.plugins.pluginhub.com.camerakeys;

import com.google.inject.Inject;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.ImageComponent;
import net.runelite.client.util.ImageUtil;

public class CameraKeysOverlay extends OverlayPanel
{
	private final BufferedImage zoomIcon;
	private final CameraKeysConfig config;

	@Inject
	private CameraKeysOverlay(CameraKeysConfig config, CameraKeysPlugin plugin)
	{
		super(plugin);
		setPosition(OverlayPosition.CANVAS_TOP_RIGHT);
		this.config = config;
		setPriority(OverlayPriority.LOW);
		zoomIcon = ImageUtil.loadImageResource(CameraKeysPlugin.class, "zoomIcon.png");
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (config.isZoomIndicatorEnabled())
		{
			panelComponent.getChildren().clear();
			ImageComponent imageComponent = new ImageComponent(zoomIcon);
			panelComponent.getChildren().add(imageComponent);

			return super.render(graphics);
		}
		else
		{
			return null;
		}
	}
}

