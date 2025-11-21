package net.runelite.client.plugins.pluginhub.rs117.hd.utils;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.function.Function;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.client.plugins.pluginhub.rs117.hd.HdPlugin;

import static rs117.hd.utils.ResourcePath.path;

@Slf4j
public class PopupUtils {
	public static void displayPopupMessage(
		Client client,
		String title,
		String message,
		String[] buttonLabels,
		Function<Integer, Boolean> buttonIndexConsumer
	) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame(title);

			JPanel mainPanel = new JPanel(new BorderLayout());
			mainPanel.setBorder(BorderFactory.createEmptyBorder(16, 0, 8, 8));

			try {
				BufferedImage logoImage = path(HdPlugin.class, "logo.png").loadImage();
				frame.setIconImage(logoImage);
				Image logoScaled = logoImage.getScaledInstance(96, -1, Image.SCALE_SMOOTH);
				JLabel logoLabel = new JLabel(new ImageIcon(logoScaled));
				logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
				mainPanel.add(logoLabel, BorderLayout.LINE_START);
			} catch (IOException ex) {
				log.error("Unable to load HD logo: ", ex);
			}

			String html = String.format("<html><style>a { color: #dc8a00; }</style><body>%s</body></html>", message);
			JEditorPane messagePane = new JEditorPane("text/html", html);
			messagePane.setBorder(BorderFactory.createEmptyBorder());
			messagePane.setHighlighter(null);
			messagePane.setEditable(false);
			messagePane.setOpaque(false);
			messagePane.addHyperlinkListener(e -> {
				if (Desktop.isDesktopSupported() && e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
					try {
						Desktop.getDesktop().browse(e.getURL().toURI());
					} catch (IOException | URISyntaxException ex) {
						log.error("Unable to open link: {}", e.getURL().toString(), ex);
					}
                }
            });

			JScrollPane scrollPane = new JScrollPane(messagePane);
            scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
			scrollPane.setOpaque(false);
			scrollPane.getViewport().setOpaque(false);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			mainPanel.add(scrollPane, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
            for (int i = 0; i < buttonLabels.length; i++) {
                JButton button = new JButton(buttonLabels[i]);
                int index = i;
				button.addActionListener(actionEvent -> {
					if (buttonIndexConsumer.apply(index))
						frame.setVisible(false);
				});
				buttonPanel.add(button);
            }

            JPanel framePanel = new JPanel(new BorderLayout());
            framePanel.add(mainPanel, BorderLayout.CENTER);
            framePanel.add(buttonPanel, BorderLayout.PAGE_END);

            frame.setContentPane(framePanel);
            frame.pack();
            frame.setResizable(false);

            frame.setLocationRelativeTo(client.getCanvas());
            Point point = frame.getLocation();
            frame.setLocation(point.x + 5, point.y + (Constants.GAME_FIXED_HEIGHT - client.getCanvasHeight()) / 2 - 3);
            frame.setAutoRequestFocus(true);

            JFrame runeLiteWindow = (JFrame) SwingUtilities.getWindowAncestor(client.getCanvas());
            if (runeLiteWindow.isAlwaysOnTop())
                frame.setAlwaysOnTop(true);

            frame.setVisible(true);
        });
    }
}

/*
 * Copyright (c) 2022, Hooder <ahooder@protonmail.com>
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