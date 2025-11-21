package net.runelite.client.plugins.pluginhub.com.cluehunterfinder;

import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemContainer;

public class CommandHandler
{

	private final Client client;

	@Inject
	CommandHandler(final Client client)
	{
		this.client = client;
	}

	ClueHunterItem getItemForCommand(final String itemDescriptor)
	{
		final ClueHunterItem item = ClueHunterItem.getClueHunterItemMap().get(itemDescriptor);
		if (item == null || hasClueHunterItems(item.getId()))
		{
			return null;
		}
		return item;
	}

	private boolean hasClueHunterItems(final int id)
	{
		final ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);
		final ItemContainer bank = client.getItemContainer(InventoryID.BANK);
		final ItemContainer equipment = client.getItemContainer(InventoryID.EQUIPMENT);

		return inventory != null && inventory.contains(id) ||
			// TODO: Does not work, think about necessity of this boolean
			bank != null && bank.contains(id) ||
			equipment != null && equipment.contains(id);
	}

}
