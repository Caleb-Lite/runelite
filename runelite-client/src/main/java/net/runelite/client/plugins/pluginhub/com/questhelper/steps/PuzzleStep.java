package net.runelite.client.plugins.pluginhub.com.questhelper.steps;

import net.runelite.client.plugins.pluginhub.com.questhelper.QuestHelperPlugin;
import net.runelite.client.plugins.pluginhub.com.questhelper.questhelpers.QuestHelper;
import net.runelite.client.plugins.pluginhub.com.questhelper.requirements.Requirement;
import net.runelite.client.plugins.pluginhub.com.questhelper.steps.widget.WidgetDetails;
import net.runelite.api.events.GameTick;
import net.runelite.api.widgets.Widget;
import net.runelite.client.eventbus.Subscribe;

import java.awt.*;
import java.util.HashSet;
public class PuzzleStep extends DetailedQuestStep
{

	ButtonHighlighCalculator highlightCalculator;
	private HashSet<WidgetDetails> highlightedButtons = new HashSet<>();

	public PuzzleStep(QuestHelper questHelper, ButtonHighlighCalculator highlightCalculator, Requirement... requirements)
	{
		this(questHelper, "Click the highlighted buttons to complete the puzzle", highlightCalculator, requirements);
	}

	public PuzzleStep(QuestHelper questHelper, String text, ButtonHighlighCalculator highlightCalculator, Requirement... requirements)
	{
		super(questHelper, text, requirements);
		this.highlightCalculator = highlightCalculator;
	}

	@Override
	public void startUp()
	{
		this.highlightedButtons = highlightCalculator.getHighlightedButtons();
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		super.onGameTick(event);
		this.highlightedButtons = highlightCalculator.getHighlightedButtons();
	}

	@Override
	public void makeWidgetOverlayHint(Graphics2D graphics, QuestHelperPlugin plugin)
	{
		super.makeWidgetOverlayHint(graphics, plugin);
		for (WidgetDetails button : highlightedButtons)
		{
			if (button == null)
			{
				continue;
			}

			Widget widget = client.getWidget(button.getGroupID(), button.getChildID());
			if (widget != null)
			{
				graphics.setColor(new Color(questHelper.getConfig().targetOverlayColor().getRed(),
					questHelper.getConfig().targetOverlayColor().getGreen(),
					questHelper.getConfig().targetOverlayColor().getBlue(), 65));
				graphics.fill(widget.getBounds());
				graphics.setColor(questHelper.getConfig().targetOverlayColor());
				graphics.draw(widget.getBounds());
			}

		}
	}

	public interface ButtonHighlighCalculator
	{
		HashSet<WidgetDetails> getHighlightedButtons();
	}
}

/*
 * Copyright (c) 2024, Zoinkwiz
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