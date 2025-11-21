package net.runelite.client.plugins.pluginhub.thestonedturtle.crabsolver;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.coords.WorldPoint;

public enum CrabTile
{
	// This information came from the TilePacks plugin: https://github.com/TrevorMDev/tile-packs/tree/master
	// Region 1
	R1_ANCHOR_1(Color.YELLOW, "STUN", 13395, 7, 46, 2),
	R1_ANCHOR_2(Color.YELLOW, "STUN", 13395, 8, 48, 2),
	R1_PASSIVE(CrabCrystal.BLACK.getSolutionColor(), "4", 13395, 14, 47, 2),
	R1_MELEE_CLOSE(CrabCrystal.CYAN.getSolutionColor(), "2", 13395, 10, 46, 2),
	R1_MELEE_STUN(CrabCrystal.CYAN.getSolutionColor(), "STUN/MELEE", 13395, 11, 56, 2),
	R1_RANGE(CrabCrystal.MAGENTA.getSolutionColor(), "1/4", 13395, 11, 46, 2),
	R1_MAGE(CrabCrystal.YELLOW.getSolutionColor(), "3", 13395, 10, 47, 2),

	// Region 2
	R2_ANCHOR_1(Color.YELLOW, "STUN", 13139, 25, 41, 2),
	R2_ANCHOR_2(Color.YELLOW, "STUN", 13139, 24, 39, 2),
	R2_PASSIVE(CrabCrystal.BLACK.getSolutionColor(), "2", 13139, 18, 40, 2),
	R2_MELEE(CrabCrystal.CYAN.getSolutionColor(), "1", 13139, 22, 40, 2),
	R2_RANGE(CrabCrystal.MAGENTA.getSolutionColor(), "3", 13139, 17, 40, 2),
	R2_MAGE(CrabCrystal.YELLOW.getSolutionColor(), "4", 13139, 14, 40, 2),
	;

	@Getter
	private final Color color;
	@Getter
	private final String label;
	private final int region;
	private final WorldPoint worldPoint;

	private final Collection<WorldPoint> worldPoints = new ArrayList<>();

	CrabTile(Color c, String l, int region, int x, int y, int z)
	{
		this.color = c;
		this.label = l;
		this.region = region;
		this.worldPoint = WorldPoint.fromRegion(region, x, y, z);
	}

	public void setWorldPoints(Client c)
	{
		worldPoints.clear();
		worldPoints.addAll(WorldPoint.toLocalInstance(c, this.worldPoint));
	}

	public Collection<WorldPoint> getWorldPoints()
	{
		return this.worldPoints;
	}

	private static final Multimap<Integer, CrabTile> BY_REGION = ArrayListMultimap.create();
	static
	{
		for (final CrabTile t : values())
		{
			BY_REGION.put(t.region, t);
		}
	}

	public static Collection<CrabTile> getByRegion(final int region)
	{
		return BY_REGION.get(region);
	}
}

