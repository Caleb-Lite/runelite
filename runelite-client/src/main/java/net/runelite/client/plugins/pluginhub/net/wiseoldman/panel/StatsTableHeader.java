package net.runelite.client.plugins.pluginhub.net.wiseoldman.panel;

import com.google.common.collect.ImmutableMap;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatsTableHeader extends JPanel
{
    private static final int ICON_WIDTH = 35;

    private static final String[] SKILLING_LABELS = {"Exp", "Level", "Rank", "EHP"};
    private static final String[] BOSSING_LABELS = {"Kills", "Rank", "EHB"};
    private static final String[] ACTIVITIES_LABELS = {"Score", "Rank"};

    private static final ImmutableMap<String, String[]> HEADER_LABELS = ImmutableMap.of(
        "skilling", SKILLING_LABELS,
        "bossing", BOSSING_LABELS,
        "activities", ACTIVITIES_LABELS
    );

    StatsTableHeader(String stats)
    {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(2, 0, 2, 0));
        setBackground(ColorScheme.DARKER_GRAY_COLOR);

        JPanel iconPnl = new JPanel(new BorderLayout());
        iconPnl.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        iconPnl.setPreferredSize(new Dimension(ICON_WIDTH, 0));
        iconPnl.add(new JLabel("", SwingConstants.CENTER));

        JPanel headersPanel = new JPanel(new GridLayout());
        headersPanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);

        for (String label : HEADER_LABELS.get(stats))
        {
            JLabel lbl = new JLabel(label, SwingConstants.CENTER);
            lbl.setFont(FontManager.getRunescapeSmallFont());
            lbl.setForeground(Color.WHITE);
            headersPanel.add(lbl);
        }

        add(iconPnl, BorderLayout.WEST);
        add(headersPanel);
    }
}
