package net.runelite.client.plugins.pluginhub.com.cluedetails;

import javax.inject.Singleton;
import net.runelite.api.ItemID;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.ui.overlay.WidgetItemOverlay;
import javax.inject.Inject;
import java.awt.Graphics2D;

import static com.cluedetails.ClueDetailsConfig.SavedThreeStepperEnum.BOTH;
import static com.cluedetails.ClueDetailsConfig.SavedThreeStepperEnum.INVENTORY;

@Singleton
public class ClueThreeStepSaverWidgetOverlay extends WidgetItemOverlay
{
	private final ClueDetailsPlugin clueDetailsPlugin;
	private final ClueDetailsConfig config;

	private final ClueThreeStepSaver clueThreeStepSaver;

	@Inject
	private ClueThreeStepSaverWidgetOverlay(ClueDetailsPlugin clueDetailsPlugin, ClueThreeStepSaver clueThreeStepSaver, ClueDetailsConfig config)
	{
		this.clueDetailsPlugin = clueDetailsPlugin;
		this.clueThreeStepSaver = clueThreeStepSaver;
		this.config = config;
		showOnInventory();
	}

	@Override
	public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem widgetItem)
	{
		if (itemId != ItemID.CLUE_SCROLL_MASTER || !clueThreeStepSaver.cluesMatch())
		{
			return;
		}

		if (config.threeStepperSaver() && (config.highlightSavedThreeStepper() == BOTH || config.highlightSavedThreeStepper() == INVENTORY))
		{
			clueDetailsPlugin.getItemsOverlay().inventoryTagsOverlay(graphics, itemId, widgetItem, config.invThreeStepperHighlightColor());
		}
	}
}

/*
 * Copyright (c) 2025, cubeee <https://www.github.com/cubeee>
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