package net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.ui.components;

import net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.EmoteClueItemsImages;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.components.shadowlabel.JShadowedLabel;

import javax.swing.*;
import java.awt.*;

/**
 * Displays a plugin panel header with the title and useful links.
 */
public class HeaderPanel extends JPanel {

    /**
     * Creates a plugin panel header.
     * @param title The title of the plugin panel.
     * @param version The version of the plugin.
     * @param gitHubUrl A URL to the GitHub repository page.
     * @param payPalUrl A donation url.
     */
    public HeaderPanel(final String title, final String version, final String gitHubUrl, final String payPalUrl) {
        super(new GridBagLayout());

        final GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.insets.bottom = 9;

        c.insets.top = 6;
        super.add(getTitle(title), c);
        c.weightx = 0;
        c.insets.left = 11;
        c.gridx++;

        final String patchNotesUrl = String.format("%s/releases/tag/%s", gitHubUrl, version);
        super.add(new LinkButton(version, patchNotesUrl, "View patch notes.", FontManager.getRunescapeFont()), c);
        c.gridx++;

        c.insets.top = 4;
        super.add(new LinkButton(EmoteClueItemsImages.Icons.GITHUB, gitHubUrl, "Visit GitHub webpage."), c);
        c.gridx++;

        super.add(new LinkButton(EmoteClueItemsImages.Icons.PAYPAL, payPalUrl, "Buy me a coffee!", 1.3f), c);
    }

    private JLabel getTitle(final String title) {
        final JLabel label = new JShadowedLabel(title);
        label.setHorizontalTextPosition(SwingConstants.LEFT);
        return label;
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
