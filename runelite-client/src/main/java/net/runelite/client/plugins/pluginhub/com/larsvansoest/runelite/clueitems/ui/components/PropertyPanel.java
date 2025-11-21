package net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.ui.components;

import net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.ui.EmoteClueItemsPalette;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.components.shadowlabel.JShadowedLabel;

import javax.swing.*;
import java.awt.*;

/**
 * Displays a key-value pair in similar fashion to "<b>key:</b> value".
 *
 * @author Lars van Soest
 * @since 3.0.0
 */
public class PropertyPanel extends JPanel implements StatusPanel
{
	private final JLabel nameLabel;
	private final JLabel valueLabel;

	/**
	 * Creates the panel.
	 *
	 * @param palette Colour scheme for the panel.
	 * @param name    Name to display before the value.
	 * @param value   Value to display after the name.
	 */
	public PropertyPanel(final EmoteClueItemsPalette palette, final String name, final String value)
	{
		super(new GridBagLayout());
		super.setBackground(palette.getFoldContentColor());

		this.nameLabel = new JShadowedLabel();
		this.setName(name);
		this.nameLabel.setFont(FontManager.getRunescapeSmallFont());
		this.nameLabel.setForeground(palette.getPropertyNameColor());
		this.nameLabel.setHorizontalAlignment(JLabel.CENTER);

		this.valueLabel = new JLabel();
		this.setValue(value);
		this.valueLabel.setFont(FontManager.getRunescapeSmallFont());
		this.valueLabel.setHorizontalAlignment(JLabel.CENTER);
		this.setStatus(Status.InComplete);

		final GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets.left = 5;
		c.weightx = 0;
		c.fill = GridBagConstraints.BOTH;
		super.add(this.nameLabel, c);

		c.gridx++;
		super.add(this.valueLabel, c);
	}

	/**
	 * Sets the name of the property panel.
	 *
	 * @param name the new name to display before the value.
	 */
	public void setName(final String name)
	{
		this.nameLabel.setText(String.format("%s:", name));
	}

	/**
	 * Sets the value of the property panel.
	 *
	 * @param value the new value to display after the name.
	 */
	public void setValue(final String value)
	{
		this.valueLabel.setText(value.toLowerCase());
	}

	@Override
	public void setStatus(Status status) {
		this.valueLabel.setForeground(status.colour);
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
