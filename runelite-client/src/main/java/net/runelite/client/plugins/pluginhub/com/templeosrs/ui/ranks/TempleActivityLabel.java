package net.runelite.client.plugins.pluginhub.com.templeosrs.ui.ranks;

import static com.templeosrs.ui.TempleOSRSPanel.DEFAULT;
import javax.swing.JLabel;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;
import net.runelite.client.util.QuantityFormatter;

public class TempleActivityLabel extends JLabel
{
	TempleActivityLabel()
	{
		setText(DEFAULT);
		setFont(FontManager.getRunescapeSmallFont());
		setForeground(ColorScheme.LIGHT_GRAY_COLOR);
	}

	/* update activity-row label (xp, level, rank) */
	void update(long value)
	{
		setText(QuantityFormatter.quantityToStackSize(value));
		if (value == 0)
		{
			setForeground(ColorScheme.LIGHT_GRAY_COLOR);
			setText(DEFAULT);
		}
		else if (value > 0)
		{
			setForeground(ColorScheme.GRAND_EXCHANGE_PRICE);
		}
		else
		{
			setForeground(ColorScheme.PROGRESS_ERROR_COLOR);
		}
	}

	/* update (double) activity-row label (ehp, ehb) */
	void update(double value)
	{
		setText(String.format("%.2f", value));
		if (value == 0)
		{
			setForeground(ColorScheme.LIGHT_GRAY_COLOR);
			setText(DEFAULT);
		}
		else if (value > 0)
		{
			setForeground(ColorScheme.GRAND_EXCHANGE_PRICE);
		}
		else
		{
			setForeground(ColorScheme.PROGRESS_ERROR_COLOR);
		}
	}

	/* reset activity-row label to default */
	void reset()
	{
		setText(DEFAULT);
		setForeground(ColorScheme.LIGHT_GRAY_COLOR);
	}
}

/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
 * Copyright (c) 2020, dekvall
 * Copyright (c) 2021, Rorro
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
