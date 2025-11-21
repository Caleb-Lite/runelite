package net.runelite.client.plugins.pluginhub.com.commitorquit;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import net.runelite.api.ItemID;

// All rarity data have been manually scraped from osrs wiki
// Data set only contain (subjectively) rare drops so that if a drop matches against a pickpocket it is always posted to Discord
@AllArgsConstructor
public enum PickpocketRarity
{
	// @formatter:off
	VYRE_BLOOD_SHARD(ItemID.BLOOD_SHARD, 1f / 5000f),
	ELF_TELEPORT_CRYSTAL(ItemID.ENHANCED_CRYSTAL_TELEPORT_SEED, 1f / 1024f);

	// @formatter:on
	private final int itemId;
	private final float rarity;

	public static final ImmutableMap<Integer, RarityItemData> PICKPOCKET_TABLE_MAPPING = initPickpocketMapping();

	private static ImmutableMap<Integer, RarityItemData> initPickpocketMapping()
	{
		ImmutableMap.Builder<Integer, RarityItemData> builder = new ImmutableMap.Builder<>();
		for (PickpocketRarity r : values())
		{
			RarityItemData data = new RarityItemData();
			data.Unique = true;
			data.Rarity = r.rarity;
			builder.put(r.itemId, data);
		}

		return builder.build();
	}
}
