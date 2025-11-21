package net.runelite.client.plugins.pluginhub.inventorysetups.ui;

import net.runelite.client.plugins.pluginhub.inventorysetups.InventorySetup;
import net.runelite.client.plugins.pluginhub.inventorysetups.InventorySetupsItem;
import net.runelite.client.plugins.pluginhub.inventorysetups.InventorySetupsPlugin;
import net.runelite.client.plugins.pluginhub.inventorysetups.InventorySetupsSlotID;
import lombok.Getter;
import net.runelite.api.gameval.ItemID;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.ColorScheme;

import javax.swing.JPopupMenu;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class InventorySetupsQuiverPanel
{
	// Shows up when a quiver is equipped or in inventory
	@Getter
	private InventorySetupsSlot quiverSlot;

	@Getter
	private final int QUIVER_SLOT_IDX = 0;

	private JPopupMenu quiverSlotRightClickMenu;
	private final JPopupMenu emptyJPopMenu = new JPopupMenu();
	private final InventorySetupsPlugin plugin;
	private final ItemManager itemManager;

	public static final List<Integer> DIZANA_QUIVER_IDS = Arrays.asList(ItemID.DIZANAS_QUIVER_CHARGED,
			ItemID.DIZANAS_QUIVER_CHARGED_TROUVER,
			ItemID.DIZANAS_QUIVER_UNCHARGED,
			ItemID.DIZANAS_QUIVER_UNCHARGED_TROUVER,
			ItemID.SKILLCAPE_MAX_DIZANAS,
			ItemID.SKILLCAPE_MAX_DIZANAS_TROUVER,
			ItemID.DIZANAS_QUIVER_INFINITE,
			ItemID.DIZANAS_QUIVER_INFINITE_TROUVER);

	public static final Set<Integer> DIZANA_QUIVER_IDS_SET = new HashSet<>(DIZANA_QUIVER_IDS);


	InventorySetupsQuiverPanel(final ItemManager itemManager, final InventorySetupsPlugin plugin)
	{
		this.plugin = plugin;
		this.itemManager = itemManager;
		quiverSlot = new InventorySetupsSlot(ColorScheme.DARKER_GRAY_COLOR, InventorySetupsSlotID.QUIVER, QUIVER_SLOT_IDX);
		InventorySetupsSlot.addFuzzyMouseListenerToSlot(plugin, quiverSlot);
		InventorySetupsSlot.addStackMouseListenerToSlot(plugin, quiverSlot);
		InventorySetupsSlot.addUpdateFromContainerMouseListenerToSlot(plugin, quiverSlot);
		InventorySetupsSlot.addUpdateFromSearchMouseListenerToSlot(plugin, quiverSlot, true);
		InventorySetupsSlot.addRemoveMouseListenerToSlot(plugin, quiverSlot);
		this.quiverSlotRightClickMenu = quiverSlot.getRightClickMenu();
		quiverSlot.setComponentPopupMenu(new JPopupMenu());
	}

	public void handleQuiverHighlighting(final InventorySetup setup, boolean doesCurrentInventoryHaveQuiver)
	{
		this.quiverSlot.setParentSetup(setup);
		// This must be run on the client thread!
		if (setup.getQuiver() != null)
		{
			InventorySetupsSlot.setSlotImageAndText(itemManager, quiverSlot, setup, setup.getQuiver().get(0));
			quiverSlot.setComponentPopupMenu(quiverSlotRightClickMenu);

			if (!setup.isHighlightDifference() || !plugin.isHighlightingAllowed())
			{
				quiverSlot.setBackground(ColorScheme.DARKER_GRAY_COLOR);
			}
			else if (doesCurrentInventoryHaveQuiver)
			{
				List<InventorySetupsItem> currentQuiverDataInInvEqp = plugin.getAmmoHandler().getQuiverData();
				final int indexInSlot = quiverSlot.getIndexInSlot();
				InventorySetupsSlot.highlightSlot(setup, setup.getQuiver().get(indexInSlot), currentQuiverDataInInvEqp.get(indexInSlot), quiverSlot);
			}
			else
			{
				quiverSlot.setBackground(setup.getHighlightColor());
			}
		}
		else
		{
			InventorySetupsSlot.setSlotImageAndText(itemManager, quiverSlot, setup, InventorySetupsItem.getDummyItem());
			quiverSlot.setBackground(ColorScheme.DARK_GRAY_COLOR);
			quiverSlot.setComponentPopupMenu(emptyJPopMenu);
		}
	}


}

/*
 * Copyright (c) 2019, dillydill123 <https://github.com/dillydill123>
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