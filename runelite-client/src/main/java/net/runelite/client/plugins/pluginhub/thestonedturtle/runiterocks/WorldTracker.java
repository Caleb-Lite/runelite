package net.runelite.client.plugins.pluginhub.thestonedturtle.runiterocks;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Getter;
import net.runelite.api.GameObject;
import net.runelite.api.coords.WorldPoint;
import net.runelite.http.api.worlds.World;

@Getter
public class WorldTracker
{
	@Getter(AccessLevel.NONE)
	private final Map<Rock, RuniteRock> rockMap = new HashMap<>();
	private final World world;

	public WorldTracker(final World world)
	{
		this.world = world;
	}

	@Nullable
	public RuniteRock updateRockState(final WorldPoint worldPoint, final GameObject gameObject)
	{
		final Rock rock = Rock.getByWorldPoint(worldPoint);
		if (rock == null)
		{
			return null;
		}

		final RuniteRock runeRock = rockMap.getOrDefault(rock, new RuniteRock(world, rock));
		runeRock.setAvailable(gameObject.getId());
		rockMap.put(rock, runeRock);

		return runeRock;
	}

	public void removeRock(final Rock rock)
	{
		rockMap.remove(rock);
	}

	public void clear()
	{
		rockMap.clear();
	}

	public Collection<RuniteRock> getRuniteRocks()
	{
		return rockMap.values();
	}
}

