package net.runelite.client.plugins.pluginhub.com.erishiongames.unrankedkctracker;

import com.google.inject.Inject;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.PluginPanel;

public class UnrankedKcTrackerPanel extends PluginPanel
{
	private JPanel bossPanel;
//	private JPanel nonBossPanel;

	@Inject
	private UnrankedKcTrackerPlugin unrankedKcTrackerPlugin;

	@Inject
	private EventBus eventBus;

	@Inject
	ConfigManager configManager;

	final Font smallFont = FontManager.getRunescapeSmallFont();

	void init()
	{
		setLayout(new FlowLayout());
		setBackground(ColorScheme.DARK_GRAY_COLOR);
		setBorder(new EmptyBorder(10, 10, 10, 10));

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		bossPanel = new JPanel();
		bossPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		bossPanel.setLayout(new GridLayout(0, 1, 0, 0));

//		nonBossPanel = new JPanel();
//		nonBossPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
//		nonBossPanel.setLayout(new GridLayout(0, 1, 0, 0));


		Button refreshKCButton = new Button("Refresh KC");
		refreshKCButton.setFont(smallFont);

		refreshKCButton.setBackground(ColorScheme.DARKER_GRAY_COLOR);

		refreshKCButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setUpBossKCPanels();
			}
		});

		panel.add(refreshKCButton);

		setUpBossKCPanels();
//		setUpNonBossKCPanels();

		add(panel, BorderLayout.CENTER);
		add(bossPanel, BorderLayout.CENTER);
//		add(nonBossPanel, BorderLayout.CENTER);

		eventBus.register(this);
	}

	void deinit()
	{
		eventBus.unregister(this);
	}

	//Tob entry mode is not working
	public void setUpBossKCPanels()
	{
		bossPanel.removeAll();
		for (Bosses boss : Bosses.values()){
			int kc = getKcFromConfig(boss.getName());

			JLabel tempLabel = new JLabel();
			if(kc >= boss.getMinumiumKC())
			{
				tempLabel.setForeground(ColorScheme.PROGRESS_INPROGRESS_COLOR);
				tempLabel.setToolTipText("KC is greater than or equal to the amount needed to be tracked on high scores, use that value instead");
			}
			else
			{
				tempLabel.setForeground(ColorScheme.PROGRESS_COMPLETE_COLOR);
				tempLabel.setToolTipText("KC is less than the amount needed to be tracked on high scores. Use this value");
			}
			tempLabel.setText(kc +": " + boss.getName());
			tempLabel.setFont(smallFont);
			bossPanel.add(tempLabel);
		}
	}

//	public void setUpNonBossKCPanels()
//	{
//		nonBossPanel.removeAll();
//		int queenKills = unrankedKcTrackerPlugin.queenKills;
//		if(unrankedKcTrackerPlugin.queenKills == null){
//			queenKills = -1;
//		}
//		JLabel queenKillsLabel = new JLabel();
//		queenKillsLabel.setForeground(ColorScheme.PROGRESS_COMPLETE_COLOR);
//		queenKillsLabel.setText(queenKills +": Penance Queen");
//	}




	//From https://github.com/runelite/runelite/blob/master/runelite-client/src/main/java/net/runelite/client/plugins/chatcommands/ChatCommandsPlugin.java
	public int getKcFromConfig(String boss)
	{
		if(configManager == null){
			return 0;
		}
		Integer killCount = configManager.getRSProfileConfiguration("killcount", boss.toLowerCase(), int.class);
		return killCount == null ? 0 : killCount;
	}
}
/* BSD 2-Clause License
 * Copyright (c) 2023, Erishion Games LLC <https://github.com/Erishion-Games-LLC>
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