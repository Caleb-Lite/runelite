package net.runelite.client.plugins.pluginhub.com.regionchat;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;

public enum Region
{
	BARBARIAN_FISHING("barb-fishing-ba", new Zone(new WorldPoint(2495, 3474, 0), new WorldPoint(2527, 3532, 0))),
	TEMPOROSS("tempoross", true, new Zone(12078)),
	ZEAH_RC("zeahrc",
		new Zone(new WorldPoint(1672, 3814, 0), new WorldPoint(1858, 3903, 0)),
		new Zone(new WorldPoint(1636, 3848, 0), new WorldPoint(1671, 3902, 0))
	),
	MOTHERLODE_MINE("motherlode", new Zone(14936)),
	ZALCANO("zalcano", new Zone(12126)),
	SEPULCHRE("sepulchre", new Zone(new WorldPoint(2220, 5760, 0), new WorldPoint(2591, 6039, 3))),
	SULLIUSCEP("sulliuscep", new Zone(new WorldPoint(3627, 3725, 0), new WorldPoint(3697, 3811, 0))),
	ZEAH_CATACOMBS("zeah-catacombs", new Zone(new WorldPoint(1599, 9983, 0), new WorldPoint(1730, 10115, 0))),
	WYRMS("wyrms", new Zone(new WorldPoint(1248, 10144, 0), new WorldPoint(1300, 10209, 0)));

	@Getter
	private final List<Zone> zones;

	@Getter
	private final String name;

	@Getter
	private final boolean isInstance;

	Region(String name, Zone... zone)
	{
		this.name = name;
		this.zones = Arrays.asList(zone);
		this.isInstance = false;
	}

	Region(String name, boolean isInstance, Zone... zone)
	{
		this.name = name;
		this.zones = Arrays.asList(zone);
		this.isInstance = isInstance;
	}

	public int getInstancedRegionID(WorldPoint realPlayerPoint, WorldPoint instancePlayerPoint)
	{
		WorldPoint minPoint = this.getZones().get(0).getMinWorldPoint();

		int xDiff = instancePlayerPoint.getX() - minPoint.getX();
		int yDiff = instancePlayerPoint.getY() - minPoint.getY();
		int realMinPointX = realPlayerPoint.getX() - xDiff;
		int realMinPointY = realPlayerPoint.getY() - yDiff;

		WorldPoint realMinPoint = new WorldPoint(realMinPointX, realMinPointY, 0);

		return realMinPoint.getRegionID();
	}
}

