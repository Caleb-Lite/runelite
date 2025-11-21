package net.runelite.client.plugins.pluginhub.io.hydrox.coffincounter;

import lombok.Getter;
import net.runelite.api.ItemID;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
public enum Coffin
{
	BRONZE(3, ItemID.BRONZE_COFFIN, ItemID.OPEN_BRONZE_COFFIN),
	STEEL(8, ItemID.STEEL_COFFIN, ItemID.OPEN_STEEL_COFFIN),
	BLACK(14, ItemID.BLACK_COFFIN, ItemID.OPEN_BLACK_COFFIN),
	SILVER(20, ItemID.SILVER_COFFIN, ItemID.OPEN_SILVER_COFFIN),
	GOLD(28, ItemID.GOLD_COFFIN, ItemID.OPEN_GOLD_COFFIN);

	private final int maxRemains;
	private final int[] itemIDs;

	private static final Map<Integer, Coffin> MAP = new HashMap<>();

	Coffin(int maxRemains, int... itemIDs)
	{
		this.maxRemains = maxRemains;
		this.itemIDs = itemIDs;
	}

	static
	{
		for (Coffin c : values())
		{
			for (int id : c.itemIDs)
			{
				MAP.put(id, c);
			}
		}
	}

	static Set<Integer> ALL_COFFINS()
	{
		return MAP.keySet();
	}

	static Coffin getFromItem(int itemID)
	{
		return MAP.get(itemID);
	}
}

