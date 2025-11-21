package net.runelite.client.plugins.pluginhub.thestonedturtle.bankedexperience.components;

import net.runelite.client.plugins.pluginhub.thestonedturtle.bankedexperience.data.BankedItem;
import java.util.EventListener;

public interface SelectionListener extends EventListener
{
	boolean selected(final BankedItem item);
	boolean ignored(final BankedItem item);
}
