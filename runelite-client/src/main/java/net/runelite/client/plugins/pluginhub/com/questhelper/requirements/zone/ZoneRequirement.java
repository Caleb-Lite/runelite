package net.runelite.client.plugins.pluginhub.com.questhelper.requirements.zone;

import net.runelite.client.plugins.pluginhub.com.questhelper.questhelpers.QuestUtil;
import net.runelite.client.plugins.pluginhub.com.questhelper.requirements.AbstractRequirement;
import net.runelite.client.plugins.pluginhub.com.questhelper.util.Utils;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.coords.WorldPoint;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Stream;

public class ZoneRequirement extends AbstractRequirement
{
	@Getter
	private final List<Zone> zones;
	private final boolean checkInZone;
	private String displayText;

	/**
	 * Check if the player is either in the specified zone.
	 *
	 * @param displayText display text
	 * @param zone the zone to check
	 */
	public ZoneRequirement(String displayText, Zone zone)
	{
		this(displayText, false, zone);
	}

	/**
	 * Check if the player is either in, or not in, the specified zone.
	 *
	 * @param displayText display text
	 * @param checkNotInZone true to negate this requirement check (i.e. it will check if the player is NOT in the zone)
	 * @param zone the zone to check
	 */
	public ZoneRequirement(String displayText, boolean checkNotInZone, Zone zone)
	{
		assert(zone != null);
		this.displayText = displayText;
		this.checkInZone = !checkNotInZone; // This was originally 'checkNotInZone' so we have to maintain that behavior
		this.zones = QuestUtil.toArrayList(zone);
	}

	public ZoneRequirement(WorldPoint... worldPoints)
	{
		assert(Utils.varargsNotNull(worldPoints));
		this.zones = Stream.of(worldPoints).map(Zone::new).collect(QuestUtil.collectToArrayList());
		this.checkInZone = true;
	}

	public ZoneRequirement(Zone... zone)
	{
		assert(Utils.varargsNotNull(zone));
		this.zones = QuestUtil.toArrayList(zone);
		this.checkInZone = true;
	}

	public ZoneRequirement(boolean checkInZone, Zone... zone)
	{
		assert(Utils.varargsNotNull(zone));
		this.zones = QuestUtil.toArrayList(zone);
		this.checkInZone = checkInZone;
	}

	public ZoneRequirement(boolean checkInZone, WorldPoint... worldPoints)
	{
		assert(Utils.varargsNotNull(worldPoints));
		this.zones = Stream.of(worldPoints).map(Zone::new).collect(QuestUtil.collectToArrayList());
		this.checkInZone = checkInZone;
	}

	@Override
	public boolean check(Client client)
	{
		Player player = client.getLocalPlayer();
		if (player != null && zones != null)
		{
			WorldPoint location = WorldPoint.fromLocalInstance(client, player.getLocalLocation());
			boolean inZone = zones.stream().anyMatch(z -> z.contains(location));
			return inZone == checkInZone;
		}
		return false;
	}

	@Nonnull
	@Override
	public String getDisplayText()
	{
		return displayText == null ? "" : displayText;
	}
}
