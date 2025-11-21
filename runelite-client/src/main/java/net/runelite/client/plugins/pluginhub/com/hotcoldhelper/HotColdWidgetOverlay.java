package net.runelite.client.plugins.pluginhub.com.hotcoldhelper;

import net.runelite.api.Client;
import net.runelite.api.widgets.Widget;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

@Singleton
public class HotColdWidgetOverlay extends OverlayPanel {
	private final Client client;
	private final HotColdHelperPlugin plugin;
	private static final Color HIGHLIGHT_COLOR = new Color(255, 90, 0, 155);

	@Inject
	public HotColdWidgetOverlay(Client client, HotColdHelperPlugin plugin) {
		this.client = client;
		this.plugin = plugin;

		setLayer(OverlayLayer.ABOVE_WIDGETS);
		setPosition(OverlayPosition.DYNAMIC);
		setPriority(PRIORITY_HIGH);
	}

	@Override
	public Dimension render(Graphics2D graphics) {
		if (!plugin.isHotColdClueActive()) {
			return null;
		}

		int possibleLocations = plugin.getPossibleLocationsCount();
		if (possibleLocations <= 1) {
			return null;
		}

		HotColdTeleports bestTeleport = plugin.getBestTeleport();
		if (bestTeleport == null) {
			return null;
		}

		int componentId = bestTeleport.getComponentId();
		if (componentId == -1) {
			return null;
		}

		String teleportType = bestTeleport.getTeleportType();
		if ("Standard".equals(teleportType) ||
			"Ancients".equals(teleportType) ||
			"Arceuus".equals(teleportType)) {

			Widget spellbookWidget = client.getWidget(InterfaceID.MagicSpellbook.SPELLBOOK_SWAP);
			if (spellbookWidget != null && !spellbookWidget.isHidden()) {
				Widget spellbookParent = spellbookWidget.getParent();
				if (spellbookParent != null) {
					graphics.setClip(spellbookParent.getBounds());
				}
				graphics.setColor(HIGHLIGHT_COLOR);
				graphics.fill(spellbookWidget.getBounds());
			}
		}

		Widget widget = client.getWidget(componentId);
		if (widget == null || widget.isHidden()) {
			return null;
		}

		Widget parentWidget = widget.getParent();
		if (parentWidget != null) {
			graphics.setClip(parentWidget.getBounds());
		}

		graphics.setColor(HIGHLIGHT_COLOR);
		graphics.fill(widget.getBounds());

		return null;
	}
}
/*
 * Copyright (c) 2025, Lalochazia
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
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
