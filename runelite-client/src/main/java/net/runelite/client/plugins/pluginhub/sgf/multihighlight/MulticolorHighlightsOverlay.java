package net.runelite.client.plugins.pluginhub.sgf.multihighlight;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.Set;
import javax.inject.Inject;
import net.runelite.api.NPC;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class MulticolorHighlightsOverlay extends Overlay
{
	private MulticolorHighlightsPlugin plugin;
	private MulticolorHighlightsConfig config;

	@Inject
	public MulticolorHighlightsOverlay(MulticolorHighlightsPlugin plugin, MulticolorHighlightsConfig config) {
		this.plugin = plugin;
		this.config = config;
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_SCENE);
	}

	@Override
	public Dimension render(Graphics2D graphics) {
		int group = 1;
		for (Set<NPC> highlights : plugin.getGroupHighlights()) {
			for (NPC npc : highlights) {
				renderNpcOverlay(graphics, npc, plugin.getGroupColor(group), plugin.getGroupFillColor(group));
			}
			group++;
		}
		return null;
	}

	private void renderNpcOverlay(Graphics2D graphics, NPC npc, Color color, Color fillColor) {
		if (config.highlightHullOutline()) {
			Shape shape = npc.getConvexHull();
			if (shape != null) {
				graphics.setColor(color);
				graphics.setStroke(new BasicStroke(config.getOutlineStrokeWidth()));
				graphics.draw(shape);
			}
		}
		if (config.highlightHullFill()) {
			Shape shape = npc.getConvexHull();
			if (shape != null) {
				graphics.setColor(fillColor);
				graphics.fill(shape);
			}
		}
	}
}

