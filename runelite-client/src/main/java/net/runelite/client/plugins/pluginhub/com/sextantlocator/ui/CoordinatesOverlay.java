package net.runelite.client.plugins.pluginhub.com.sextantlocator.ui;

import net.runelite.client.plugins.pluginhub.com.sextantlocator.SextantLocatorConfig;
import net.runelite.client.plugins.pluginhub.com.sextantlocator.SextantPoint;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

public class CoordinatesOverlay extends OverlayPanel
{
	private final Client client;
	private final SextantLocatorConfig sextantLocatorConfig;

	@Inject
	public CoordinatesOverlay(Client client, SextantLocatorConfig sextantLocatorConfig)
	{
		this.client = client;
		this.sextantLocatorConfig = sextantLocatorConfig;

		setPosition(OverlayPosition.TOP_LEFT);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!sextantLocatorConfig.displayCoordinatesOverlay())
		{
			return null;
		}

		addOverlayComponents();

		return super.render(graphics);
	}

	private void addOverlayComponents()
	{
		if (client.getLocalPlayer() == null)
		{
			return;
		}

		int playerX = client.getLocalPlayer().getWorldLocation().getX();
		int playerY = client.getLocalPlayer().getWorldLocation().getY();
		SextantPoint playerSextantPoint = new SextantPoint(playerX, playerY);

		LineComponent titleLine = LineComponent.builder().left("Sextant coordinates:").build();
		LineComponent xCoordinateLine = LineComponent.builder().right(playerSextantPoint.getXCoordinate().toString()).build();
		LineComponent yCoordinateLine = LineComponent.builder().right(playerSextantPoint.getYCoordinate().toString()).build();

		panelComponent.getChildren().add(titleLine);
		panelComponent.getChildren().add(yCoordinateLine);
		panelComponent.getChildren().add(xCoordinateLine);
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