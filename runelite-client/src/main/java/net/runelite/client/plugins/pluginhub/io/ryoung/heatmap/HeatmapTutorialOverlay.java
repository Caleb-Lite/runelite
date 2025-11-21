package net.runelite.client.plugins.pluginhub.io.ryoung.heatmap;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;

public class HeatmapTutorialOverlay extends OverlayPanel
{
	private final Client client;
	private final HeatmapPlugin plugin;

	@Inject
	private HeatmapTutorialOverlay(Client client, HeatmapPlugin plugin)
	{
		super(plugin);
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_WIDGETS);
		this.client = client;
		this.plugin = plugin;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!plugin.isBankVisible())
		{
			return null;
		}

		Widget button = client.getWidget(ComponentID.BANK_SETTINGS_BUTTON);
		if (button == null || button.isSelfHidden() || button.getDynamicChildren()[0].getSpriteId() != 195)
		{
			return null;
		}

		Rectangle bounds = button.getBounds();

		graphics.setColor(ColorScheme.BRAND_ORANGE);
		graphics.setStroke(new BasicStroke(2));
		graphics.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);

		FontMetrics font = graphics.getFontMetrics();
		int width = font.stringWidth("Right click this button");

		graphics.setColor(ColorScheme.DARKER_GRAY_COLOR);
		graphics.fillRect(bounds.x + bounds.width + 2, bounds.y - 15, width + 6, 30);


		graphics.setColor(ColorScheme.BRAND_ORANGE);
		graphics.drawString("Right click this button", bounds.x + bounds.width + 5, bounds.y);
		graphics.drawString("for Heatmap overlay", bounds.x + bounds.width + 5, bounds.y + 12);

		return super.render(graphics);
	}
}

/*
 * Copyright (c) 2019, Ron Young <https://github.com/raiyni>
 * All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *     list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
