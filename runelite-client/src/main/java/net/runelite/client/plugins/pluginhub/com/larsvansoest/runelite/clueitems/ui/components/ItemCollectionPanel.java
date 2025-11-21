package net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.ui.components;

import net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.EmoteClueItemsImages;
import net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.ui.EmoteClueItemsPalette;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Displays an item collection log, with item icons and quantity indicators.
 * <p>
 * Items are semi-transparent when quantity is 0.
 */
public class ItemCollectionPanel extends RequirementPanel
{
	private final int slotRowSize;
	private final ArrayList<ItemSlotPanel> itemSlots;
	private final JPanel itemsPanel;
	private final Color itemSlotBackGround;

	/**
	 * Creates the item collection panel.
	 *
	 * @param palette     Colour scheme for the grid.
	 * @param name        Name to display as {@link com.larsvansoest.runelite.clueitems.ui.components.FoldablePanel} header text.
	 * @param slotRowSize The amount of item icons per row.
	 */
	public ItemCollectionPanel(final EmoteClueItemsPalette palette, final String name, final int slotRowSize)
	{
		super(palette, name, 160, 20);
		super.setStatus(Status.InComplete);
		super.addLeft(new JLabel(new ImageIcon(EmoteClueItemsImages.Icons.RuneScape.INVENTORY)), new Insets(2, 4, 2, 0), 0, 0, DisplayMode.All);

		this.itemSlotBackGround = palette.getFoldContentColor();

		this.itemsPanel = new JPanel(new GridBagLayout());
		this.itemsPanel.setBackground(this.itemSlotBackGround);
		super.setFoldContentLeftInset(0);
		super.setFoldContentRightInset(0);
		super.setFixedFoldContentTopInset(1);
		super.addChild(this.itemsPanel, DisplayMode.All);

		this.slotRowSize = slotRowSize;
		this.itemSlots = new ArrayList<>();
	}

	/**
	 * Collapses the collection log.
	 * <p>
	 * Also removes all item panels to enable re-using them in another panel.
	 */
	@Override
	public void fold()
	{
		this.itemSlots.forEach(this.itemsPanel::remove);
		super.fold();
	}

	/**
	 * Un-collapses the collection log.
	 * <p>
	 * Also re-adds all item panels to enable re-using them in another panel. s
	 */
	@Override
	public void unfold()
	{
		final GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		int i = 0;
		while (i < this.itemSlots.size())
		{
			this.itemsPanel.add(this.itemSlots.get(i), c);
			i++;
			final int x = i % this.slotRowSize;
			if (x == 0)
			{
				c.gridy++;
			}
			c.gridx = x;
		}
		super.unfold();
	}

	/**
	 * Adds an item to the item collection log.
	 *
	 * @param itemSlotPanel the panel which displays the item.
	 */
	public void addItem(final ItemSlotPanel itemSlotPanel)
	{
		if (!this.itemSlots.contains(itemSlotPanel))
		{
			itemSlotPanel.setBackground(this.itemSlotBackGround);
			this.itemSlots.add(itemSlotPanel);
		}
	}
}