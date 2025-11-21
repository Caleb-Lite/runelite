package net.runelite.client.plugins.pluginhub.com.fightcavehelper.ui;

import net.runelite.client.plugins.pluginhub.com.fightcavehelper.Wave;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

public class SpawnButton extends JButton
{
	private static final Dimension PREFERRED_SIZE = new Dimension(33, 33);
	//String text;

	public SpawnButton(String text)
	{
		super(text);
		//this.text = text;

		setPreferredSize(PREFERRED_SIZE);
		setBackground(null);
		setForeground(Color.white);
		setBorder(null);
		setFont(new Font("Arial", Font.PLAIN, 12));

//		addActionListener(e -> click());
	}
//	public void click()
//	{
//		setBackground(Color.gray);
//		setForeground(Color.green);
//	}
}

