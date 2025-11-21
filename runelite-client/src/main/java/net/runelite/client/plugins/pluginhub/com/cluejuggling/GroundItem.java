package net.runelite.client.plugins.pluginhub.com.cluejuggling;

import java.awt.Color;
import java.time.Duration;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import static net.runelite.api.TileItem.OWNERSHIP_GROUP;
import static net.runelite.api.TileItem.OWNERSHIP_NONE;
import static net.runelite.api.TileItem.OWNERSHIP_OTHER;
import static net.runelite.api.TileItem.OWNERSHIP_SELF;
import net.runelite.api.coords.WorldPoint;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.Instant;

@Data
@Builder
public
class GroundItem
{
	private int id;
	private int itemId;
	private String name;
	private int quantity;
	private WorldPoint location;
	private int height;
	private int haPrice;
	private int gePrice;
	private int offset;
	private boolean tradeable;
	private int ownership;
	private boolean isPrivate;
	@Nullable
	private Instant spawnTime;
	private boolean stackable;
	private Duration despawnTime;
	private Duration visibleTime;

	int getHaPrice()
	{
		return haPrice * quantity;
	}

	int getGePrice()
	{
		return gePrice * quantity;
	}

	boolean isMine()
	{
		return ownership != OWNERSHIP_OTHER;
	}

	@Value
	public static class GroundItemKey
	{
		private int itemId;
		private WorldPoint location;
	}
}

