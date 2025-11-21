package net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.ui.components;

import net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.ui.EmoteClueItemsPalette;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.components.shadowlabel.JShadowedLabel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Displays a title and description. The title has a shadowed markup and is supported by a separator. Underneath this separator, the description is html-formatted and displayed using html automatic line breaks.
 *
 * @author Lars van Soest
 * @since 3.0.0
 */
public class DescriptionPanel extends JPanel
{
	/**
	 * Creates the panel.
	 *
	 * @param palette     Colour scheme for the panel.
	 * @param title       Title to display above the separator.
	 * @param description Description to display underneath the separator.
	 */
	public DescriptionPanel(final EmoteClueItemsPalette palette, final String title, final String description)
	{
		super(new GridBagLayout());
		super.setBackground(palette.getFoldContentColor());

		final JLabel header = new JShadowedLabel(title);
		header.setFont(FontManager.getRunescapeSmallFont());
		header.setHorizontalAlignment(JLabel.LEFT);
		header.setForeground(palette.getPropertyNameColor());

		final JLabel content = new JLabel(String.format("<html><p style=\"width:100%%\">%s</p></html>", description));
		content.setFont(FontManager.getRunescapeSmallFont());
		content.setHorizontalAlignment(JLabel.LEFT);
		content.setForeground(palette.getPropertyValueColor());

		final GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		header.setBorder(new MatteBorder(0, 0, 1, 0, palette.getPropertyValueColor()));
		super.add(header, c);

		c.insets.top = 3;
		c.insets.bottom = 3;
		c.gridy++;
		super.add(content, c);
	}
}

/*
 * BSD 2-Clause License
 *
 * Copyright (c) 2020, Lars van Soest
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
