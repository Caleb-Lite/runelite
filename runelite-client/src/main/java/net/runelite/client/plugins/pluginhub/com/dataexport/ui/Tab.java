package net.runelite.client.plugins.pluginhub.com.dataexport.ui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.api.ItemID;

@RequiredArgsConstructor
@Getter
public enum Tab
{
	ALL_ITEMS("All Items", ItemID.TWISTED_BOW, "container_all_items"),
	BANK("Bank", ItemID.BANK_KEY, "container_bank"),
	SEED_VAULT("Seed Vault", ItemID.SEED_PACK, "container_seed_vault"),
	INVENTORY("Inventory", ItemID.EXPLORER_BACKPACK, "container_inventory"),
	EQUIPMENT("Equipment", ItemID.ROGUES_EQUIPMENT_CRATE, "container_equipment");

	public static final Tab[] CONTAINER_TABS = {ALL_ITEMS, BANK, SEED_VAULT, INVENTORY, EQUIPMENT};

	private final String name;

	private final int itemID;

	private final String filePrefix;

	@Override
	public String toString()
	{
		return name;
	}
}
