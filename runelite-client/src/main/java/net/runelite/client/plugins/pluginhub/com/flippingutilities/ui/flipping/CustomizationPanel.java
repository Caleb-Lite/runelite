package net.runelite.client.plugins.pluginhub.com.flippingutilities.ui.flipping;

import net.runelite.client.plugins.pluginhub.com.flippingutilities.controller.FlippingPlugin;
import net.runelite.client.plugins.pluginhub.com.flippingutilities.model.Section;
import net.runelite.client.plugins.pluginhub.com.flippingutilities.ui.uiutilities.Icons;
import net.runelite.client.plugins.pluginhub.com.flippingutilities.ui.uiutilities.UIUtilities;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

@Slf4j
public class CustomizationPanel extends JPanel {
    FlippingPlugin plugin;
    List<Section> sections;

    public CustomizationPanel(FlippingPlugin plugin) {
        this.plugin = plugin;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK.darker());
        setBorder(new EmptyBorder(10,10,10,10));
    }

    private JPanel createSectionPanel(Section section) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setBackground(Color.BLACK.darker());
        sectionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Section name: " + section.getName());
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(FontManager.getRunescapeBoldFont());

        JPanel makeSectionDefaultExpandedPanel = new JPanel(new FlowLayout());
        makeSectionDefaultExpandedPanel.setBackground(Color.BLACK.darker());
        JLabel shouldMakeSectionExpandedLabel = new JLabel("expand section by default");
        shouldMakeSectionExpandedLabel.setFont(new Font(FontManager.getRunescapeSmallFont().getName(), Font.ITALIC, FontManager.getRunescapeSmallFont().getSize()));

        JToggleButton toggleButton = UIUtilities.createToggleButton();
        toggleButton.setSelected(section.isDefaultExpanded());
        toggleButton.addItemListener(i ->
        {
            section.setDefaultExpanded(toggleButton.isSelected());
            onSectionChange();
        });
        makeSectionDefaultExpandedPanel.add(shouldMakeSectionExpandedLabel);
        makeSectionDefaultExpandedPanel.add(toggleButton);

        sectionPanel.add(titleLabel);
        sectionPanel.add(makeSectionDefaultExpandedPanel);
        sectionPanel.add(Box.createVerticalStrut(10));

        for (String labelName : Section.possibleLabels) {
            sectionPanel.add(createPanelForSectionLabel(section, labelName));
            sectionPanel.add(Box.createVerticalStrut(3));
        }
        return sectionPanel;
    }

    private boolean labelUsedInAnotherSection(Section section, String labelName) {
        for (Section otherSection: sections) {
            if (section != otherSection) {
                if (otherSection.isShowingLabel(labelName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private JPanel createPanelForSectionLabel(Section section, String labelName) {
        boolean labelUsedInOtherSection = labelUsedInAnotherSection(section, labelName);
        JPanel sectionLabelPanel = new JPanel(new BorderLayout());
        sectionLabelPanel.setBackground(Color.BLACK.darker());
        JLabel sectionLabel = new JLabel(labelUsedInOtherSection? labelName + " (disabled: used in other section)" :labelName);
        sectionLabel.setFont(FontManager.getRunescapeSmallFont());
        sectionLabel.setForeground(labelUsedInOtherSection? ColorScheme.MEDIUM_GRAY_COLOR : Color.WHITE);
        JToggleButton sectionLabelToggle = UIUtilities.createToggleButton();
        sectionLabelToggle.setSelected(section.isShowingLabel(labelName));
        sectionLabelToggle.setEnabled(!labelUsedInOtherSection);
        sectionLabelToggle.setDisabledIcon(Icons.TOGGLE_OFF);

        sectionLabelToggle.addItemListener(i ->
        {
            section.showLabel(labelName, sectionLabelToggle.isSelected());
            onSectionChange();
        });

        sectionLabelPanel.add(sectionLabel, BorderLayout.WEST);
        sectionLabelPanel.add(sectionLabelToggle, BorderLayout.EAST);
        return sectionLabelPanel;
    }

    private void onSectionChange() {
        rebuild(sections);
        plugin.getFlippingPanel().rebuild(plugin.viewItemsForCurrentView());
        plugin.getDataHandler().markDataAsHavingChanged(FlippingPlugin.ACCOUNT_WIDE);
    }

    public void rebuild(List<Section> sections) {
        this.sections = sections;
        removeAll();
        sections.forEach(section -> add(createSectionPanel(section)));
        revalidate();
        repaint();

    }
}

/*
 * Copyright (c) 2020, Belieal <https://github.com/Belieal>
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
