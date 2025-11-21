package net.runelite.client.plugins.pluginhub.inventorysetups;

import net.runelite.api.gameval.InventoryID;

public enum InventorySetupsSlotID
{

	INVENTORY(0),

	EQUIPMENT(1),

	RUNE_POUCH(2),

	BOLT_POUCH(3),

	SPELL_BOOK(4),

	ADDITIONAL_ITEMS(5),

	QUIVER(6);

	private final int id;

	InventorySetupsSlotID(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public static InventorySetupsSlotID fromInventoryID(final int inventoryId)
	{
		if (inventoryId == 0)
		{
			return null;
		}

		switch (inventoryId)
		{
			case InventoryID.INV:
				return INVENTORY;
			case InventoryID.WORN:
				return EQUIPMENT;
		}

		return null;
	}

}
