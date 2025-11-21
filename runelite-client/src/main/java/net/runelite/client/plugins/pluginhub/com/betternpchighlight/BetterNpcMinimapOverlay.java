package net.runelite.client.plugins.pluginhub.com.betternpchighlight;

import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.NPCComposition;
import net.runelite.api.Point;
import net.runelite.client.plugins.slayer.SlayerPluginService;
import net.runelite.client.ui.overlay.*;
import javax.inject.Inject;
import java.awt.*;
import net.runelite.client.util.Text;

public class BetterNpcMinimapOverlay extends Overlay
{
	private final Client client;

	private final BetterNpcHighlightPlugin plugin;

	private final BetterNpcHighlightConfig config;

	@Inject
	private BetterNpcMinimapOverlay(Client client, BetterNpcHighlightPlugin plugin, BetterNpcHighlightConfig config)
	{
		this.client = client;
		this.plugin = plugin;
		this.config = config;
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_WIDGETS);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		for (NPCInfo npcInfo : plugin.npcList)
		{
			NPC npc = npcInfo.getNpc();
			if (npc.getName() != null && config.npcMinimapMode() != BetterNpcHighlightConfig.npcMinimapMode.OFF)
			{
				Color color = plugin.getSpecificColor(npcInfo);

				NPCComposition npcComposition = npc.getTransformedComposition();
				if (color != null && npcComposition != null && npcComposition.isInteractible())
				{
					Point minimapLocation = npc.getMinimapLocation();
					if (minimapLocation != null)
					{
						if (config.npcMinimapMode() == BetterNpcHighlightConfig.npcMinimapMode.DOT || config.npcMinimapMode() == BetterNpcHighlightConfig.npcMinimapMode.BOTH)
						{
							OverlayUtil.renderMinimapLocation(graphics, minimapLocation, color);
						}

						if (config.npcMinimapMode() == BetterNpcHighlightConfig.npcMinimapMode.NAME || config.npcMinimapMode() == BetterNpcHighlightConfig.npcMinimapMode.BOTH)
						{
							OverlayUtil.renderTextLocation(graphics, minimapLocation, Text.removeTags(npc.getName()), color);
						}
					}
				}
			}
		}
		return null;
	}
}

/*
 * Copyright (c) 2022, Buchus <http://github.com/MoreBuchus>
 * Copyright (c) 2023, geheur <http://github.com/geheur>
 * Copyright (c) 2021, LeikvollE <http://github.com/LeikvollE>
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