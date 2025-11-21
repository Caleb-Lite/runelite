package net.runelite.client.plugins.pluginhub.com.rolerandomizer.ui;

import net.runelite.client.plugins.pluginhub.com.rolerandomizer.RoleRandomizerConfig;
import net.runelite.api.Client;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RoleRandomizerPluginPanel extends PluginPanel
{

        public RandomizeResultPanel resultPanel;
        public RoleRandomizerPanel inputPanel;
        public OptionsPanel optionsPanel;

    public RoleRandomizerPluginPanel(Client client, RoleRandomizerConfig config, ChatMessageManager chatMessageManager)
        {
            super();

            inputPanel = new RoleRandomizerPanel(client, config, chatMessageManager, this);

            resultPanel = new RandomizeResultPanel(this);

            optionsPanel = new OptionsPanel(this, inputPanel);

            JButton clearButton = new JButton("Clear");
            clearButton.setBackground(ColorScheme.PROGRESS_ERROR_COLOR);
            clearButton.setBorder(new EmptyBorder(5, 7, 5, 7));
            clearButton.setToolTipText("This wipes the slate clean!");
            clearButton.addActionListener(e -> {
                inputPanel.cleanSlate();
                resultPanel.cleanSlate();
            });

            JButton removePreviousButton = new JButton("Remove previous roles");
            removePreviousButton.setBackground(ColorScheme.DARKER_GRAY_COLOR);
            removePreviousButton.setFocusable(false);
            removePreviousButton.addActionListener(e -> {
                inputPanel.removePreviousRoles();
            });

            add(inputPanel);
            add(removePreviousButton);
            add(resultPanel);
            add(clearButton);
            add(optionsPanel);
        }

    public boolean addPlayer(String stringSelection) {
        String player = stringSelection.toString();
        // sanitize the string even more
        player = player.replaceAll("\\[.*\\]", "").trim().replace(":", "");
        // player 1 is always covered by the UI
        if (inputPanel.uiFieldPlayer2.getText().isEmpty()) {
            inputPanel.uiFieldPlayer2.setText(player);
            inputPanel.addAllPreferences(inputPanel.uiFieldPlayer2Preferences);
            return true;
        } else if (inputPanel.uiFieldPlayer3.getText().isEmpty()) {
            inputPanel.uiFieldPlayer3.setText(player);
            inputPanel.addAllPreferences(inputPanel.uiFieldPlayer3Preferences);
            return true;
        } else if (inputPanel.uiFieldPlayer4.getText().isEmpty()) {
            inputPanel.uiFieldPlayer4.setText(player);
            inputPanel.addAllPreferences(inputPanel.uiFieldPlayer4Preferences);
            return true;
        } else if (inputPanel.uiFieldPlayer5.getText().isEmpty()) {
            inputPanel.uiFieldPlayer5.setText(player);
            inputPanel.addAllPreferences(inputPanel.uiFieldPlayer5Preferences);
            return true;
        }
        return false;
    }
}

