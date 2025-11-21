package net.runelite.client.plugins.pluginhub.inventorysetups.ui;

import net.runelite.client.plugins.pluginhub.inventorysetups.InventorySetup;
import net.runelite.client.plugins.pluginhub.inventorysetups.InventorySetupsPlugin;
import net.runelite.client.plugins.pluginhub.inventorysetups.InventorySetupsSection;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import net.runelite.api.EquipmentInventorySlot;
import net.runelite.api.gameval.ItemID;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.util.AsyncBufferedImage;

public class InventorySetupsIconPanel extends InventorySetupsPanel
{
	InventorySetupsIconPanel(InventorySetupsPlugin plugin, InventorySetupsPluginPanel panel, InventorySetup invSetup, InventorySetupsSection section)
	{
		this(plugin, panel, invSetup, section, true);
	}

	InventorySetupsIconPanel(InventorySetupsPlugin plugin, InventorySetupsPluginPanel panel, InventorySetup invSetup, InventorySetupsSection section, boolean allowEditable)
	{
		super(plugin, panel, invSetup, section, allowEditable);

		setLayout(new BorderLayout());
		setBackground(ColorScheme.DARKER_GRAY_COLOR);

		//final int sizeOfImage = 33;
		//setPreferredSize(new Dimension(sizeOfImage + 4, sizeOfImage + 2));
		setPreferredSize(new Dimension(46, 42));

		JLabel imageLabel = new JLabel();
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		imageLabel.setVerticalAlignment(JLabel.CENTER);
		int itemIDForImage = invSetup.getIconID();
		// ID 0 is "Dwarf Remains" meaning setups saved before iconID was added will default to 0
		// and a picture of "Dwarf Remains" will be used. So exclude 0 as well and select a weapon
		// Since most people will probably not want Dwarf Remains as the icon...
		if (itemIDForImage <= 0)
		{
			itemIDForImage = invSetup.getEquipment().get(EquipmentInventorySlot.WEAPON.getSlotIdx()).getId();
			if (itemIDForImage <= 0)
			{
				itemIDForImage = ItemID._100GUIDE_GUIDECAKE;
			}
		}

		add(imageLabel, BorderLayout.CENTER);
		AsyncBufferedImage itemImg = plugin.getItemManager().getImage(itemIDForImage, 1, false);
		Runnable r = () ->
		{
			// Use 33 width for 5 items per row, else just used the AsyncBufferedImage if no scaling with 4
			// Might need to set a preferred width to get the exact size you want
			//Image scaledItemImg = itemImg.getScaledInstance(sizeOfImage, -1, Image.SCALE_SMOOTH);
			imageLabel.setIcon(new ImageIcon(itemImg));
			this.repaint();
		};
		itemImg.onLoaded(r); // transforms if loaded later
		r.run(); // transforms if already loaded

		imageLabel.setBorder(new EmptyBorder(2, 2, 2, 2));

		setToolTipText(invSetup.getName());
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				if (SwingUtilities.isLeftMouseButton(e))
				{
					panel.setCurrentInventorySetup(invSetup, true);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				setBackground(ColorScheme.DARKER_GRAY_HOVER_COLOR);
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				setBackground(ColorScheme.DARKER_GRAY_COLOR);
			}
		});

		JMenuItem updateIcon = new JMenuItem("Update Icon..");
		updateIcon.addActionListener(e -> plugin.updateInventorySetupIcon(invSetup));
		popupMenu.add(updateIcon);
	}
}

/*
 * Copyright (c) 2022, dillydill123 <https://github.com/dillydill123>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */