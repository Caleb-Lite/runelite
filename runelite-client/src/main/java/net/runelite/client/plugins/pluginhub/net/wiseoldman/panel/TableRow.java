package net.runelite.client.plugins.pluginhub.net.wiseoldman.panel;

import net.runelite.client.plugins.pluginhub.net.wiseoldman.util.Format;
import net.runelite.client.plugins.pluginhub.net.wiseoldman.util.Utils;
import net.runelite.client.plugins.pluginhub.net.wiseoldman.WomUtilsPlugin;
import net.runelite.client.plugins.pluginhub.net.wiseoldman.beans.Boss;
import net.runelite.client.plugins.pluginhub.net.wiseoldman.beans.Activity;
import net.runelite.client.plugins.pluginhub.net.wiseoldman.beans.Skill;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Experience;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;
import net.runelite.client.util.ImageUtil;
import net.runelite.client.util.QuantityFormatter;
import net.runelite.client.hiscore.HiscoreSkill;
import net.runelite.client.hiscore.HiscoreSkillType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TableRow extends JPanel
{
    private static final int ICON_WIDTH = 35;

    Map<String, JLabel> labels = new HashMap<>();

    TableRow(String name, String formattedName, HiscoreSkillType type, String... labels)
    {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(2, 0, 2, 0));
        setBackground(ColorScheme.DARKER_GRAY_COLOR);

        JPanel dataPanel = new JPanel(new GridLayout());
        dataPanel.setOpaque(false);

        final String directory;

        if (type == HiscoreSkillType.BOSS)
        {
            directory = "bosses/";
        }
        else if (type == HiscoreSkillType.ACTIVITY)
        {
            directory = "activities/";
        }
        else
        {
            directory = "/skill_icons_small/";
        }

        for (String l : labels)
        {
            dataPanel.add(createCell(l));
        }

        String iconDirectory = directory + name.toLowerCase() + ".png";
        log.debug("Loading icon for {}", iconDirectory);

        JPanel iconPanel = new JPanel(new BorderLayout());
        iconPanel.setOpaque(false);
        JLabel iconLabel = new JLabel("", SwingConstants.CENTER);
        iconPanel.add(iconLabel);

        ImageIcon icon = new ImageIcon(ImageUtil.loadImageResource(WomUtilsPlugin.class, iconDirectory));
        iconPanel.setPreferredSize(new Dimension(ICON_WIDTH, icon.getIconHeight()));

        iconLabel.setIcon(icon);
        iconLabel.setToolTipText(formattedName);

        add(iconPanel, BorderLayout.WEST);
        add(dataPanel);
    }

    private JLabel createCell(String l)
    {
        JLabel label = new JLabel("--", SwingConstants.CENTER);
        label.setFont(FontManager.getRunescapeSmallFont());

        labels.put(l, label);

        return label;
    }

    void update(Skill skill, boolean virtualLevels)
    {
        long experience = skill.getExperience();
        int level = skill.getLevel();
        int rank = skill.getRank();
        boolean ranked = rank != -1;
        double ehp = skill.getEhp();

        JLabel experienceLabel = labels.get("experience");
        experienceLabel.setText(experience > 0 ? Format.formatNumber(experience) : "--");
        experienceLabel.setToolTipText(experience > 0 ? QuantityFormatter.formatNumber(experience) : "");

        JLabel levelLabel = labels.get("level");
        int levelToDisplay = !virtualLevels && level > Experience.MAX_REAL_LEVEL ? Experience.MAX_REAL_LEVEL : level;
        levelLabel.setText(String.valueOf(levelToDisplay));
        levelLabel.setToolTipText(String.valueOf(levelToDisplay));

        JLabel rankLabel = labels.get("rank");
        rankLabel.setText(ranked ? Format.formatNumber(rank) : "--");
        rankLabel.setToolTipText(ranked ? QuantityFormatter.formatNumber(rank) : "Unranked");

        JLabel ehpLabel = labels.get("ehp");
        ehpLabel.setText(Format.formatNumber(ehp));
        ehpLabel.setToolTipText(QuantityFormatter.formatNumber(ehp));
    }

    void update(Boss boss, HiscoreSkill b)
    {
        int kills = boss.getKills();
        int minimumKc = Utils.getMinimumKc(b);
        boolean ranked = kills >= minimumKc;

        int rank = boss.getRank();
        double ehb = boss.getEhb();

        JLabel killsLabel = labels.get("kills");
        killsLabel.setText(ranked ? Format.formatNumber(kills) : "< " + minimumKc);
        killsLabel.setToolTipText(ranked ? QuantityFormatter.formatNumber(kills) : "The Hiscores only start tracking " + b.getName() + " after " + minimumKc + " kc");

        JLabel rankLabel = labels.get("rank");
        rankLabel.setText(ranked ? Format.formatNumber(rank) : "--");
        rankLabel.setToolTipText(ranked ? QuantityFormatter.formatNumber(rank) : "Unranked");

        JLabel ehbLabel = labels.get("ehb");
        ehbLabel.setText(Format.formatNumber(ehb));
        ehbLabel.setToolTipText(QuantityFormatter.formatNumber(ehb));
    }

    void update(Activity minigame)
    {
        int score = minigame.getScore();
        int rank = minigame.getRank();
        boolean ranked = rank != -1;

        JLabel killsLabel = labels.get("score");
        killsLabel.setText(ranked ? Format.formatNumber(score) : "--");
        killsLabel.setToolTipText(ranked ? QuantityFormatter.formatNumber(score) : "");

        JLabel rankLabel = labels.get("rank");
        rankLabel.setText(ranked ? Format.formatNumber(rank) : "--");
        rankLabel.setToolTipText(ranked ? QuantityFormatter.formatNumber(rank) : "Unranked");
    }
}
