package net.runelite.client.plugins.pluginhub.com.agilitypyramidsliderblocktimer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.WorldView;
import net.runelite.api.Point;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.ProgressPieComponent;
import net.runelite.client.util.ColorUtil;

public class AgilityPyramidSliderBlockTimerOverlay extends Overlay
{
	private final Map<Integer, Integer> trapLastPositions = new HashMap<>();
	private final Map<Integer, Instant> trapStartTimers = new HashMap<>();

	private final Client client;
	private final AgilityPyramidSliderBlockTimerConfig config;

	@Inject
	private AgilityPyramidSliderBlockTimerOverlay(Client client, AgilityPyramidSliderBlockTimerPlugin plugin, AgilityPyramidSliderBlockTimerConfig config)
	{
		super(plugin);
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_SCENE);
		this.client = client;
		this.config = config;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		WorldView worldView = client.getLocalPlayer().getWorldView();
		if (worldView != null)
		{
			Color trapHighlightColor = config.getSliderBlockColor();
			worldView.npcs().forEach((npc) -> {
				if (Npcs.TRAP_NPC_IDS.contains(npc.getId()))
				{
					Polygon tilePoly = npc.getCanvasTilePoly();
					Integer lastTrapPosition = trapLastPositions.get(npc.getId());
					WorldPoint currentPosition = npc.getWorldLocation();
					Integer xPos = currentPosition.getX();
					Integer yPos = currentPosition.getY();
					String movementDimension = Npcs.TRAP_MOVEMENT_DIMENSION.get(npc.getId());

					if (lastTrapPosition != null)
					{
						Integer restingPosition = Npcs.TRAP_RESTING_POSITION.get(npc.getId());
						boolean shouldRenderTime = (movementDimension.equals("x") && restingPosition.equals(xPos))
								|| (movementDimension.equals("y") && restingPosition.equals(yPos));

						if (shouldRenderTime && tilePoly != null)
						{
							if ((movementDimension.equals("x") && !lastTrapPosition.equals(xPos))
									|| (movementDimension.equals("y") && !lastTrapPosition.equals(yPos)))
							{
								trapStartTimers.put(npc.getId(), Instant.now());
							}

							final String restingTime = Npcs.TRAP_RESTING_TIME.get(npc.getId());
							final Duration trapTime = Duration.parse(restingTime);
							Instant timer = trapStartTimers.get(npc.getId());
							if (timer == null)
							{
								timer = Instant.now();
							}

							final ProgressPieComponent progressPie = new ProgressPieComponent();
							int xPiePos = (tilePoly.xpoints[0] + tilePoly.xpoints[2]) / 2;
							int yPiePos = (tilePoly.ypoints[0] + tilePoly.ypoints[2]) / 2;

							final Point position = new Point(xPiePos, yPiePos);

							Color trapBorder = ColorUtil.colorWithAlpha(trapHighlightColor, 255);

							progressPie.setFill(trapHighlightColor);
							progressPie.setBorderColor(trapBorder);
							progressPie.setPosition(position);

							final Duration duration = Duration.between(timer, Instant.now());
							progressPie.setProgress(1 - (duration.compareTo(trapTime) < 0
									? (double) duration.toMillis() / trapTime.toMillis()
									: 1));

							progressPie.render(graphics);
						}
					}

					if (movementDimension.equals("x"))
					{
						trapLastPositions.put(npc.getId(), currentPosition.getX());
					}
					else if (movementDimension.equals("y"))
					{
						trapLastPositions.put(npc.getId(), currentPosition.getY());
					}
				}
			});
		}

		return null;
	}
}
