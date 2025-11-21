package net.runelite.client.plugins.pluginhub.com.suppliestracker;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.Item;

/**
 * Data class that tracks all info related to a menu click action
 */
@AllArgsConstructor
public class ItemMenuAction
{
	@Getter(AccessLevel.MODULE)
	private ActionType type;
	@Getter(AccessLevel.MODULE)
	private Item[] oldInventory;

	static class ItemAction extends ItemMenuAction
	{

		@Getter(AccessLevel.MODULE)
		private final int itemID;
		@Getter(AccessLevel.MODULE)
		private final int slot;

		ItemAction(final ActionType type, final Item[] oldInventory, final int itemID, final int slot)
		{
			super(type, oldInventory);
			this.itemID = itemID;
			this.slot = slot;
		}
	}
}
