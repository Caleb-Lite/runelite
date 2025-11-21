package net.runelite.client.plugins.pluginhub.inventorysetups.ui;

import net.runelite.client.plugins.pluginhub.inventorysetups.InventorySetupsPlugin;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


// Displays a warning that layouts will not work.
public class InventorySetupsLayoutWarningPanel extends JPanel
{

	InventorySetupsLayoutWarningPanel(InventorySetupsPlugin plugin, InventorySetupsPluginPanel panel)
	{
		final String warningText = "Inventory Setups has detected that layouts will not work with your current settings.\n\n" +
				"The Bank Tags Plugin must be ON to use bank filtering and layouts.\n\n" +
				"Inventory Setups can turn it on for you. This is recommended so you can use all features of Inventory Setups.\n\n" +
				"Hub Plugin \"Bank Tag Layouts\" is not required with Inventory Setups. If you only used it with Inventory Setups, you can safely uninstall it.\n\n" +
				"You can disable this warning in the settings.";

		JTextArea textArea = new JTextArea(2, 20);
		textArea.setText(warningText);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setFocusable(false);
		textArea.setBackground(ColorScheme.DARK_GRAY_COLOR);
		Font textAreaFont = FontManager.getRunescapeFont();
		textAreaFont = textAreaFont.deriveFont(textAreaFont.getStyle(), (float)textAreaFont.getSize() - (float)0.1);
		textArea.setFont(textAreaFont);

		textArea.setBorder(new EmptyBorder(0, 0, 0, 0));

		final JLabel configureSettings = new JLabel("Do you want Inventory Setups to ");
		final JLabel configureSettings2 = new JLabel("change your settings to use layouts?");
		final JButton configureSettingsButton = new JButton("Turn on Bank Tags.");
		configureSettingsButton.setForeground(Color.GREEN);
		configureSettingsButton.addActionListener(e ->
		{
			plugin.enableLayouts();
			panel.setHasDisplayedLayoutWarning(true);
			panel.showCorrectPanel();
		});
		configureSettings.setFont(FontManager.getRunescapeSmallFont());
		configureSettings2.setFont(FontManager.getRunescapeSmallFont());
		configureSettings.setHorizontalAlignment(JLabel.CENTER);
		configureSettings2.setHorizontalAlignment(JLabel.CENTER);

		final JPanel configureSettingsPanelInfo = new JPanel();
		configureSettingsPanelInfo.setLayout(new BorderLayout());
		configureSettingsPanelInfo.add(configureSettings, BorderLayout.NORTH);
		configureSettingsPanelInfo.add(configureSettings2, BorderLayout.CENTER);
		configureSettingsPanelInfo.add(configureSettingsButton, BorderLayout.SOUTH);

		final JLabel noConfigureSettings = new JLabel("Use Inventory Setups without layouts.");
		final JPanel noConfigureSettingsPanel = new JPanel(new BorderLayout());
		final JButton noConfigureSettingsButton = new JButton("Do not use layouts.");
		noConfigureSettingsButton.setForeground(Color.RED);
		noConfigureSettingsButton.addActionListener(e ->
		{
			panel.setHasDisplayedLayoutWarning(true);
			panel.showCorrectPanel();
		});
		noConfigureSettings.setFont(FontManager.getRunescapeSmallFont());
		noConfigureSettings.setHorizontalAlignment(JLabel.CENTER);
		noConfigureSettingsPanel.add(noConfigureSettings, BorderLayout.NORTH);
		noConfigureSettingsPanel.add(noConfigureSettingsButton, BorderLayout.SOUTH);


		final JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.add(textArea);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 24)));
		contentPanel.add(configureSettingsPanelInfo);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 16)));
		contentPanel.add(noConfigureSettingsPanel);

		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(5, 10, 5, 10));
		add(contentPanel, BorderLayout.NORTH);
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