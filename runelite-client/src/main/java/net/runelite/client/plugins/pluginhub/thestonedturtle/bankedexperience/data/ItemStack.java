package net.runelite.client.plugins.pluginhub.thestonedturtle.bankedexperience.data;

import javax.annotation.Nullable;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.runelite.api.ItemComposition;
import net.runelite.client.game.ItemManager;

@Data
@RequiredArgsConstructor
public class ItemStack
{
	private final int id;
	private final double qty;
	@Nullable
	private ItemInfo info;

	public void updateItemInfo(final ItemManager itemManager)
	{
		final ItemComposition composition = itemManager.getItemComposition(id);
		this.info = new ItemInfo(composition.getName(), composition.isStackable());
	}
}
