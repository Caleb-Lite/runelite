package net.runelite.client.plugins.pluginhub.begosrs.barbarianassault.groundmarkers;

import net.runelite.client.plugins.pluginhub.begosrs.barbarianassault.BaMinigameConfig;
import net.runelite.client.plugins.pluginhub.begosrs.barbarianassault.BaMinigamePlugin;
import net.runelite.client.plugins.pluginhub.begosrs.barbarianassault.Role;
import net.runelite.client.plugins.pluginhub.begosrs.barbarianassault.Wave;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GroundObject;
import net.runelite.api.Perspective;
import net.runelite.api.Player;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;
import java.util.List;

@Slf4j
public class BrokenTrapsOverlay extends Overlay
{
	private static final int MAX_DRAW_DISTANCE = 32;

	private final Client client;
	private final BaMinigameConfig config;
	private final BaMinigamePlugin plugin;

	@Inject
	private BrokenTrapsOverlay(Client client, BaMinigameConfig config, BaMinigamePlugin plugin)
	{
		this.client = client;
		this.config = config;
		this.plugin = plugin;
		setPosition(OverlayPosition.DYNAMIC);
		setPriority(0f);
		setLayer(OverlayLayer.ABOVE_SCENE);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!config.highlightBrokenTraps())
		{
			return null;
		}
		if (plugin.getRole() != Role.DEFENDER)
		{
			return null;
		}
		final Wave wave = plugin.getWave();
		if (wave == null || wave.isComplete())
		{
			return null;
		}

		final List<GroundObject> traps = plugin.getBrokenTraps();
		if (traps.isEmpty())
		{
			return null;
		}

		Player player = client.getLocalPlayer();
		if (player == null)
		{
			return null;
		}

		final Color color = config.highlightBrokenTrapsColor();
		final int opacity = config.highlightBrokenTrapsOpacity();
		final Stroke stroke = new BasicStroke((float) config.highlightBrokenTrapsBorderWidth());
		for (final GroundObject trap : traps)
		{
			WorldPoint worldPoint = trap.getWorldLocation();
			WorldPoint playerLocation = player.getWorldLocation();

			if (worldPoint.distanceTo(playerLocation) >= MAX_DRAW_DISTANCE)
			{
				continue;
			}

			LocalPoint lp = LocalPoint.fromWorld(client, worldPoint);
			if (lp == null)
			{
				continue;
			}

			Polygon poly = Perspective.getCanvasTilePoly(client, lp);
			if (poly != null)
			{
				OverlayUtil.renderPolygon(graphics, poly, color, new Color(0, 0, 0, opacity), stroke);
			}
		}

		return null;
	}

}

