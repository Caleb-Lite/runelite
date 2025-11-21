package net.runelite.client.plugins.pluginhub.inventorysetups;

import java.awt.Color;

public interface InventorySetupsValidName
{
	boolean isNameValid(final String name, final Color displayColor);

	void updateName(final String newName);
}
