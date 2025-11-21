package net.runelite.client.plugins.pluginhub.com.groundMarkerProfiles;

import javax.swing.border.TitledBorder;

import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class GroundMarkerProfilesSidePanel extends PluginPanel
{
    private static final Logger log = LoggerFactory.getLogger(GroundMarkerProfilesSidePanel.class);
    private final GroundMarkerProfilesPlugin plugin;
    private final GroundMarkerProfilesConfig config;

    JButton buttonTiles0 = new JButton();
    JButton buttonTiles1 = new JButton();
    JButton buttonTiles2 = new JButton();
    JButton buttonTiles3 = new JButton();
    JButton buttonTiles4 = new JButton();
    JButton buttonTiles5 = new JButton();
    JButton buttonTiles6 = new JButton();
    JButton buttonTiles7 = new JButton();
    JButton buttonTiles8 = new JButton();
    JButton buttonTiles9 = new JButton();

    ArrayList<JButton> buttonTilesList = new ArrayList<>(
            Arrays.asList(
                    buttonTiles0, buttonTiles1, buttonTiles2, buttonTiles3, buttonTiles4,
                    buttonTiles5, buttonTiles6, buttonTiles7, buttonTiles8, buttonTiles9
            )
    );

    ArrayList<String> buttonNames = new ArrayList<>();

    @Inject
    GroundMarkerProfilesSidePanel(GroundMarkerProfilesPlugin plugin, GroundMarkerProfilesConfig config)
    {
        this.config = config;
        this.plugin = plugin;

        Collections.addAll(buttonNames,
                config.nameProfile0(), config.nameProfile1(), config.nameProfile2(), config.nameProfile3(), config.nameProfile4(),
                config.nameProfile5(), config.nameProfile6(), config.nameProfile7(), config.nameProfile8(), config.nameProfile9()
        );
    }

    public void startPanel() {
        getParent().setLayout(new BorderLayout());
        getParent().add(this, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(ColorScheme.DARK_GRAY_COLOR);

        final JPanel layout = new JPanel();
        BoxLayout boxLayout = new BoxLayout(layout, BoxLayout.Y_AXIS);
        layout.setLayout(boxLayout);
        add(layout, BorderLayout.NORTH);

        layout.add(Box.createRigidArea(new Dimension(0, 15)));
        JPanel frame = createFrame();
        layout.add(frame);
    }

    public void updateButtonName(Integer index, String text) {
        buttonTilesList.get(index).setText(text);
    }

    private JPanel createFrame() {
        JPanel swapsFrame = new JPanel();
        swapsFrame.setLayout(new GridLayout(5, 2));
        TitledBorder border = BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Ground Marker Profiles");
        swapsFrame.setBorder(border);

        for (int i = 0; i < buttonTilesList.size(); i++) {
            buttonTilesList.get(i).setText(buttonNames.get(i));
            buttonTilesList.get(i).setPreferredSize(new Dimension(40, 40));
            buttonTilesList.get(i).setFocusable(false);
            buttonTilesList.get(i).setBackground(ColorScheme.DARKER_GRAY_COLOR);
            buttonTilesList.get(i).setForeground(Color.GRAY);

            final int index = i;

            buttonTilesList.get(i).addActionListener(e -> _configureMarker(index));

            swapsFrame.add(buttonTilesList.get(i));
        }

        // default is set to 1000 for this field so that no button is selected initially
        if (config.previousMarkerProfile() < buttonTilesList.size()) {
            _configureMarker(config.previousMarkerProfile());
        }

        return swapsFrame;
    }

    private void _configureMarker(Integer index) {
        _resetProfileSelection();
        plugin.profileSwapper.setActiveProfileIndex(index);
        buttonTilesList.get(index).setForeground(Color.WHITE);
        config.setPreviousMarkerProfile(index);
    }

    private void _resetProfileSelection() {
        for (int i = 0; i < buttonTilesList.size(); i++) {
            buttonTilesList.get(i).setForeground(Color.GRAY);
        }
    }
}
/*
 * Copyright (c) 2018, TheLonelyDev <https://github.com/TheLonelyDev>
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
 * Copyright (c) 2022, Trevor <https://github.com/TrevorMDev>
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