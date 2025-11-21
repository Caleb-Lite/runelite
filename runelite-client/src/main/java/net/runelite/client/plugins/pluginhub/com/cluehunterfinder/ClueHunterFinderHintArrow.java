package net.runelite.client.plugins.pluginhub.com.cluehunterfinder;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.coords.WorldPoint;

@Slf4j
public class ClueHunterFinderHintArrow
{

	private final static int MIN_DRAW_DISTANCE = 64;
	private final Client client;

	@Inject
	ClueHunterFinderHintArrow(final Client client)
	{
		this.client = client;
	}

	void setArrow(final WorldPoint target)
	{
		clear();
		if (client.getLocalPlayer() == null)
		{
			return;
		}
		final WorldPoint nextWorldPoint = computeNextWorldPoint(target, client.getLocalPlayer().getWorldLocation());
		client.setHintArrow(nextWorldPoint);
	}

	void clear()
	{
		client.clearHintArrow();
	}

	private WorldPoint computeNextWorldPoint(final WorldPoint target, final WorldPoint current)
	{
		final int distance = current.distanceTo(target);
		final double distanceRatio = MIN_DRAW_DISTANCE / (double) distance;
		if (distanceRatio >= 1)
		{
			return target;
		}

		final int nextWorldPointX = (int) Math.floor((target.getX() - current.getX()) * distanceRatio) + current.getX();
		final int nextWorldPointY = (int) Math.floor((target.getY() - current.getY()) * distanceRatio) + current.getY();
		return new WorldPoint(nextWorldPointX, nextWorldPointY, 0);
	}
}


