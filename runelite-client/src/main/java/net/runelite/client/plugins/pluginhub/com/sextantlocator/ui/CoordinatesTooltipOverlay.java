package net.runelite.client.plugins.pluginhub.com.sextantlocator.ui;

import net.runelite.client.plugins.pluginhub.com.sextantlocator.SextantLocatorConfig;
import net.runelite.client.plugins.pluginhub.com.sextantlocator.SextantPoint;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Tile;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.tooltip.Tooltip;
import net.runelite.client.ui.overlay.tooltip.TooltipManager;

public class CoordinatesTooltipOverlay extends Overlay
{
	private final Client client;
	private final SextantLocatorConfig sextantLocatorConfig;
	private final TooltipManager tooltipManager;

	@Inject
	public CoordinatesTooltipOverlay(Client client, SextantLocatorConfig sextantLocatorConfig, TooltipManager tooltipManager)
	{
		this.client = client;
		this.sextantLocatorConfig = sextantLocatorConfig;
		this.tooltipManager = tooltipManager;

		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_SCENE);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!sextantLocatorConfig.displayTileCoordinatesOverlay())
		{
			return null;
		}

		addTooltip();
		renderSelectedTile(graphics);

		return null;
	}

	private void addTooltip()
	{
		Tile selectedTile = client.getSelectedSceneTile();

		if (selectedTile == null || selectedTile.getWorldLocation() == null)
		{
			return;
		}

		int selectedTileX = selectedTile.getWorldLocation().getX();
		int selectedTileY = selectedTile.getWorldLocation().getY();
		SextantPoint selectedTileSextantCoordinates;

		selectedTileSextantCoordinates = new SextantPoint(selectedTileX, selectedTileY);

		tooltipManager.add(new Tooltip(selectedTileSextantCoordinates.toString()));
	}

	private void renderSelectedTile(Graphics2D graphics)
	{
		Tile selectedTile = client.getSelectedSceneTile();

		if (selectedTile == null || selectedTile.getLocalLocation() == null)
		{
			return;
		}

		final Polygon selectedTilePolygon = Perspective.getCanvasTilePoly(client, selectedTile.getLocalLocation());

		if (selectedTilePolygon == null)
		{
			return;
		}

		OverlayUtil.renderPolygon(graphics, selectedTilePolygon, sextantLocatorConfig.selectedTileBorderColor(), sextantLocatorConfig.selectedTileFillColor(), new BasicStroke(sextantLocatorConfig.selectedTileBorderWidth()));
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