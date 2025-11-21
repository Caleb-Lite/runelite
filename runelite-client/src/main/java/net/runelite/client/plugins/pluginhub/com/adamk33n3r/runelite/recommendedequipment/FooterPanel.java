package net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.recommendedequipment;

import net.runelite.client.ui.ColorScheme;
import net.runelite.client.util.LinkBrowser;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

public class FooterPanel extends JPanel {
    public FooterPanel() {
        super();
        this.setLayout(new StretchedStackedLayout(2));

        JPanel secondaryPanel = new JPanel(new StretchedStackedLayout(8));
        secondaryPanel.setBorder(new HorizontalRuleBorder(8, HorizontalRuleBorder.BOTTOM));

        JButton howToUseButton = new JButton("How to use this plugin");
        Theme.applyStyle(howToUseButton, Theme.ButtonType.SECONDARY);
        howToUseButton.addActionListener(e -> LinkBrowser.browse("https://github.com/adamk33n3r/runelite-recommended-equipment/blob/master/README.md"));
        secondaryPanel.add(howToUseButton);
        JButton donateButton = new JButton("Donate");
        Theme.applyStyle(donateButton, Theme.ButtonType.SECONDARY);
        donateButton.addActionListener(e -> LinkBrowser.browse("https://donate.stripe.com/9AQcNxadm1pL7Hq9AA"));
        secondaryPanel.add(donateButton);
        this.add(secondaryPanel);

        JButton submitRequestButton = new JButton("Submit a request");
        Theme.applyStyle(submitRequestButton, Theme.ButtonType.HIGHLIGHT);
        submitRequestButton.setBackground(ColorScheme.DARKER_GRAY_HOVER_COLOR);
        submitRequestButton.addActionListener(e -> LinkBrowser.browse("https://github.com/adamk33n3r/runelite-recommended-equipment/issues/new"));
        this.add(submitRequestButton);
    }
}

/**
 *  A panel that implements the Scrollable interface. This class allows you
 *  to customize the scrollable features by using newly provided setter methods
 *  so you don't have to extend this class every time.
 *
 *  Scrollable amounts can be specifed as a percentage of the viewport size or
 *  as an actual pixel value. The amount can be changed for both unit and block
 *  scrolling for both horizontal and vertical scrollbars.
 *
 *  The Scrollable interface only provides a boolean value for determining whether
 *  or not the viewport size (width or height) should be used by the scrollpane
 *  when determining if scrollbars should be made visible. This class supports the
 *  concept of dynamically changing this value based on the size of the viewport.
 *  In this case the viewport size will only be used when it is larger than the
 *  panels size. This has the effect of ensuring the viewport is always full as
 *  components added to the panel will be size to fill the area available,
 *  based on the rules of the applicable layout manager of course.
 */