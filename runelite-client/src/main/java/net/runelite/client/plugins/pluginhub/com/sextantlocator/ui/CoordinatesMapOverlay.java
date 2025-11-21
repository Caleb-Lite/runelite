package net.runelite.client.plugins.pluginhub.com.sextantlocator.ui;

import net.runelite.client.plugins.pluginhub.com.sextantlocator.SextantLocatorConfig;
import net.runelite.client.plugins.pluginhub.com.sextantlocator.SextantPoint;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Point;
import net.runelite.api.RenderOverview;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.worldmap.WorldMapOverlay;

public class CoordinatesMapOverlay extends Overlay
{
	private final int TEXT_MARGIN = 5;

	private final Client client;
	private final WorldMapOverlay worldMapOverlay;
	private final SextantLocatorConfig sextantLocatorConfig;

	@Inject
	public CoordinatesMapOverlay(Client client, WorldMapOverlay worldMapOverlay, SextantLocatorConfig sextantLocatorConfig)
	{
		this.client = client;
		this.worldMapOverlay = worldMapOverlay;
		this.sextantLocatorConfig = sextantLocatorConfig;

		setPosition(OverlayPosition.DYNAMIC);
		setPriority(OverlayPriority.HIGH);
		setLayer(OverlayLayer.MANUAL);
		drawAfterInterface(WidgetID.WORLD_MAP_GROUP_ID);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!sextantLocatorConfig.displayMapCoordinatesOverlay())
		{
			return null;
		}

		RenderOverview renderOverview = client.getRenderOverview();
		Widget worldMapWidget = client.getWidget(WidgetInfo.WORLD_MAP_VIEW);

		if (renderOverview == null || worldMapWidget == null)
		{
			return null;
		}

		WorldPoint mapCenterPoint = new WorldPoint(renderOverview.getWorldMapPosition().getX(), renderOverview.getWorldMapPosition().getY(), 0);
		Rectangle worldMapBounds = worldMapWidget.getBounds();

		renderCrosshair(graphics, mapCenterPoint, worldMapBounds);

		SextantPoint mapCenterSextantPoint = new SextantPoint(mapCenterPoint.getX(), mapCenterPoint.getY());

		renderCoordinates(graphics, mapCenterSextantPoint, worldMapBounds);

		return null;
	}

	private void renderCrosshair(Graphics graphics, WorldPoint mapCenterPoint, Rectangle worldMapBounds)
	{
		Point middle = worldMapOverlay.mapWorldPointToGraphicsPoint(mapCenterPoint);

		if (middle == null)
		{
			return;
		}

		graphics.setClip(worldMapBounds);
		graphics.setColor(sextantLocatorConfig.coordinateMapOverlayCrosshairColor());
		graphics.drawLine(middle.getX(), worldMapBounds.y, middle.getX(), worldMapBounds.y + worldMapBounds.height);
		graphics.drawLine(worldMapBounds.x, middle.getY(), worldMapBounds.x + worldMapBounds.width, middle.getY());
	}

	private void renderCoordinates(Graphics graphics, SextantPoint mapCenterSextantPoint, Rectangle worldMapBounds)
	{
		String output = mapCenterSextantPoint.toString();
		FontMetrics fontMetrics = graphics.getFontMetrics();
		int textHeight = fontMetrics.getHeight();
		int textWidth = fontMetrics.stringWidth(output);
		int textX = (int) worldMapBounds.getX() + TEXT_MARGIN;
		int textY = (int) worldMapBounds.getY() + TEXT_MARGIN + textHeight;

		int rectangleX = (int) worldMapBounds.getX();
		int rectangleY = (int) worldMapBounds.getY();
		int rectangleWidth = textWidth + TEXT_MARGIN * 2;
		int rectangleHeight = textHeight + TEXT_MARGIN * 2;

		graphics.setColor(sextantLocatorConfig.coordinateMapOverlayBackgroundColor());
		graphics.fillRect(rectangleX, rectangleY, rectangleWidth, rectangleHeight);

		graphics.setColor(sextantLocatorConfig.coordinateMapOverlayTextColor());
		graphics.drawString(output, textX, textY);
	}
}

/*
 * BSD 2-Clause License
 *
 * Copyright (c) 2021, Miguel Sousa
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */