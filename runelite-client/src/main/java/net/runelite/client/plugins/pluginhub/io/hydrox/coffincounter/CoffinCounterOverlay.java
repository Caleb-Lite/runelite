package net.runelite.client.plugins.pluginhub.io.hydrox.coffincounter;

import com.google.inject.Inject;
import static io.hydrox.coffincounter.UIUtil.drawString;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Map;

public class CoffinCounterOverlay extends WidgetItemOverlay
{
	private final CoffinCounterPlugin plugin;

	@Inject
	CoffinCounterOverlay(CoffinCounterPlugin plugin)
	{
		this.plugin = plugin;
		showOnInventory();
		showOnBank();
		showOnEquipment();
	}

	@Override
	public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem widgetItem)
	{
		if (!Coffin.ALL_COFFINS().contains(itemId))
		{
			return;
		}
		graphics.setFont(FontManager.getRunescapeSmallFont());
		int idx = 0;
		for (Map.Entry<Shade, Integer> entry : plugin.getStored().entrySet())
		{
			if (entry.getValue() == 0)
			{
				continue;
			}
			final Shade shade = entry.getKey();
			final Integer value = entry.getValue();
			final Rectangle bounds = widgetItem.getCanvasBounds();
			final int drawX = bounds.x + UIUtil.GAP_X * (idx / UIUtil.LABELS_PER_COLUMN);
			final int drawY = (bounds.y + UIUtil.OFFSET_TOP) + UIUtil.GAP_Y * (idx % UIUtil.LABELS_PER_COLUMN);
			if (entry.getValue() == -1)
			{
				drawString(graphics, shade, UIUtil.UNKNOWN, drawX, drawY);
			}
			else
			{
				drawString(graphics, shade, value.toString(), drawX, drawY);
			}
			idx++;
		}
	}
}

/*
 * Copyright (c) 2021 Hydrox6 <ikada@protonmail.ch>
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