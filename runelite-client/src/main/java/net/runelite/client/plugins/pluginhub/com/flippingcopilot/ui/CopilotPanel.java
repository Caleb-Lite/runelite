package net.runelite.client.plugins.pluginhub.com.flippingcopilot.ui;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;

@Singleton
public class CopilotPanel extends JPanel {

    public final SuggestionPanel suggestionPanel;
    public final StatsPanelV2 statsPanel;
    public final ControlPanel controlPanel;

    @Inject
    public CopilotPanel(SuggestionPanel suggestionPanel,
                        StatsPanelV2 statsPanel,
                        ControlPanel controlPanel) {
        this.statsPanel = statsPanel;
        this.suggestionPanel = suggestionPanel;
        this.controlPanel = controlPanel;

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(suggestionPanel);
        topPanel.add(Box.createRigidArea(new Dimension(MainPanel.CONTENT_WIDTH, 5)));
        topPanel.add(controlPanel);
        topPanel.add(Box.createRigidArea(new Dimension(MainPanel.CONTENT_WIDTH, 5)));

        add(topPanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
    }

    public void refresh() {
        if(!SwingUtilities.isEventDispatchThread()) {
            // we always execute this in the Swing EDT thread
            SwingUtilities.invokeLater(this::refresh);
            return;
        }
        suggestionPanel.refresh();
        controlPanel.refresh();
    }
}

/* Copyright (c) 2018, Jasper <Jasper0781@gmail.com>
 * Copyright (c) 2020, melky <https://github.com/melkypie>
 * Copyright (c) 2024, Cillian Brewitt
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
