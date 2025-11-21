package net.runelite.client.plugins.pluginhub.com.consumablecooldowns;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.Item;
import net.runelite.api.ItemID;

@AllArgsConstructor
@Getter(AccessLevel.MODULE)
public class InventoryConsumableItemAction
{
	private final Item[] oldInventory;
	private final int itemId;
	private final int itemSlot;
	private final int actionTick;

	boolean isItemConsumed(Item[] newInventory)
	{
		Item oldItem = oldInventory[itemSlot];
		Item newItem = newInventory[itemSlot];
		boolean multiQuantityConsumable = isMultiQuantityInSameSlotConsumable(oldItem.getId());
		boolean isItemStillInInventorySlot = oldItem.getId() == newItem.getId();
		if (!isItemStillInInventorySlot && !multiQuantityConsumable)
		{
			return true;
		}

		if (!multiQuantityConsumable)
		{
			return false;
		}

		int quantityDifference = oldItem.getQuantity() - newItem.getQuantity();
		return quantityDifference > 0;
	}

	private boolean isMultiQuantityInSameSlotConsumable(int itemId)
	{
		return itemId == ItemID.PURPLE_SWEETS || itemId == ItemID.PURPLE_SWEETS_10476 || itemId == ItemID.HONEY_LOCUST;
	}
}