package net.runelite.client.plugins.pluginhub.com.nucleon.porttasks;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.util.Set;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.GroundObject;
import net.runelite.api.Scene;
import net.runelite.api.Tile;
import net.runelite.api.WorldView;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;

public class PortTaskModelRenderer extends Overlay
{
	private final Client client;
	private final PortTasksPlugin plugin;
	private final ModelOutlineRenderer modelOutlineRenderer;

	@Inject
	private PortTaskModelRenderer(final Client client, final PortTasksPlugin plugin, final ModelOutlineRenderer modelOutlineRenderer)
	{
		this.client = client;
		this.plugin = plugin;
		this.modelOutlineRenderer = modelOutlineRenderer;

		setPosition(OverlayPosition.DYNAMIC);
		setPriority(PRIORITY_DEFAULT);
		setLayer(OverlayLayer.ABOVE_SCENE);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (plugin.isHighlightGangplanks())
		{
			highlightGangplanks(graphics);
		}
		if (plugin.isHighlightNoticeboards())
		{
			highlightNoticeboards(graphics);
		}
		return null;
	}

	private void highlightGangplanks(Graphics2D graphics)
	{
		final Set<GameObject> objects = plugin.getGangplanks();
		final Color color = plugin.getHighlightGangplanksColor();
		final Stroke stroke = new BasicStroke(1f);

		if (objects.isEmpty())
		{
			return;
		}
		for (GameObject object : objects)
		{
			WorldView wv = object.getWorldView();
			if (wv == null || object.getPlane() != wv.getPlane())
			{
				continue;
			}
			Scene scene = wv.getScene();
			Tile[][][] tiles = scene.getTiles();
			Tile tile = tiles[0][object.getLocalLocation().getSceneX()][object.getLocalLocation().getSceneY()];
			GroundObject groundObject = tile.getGroundObject();
			if (groundObject == null)
			{
				continue;
			}
			final Shape polygon = groundObject.getConvexHull();
			if (polygon == null)
			{
				continue;
			}
			OverlayUtil.renderPolygon(graphics, polygon, color, stroke);
		}
	}

	private void highlightNoticeboards(Graphics2D graphics)
	{
		final Set<GameObject> objects = plugin.getNoticeboards();
		final Color color = plugin.getHighlightNoticeboardsColor();
		final Stroke stroke = new BasicStroke(1f);
		if (objects.isEmpty())
		{
			return;
		}
		for (GameObject object : objects)
		{
			WorldView wv = object.getWorldView();
			if (wv == null || object.getPlane() != wv.getPlane())
			{
				continue;
			}
			final Shape polygon = object.getConvexHull();
			if (polygon == null)
			{
				continue;
			}
			OverlayUtil.renderPolygon(graphics, polygon, color, stroke);
		}
	}
}

/*
 * Copyright (c) 2025, nucleon <https://github.com/nucleon>
 * Copyright (c) 2025, Cooper Morris <https://github.com/coopermor>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
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