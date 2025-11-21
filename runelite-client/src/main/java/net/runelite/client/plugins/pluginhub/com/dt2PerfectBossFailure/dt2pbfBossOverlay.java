package net.runelite.client.plugins.pluginhub.com.dt2PerfectBossFailure;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import lombok.extern.slf4j.Slf4j;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.NPCComposition;
import net.runelite.api.Perspective;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;
import org.apache.commons.lang3.ArrayUtils;

@Slf4j
public class dt2pbfBossOverlay extends Overlay {
	@Inject
	private Client client;

	@Inject
	private dt2pbfPlugin plugin;

	@Inject
	private dt2pbfConfig config;

	@Inject
	private ModelOutlineRenderer modelOutlineRenderer;

	public dt2pbfBossOverlay()
	{
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_SCENE);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		Stroke stroke = new BasicStroke((float) config.borderWidth());
		for (dt2boss boss : dt2boss.values())
		{
			if (boss.render()==overlayTypes.NONE)
			{
				continue;
			}
			for (NPC npc : client.getNpcs())
			{
				if (ArrayUtils.contains(boss.getIds(), npc.getId()))
				{
					NPCComposition npcComposition = npc.getTransformedComposition();
					int size = npcComposition.getSize();
					Color color = plugin.notified ? boss.getFailureColor() : boss.getPerfectColor();
					int alpha = (int) (color.getAlpha() * 0.3);
					Color fillColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
					switch (boss.render())
					{
						case HULL:
							Shape hull = npc.getConvexHull();
							if (hull != null)
							{
								OverlayUtil.renderPolygon(graphics, hull, color, fillColor, stroke);
							}
							break;
						case TILE:
							Polygon poly = npc.getCanvasTilePoly();
							if (poly != null)
							{
								OverlayUtil.renderPolygon(graphics, poly, color, fillColor, stroke);
							}
							break;
						// Snippets from the BetterNPCHighlightOverlay.java in both of the following
						case TRUE_TILE:
							LocalPoint lp = LocalPoint.fromWorld(client, npc.getWorldLocation());
							if (lp != null)
							{
								lp = new LocalPoint(lp.getX() + size * 128 / 2 - 64, lp.getY() + size * 128 / 2 - 64);
								Polygon tile = Perspective.getCanvasTileAreaPoly(client, lp, size);
								if (tile != null)
								{
									OverlayUtil.renderPolygon(graphics, tile, color, fillColor, stroke);
								}
							}
							break;
						case OUTLINE:
							modelOutlineRenderer.drawOutline(npc,(int)config.borderWidth(),color,config.feather());
							break;

					}
				}
			}
		}
		return null;
	}
}
