package net.runelite.client.plugins.pluginhub.com.twitchliveloadout.ui;

import net.runelite.client.plugins.pluginhub.com.twitchliveloadout.fights.ActorType;
import net.runelite.client.plugins.pluginhub.com.twitchliveloadout.fights.Fight;
import net.runelite.client.plugins.pluginhub.com.twitchliveloadout.fights.FightStateManager;
import net.runelite.client.plugins.pluginhub.com.twitchliveloadout.TwitchLiveLoadoutPlugin;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.util.ImageUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class FightPanel extends JPanel
{
	private final FightStateManager fightStateManager;
	private Fight fight;

	private static final ImageIcon DELETE_ICON;
	private static final ImageIcon DELETE_HOVER_ICON;

	private final JPanel wrapper = new JPanel(new GridBagLayout());
	private final JLabel actorNameLabel = new JLabel();
	private final JLabel deleteLabel = new JLabel();

	static
	{
		final BufferedImage deleteImg = ImageUtil.loadImageResource(TwitchLiveLoadoutPlugin.class, "/delete_icon.png");
		DELETE_ICON = new ImageIcon(deleteImg);
		DELETE_HOVER_ICON = new ImageIcon(ImageUtil.alphaOffset(deleteImg, -100));
	}

	public FightPanel(FightStateManager fightStateManager)
	{
		this.fightStateManager = fightStateManager;

		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(0, 0, 10, 0));

		Styles.styleBigLabel(actorNameLabel, "N/A");

		deleteLabel.setIcon(DELETE_ICON);
		deleteLabel.setToolTipText("Reset fight statistics");
		deleteLabel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent mouseEvent)
			{
				int confirm = JOptionPane.showConfirmDialog(FightPanel.this,
					"Are you sure you want to reset this fight?",
					"Warning", JOptionPane.OK_CANCEL_OPTION);

				if (confirm == 0)
				{
					fightStateManager.deleteFight(fight);
				}
			}

			@Override
			public void mouseEntered(MouseEvent mouseEvent)
			{
				deleteLabel.setIcon(DELETE_HOVER_ICON);
				deleteLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent mouseEvent)
			{
				deleteLabel.setIcon(DELETE_ICON);
				deleteLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		wrapper.setLayout(new BorderLayout());
		wrapper.setBorder(new EmptyBorder(10, 10, 10, 10));
		wrapper.setBackground(ColorScheme.DARKER_GRAY_COLOR);
		wrapper.add(actorNameLabel, BorderLayout.WEST);
		wrapper.add(deleteLabel, BorderLayout.EAST);

		add(wrapper, BorderLayout.NORTH);
	}

	public void setFight(Fight fight)
	{
		this.fight = fight;
	}

	public void rebuild()
	{

		// guard: check if the fight is valid
		if (fight == null)
		{
			return;
		}

		final String actorName = fight.getActorName();
		final ActorType actorType = fight.getActorType();

		actorNameLabel.setText(actorName +" ("+ actorType.getName() +")");
	}
}

/*
 * Copyright (c) 2018 Abex
 * Copyright (c) 2018, Psikoi <https://github.com/psikoi>
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