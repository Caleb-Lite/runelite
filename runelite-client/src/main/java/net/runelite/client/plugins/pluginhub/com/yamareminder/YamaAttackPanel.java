package net.runelite.client.plugins.pluginhub.com.yamareminder;

import javax.inject.Singleton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.PluginPanel;

import java.awt.*;

@Singleton
public class YamaAttackPanel extends PluginPanel
{
    private JLabel phase2ReminderLabel;
    private JLabel phase3ReminderLabel;

    public YamaAttackPanel()
    {
        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(CENTER_ALIGNMENT);

        JLabel headerLabel = new JLabel("Attack Reminder");
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setAlignmentX(CENTER_ALIGNMENT);
        headerLabel.setFont(FontManager.getRunescapeFont().deriveFont(24f));
        add(headerLabel);
        init();
    }

    void init() {
        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
        selectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        selectionPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel currentAttackLabel = new JLabel("2nd Glyph Attack Style:");
        currentAttackLabel.setAlignmentX(CENTER_ALIGNMENT);
        currentAttackLabel.setFont(FontManager.getRunescapeFont().deriveFont(16f));
        selectionPanel.add(currentAttackLabel);
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JRadioButton magicButton = new JRadioButton("Magic");
        magicButton.setAlignmentX(CENTER_ALIGNMENT);
        magicButton.setFont(FontManager.getRunescapeFont().deriveFont(14f));
        
        JRadioButton rangeButton = new JRadioButton("Range");
        rangeButton.setAlignmentX(CENTER_ALIGNMENT);
        rangeButton.setFont(FontManager.getRunescapeFont().deriveFont(14f));

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(magicButton);
        buttonGroup.add(rangeButton);

        selectionPanel.add(magicButton);
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        selectionPanel.add(rangeButton);
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel phase2Label = new JLabel("Phase 2 Reminder:");
        phase2Label.setAlignmentX(CENTER_ALIGNMENT);
        phase2Label.setFont(FontManager.getRunescapeFont().deriveFont(16f));
        selectionPanel.add(phase2Label);
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        phase2ReminderLabel = new JLabel("Select an attack style");
        phase2ReminderLabel.setAlignmentX(CENTER_ALIGNMENT);
        phase2ReminderLabel.setFont(FontManager.getRunescapeFont().deriveFont(Font.BOLD, 18f));
        phase2ReminderLabel.setForeground(Color.YELLOW);
        selectionPanel.add(phase2ReminderLabel);
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel phase3Label = new JLabel("Phase 3 Reminder:");
        phase3Label.setAlignmentX(CENTER_ALIGNMENT);
        phase3Label.setFont(FontManager.getRunescapeFont().deriveFont(16f));
        selectionPanel.add(phase3Label);
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        phase3ReminderLabel = new JLabel("Select an attack style");
        phase3ReminderLabel.setAlignmentX(CENTER_ALIGNMENT);
        phase3ReminderLabel.setFont(FontManager.getRunescapeFont().deriveFont(Font.BOLD, 18f));
        phase3ReminderLabel.setForeground(Color.YELLOW);
        selectionPanel.add(phase3ReminderLabel);

        magicButton.addActionListener(e -> {
            phase2ReminderLabel.setText("Start MAGE");
            phase2ReminderLabel.setForeground(Color.ORANGE);
            phase3ReminderLabel.setText("Use RANGE");
            phase3ReminderLabel.setForeground(Color.GREEN);
        });

        rangeButton.addActionListener(e -> {
            phase2ReminderLabel.setText("Start RANGE");
            phase2ReminderLabel.setForeground(Color.ORANGE);
            phase3ReminderLabel.setText("Use MAGIC");
            phase3ReminderLabel.setForeground(Color.CYAN);
        });

        add(selectionPanel);
    }
}
