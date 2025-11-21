package net.runelite.client.plugins.pluginhub.com.fightcavehelper.ui;

import net.runelite.client.plugins.pluginhub.com.fightcavehelper.Wave;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WaveSpawnPanel extends JPanel
{
	public static JLabel wave_number = new JLabel("Wave: " + Wave.getNumber());
	public static JLabel wave_rotation = new JLabel("Rotation: " + Wave.getRotation());
	public static JLabel spawn1 = new JLabel("S1: " + Wave.getSpawn1());
	public static JLabel spawn2 = new JLabel("S2: " + Wave.getSpawn2());
	public static ImageIcon spawnImg = new ImageIcon(String.valueOf(Wave.getImage()));
	public static JLabel img = new JLabel();
	public static JButton next = new JButton("Next");
	public static JButton back = new JButton("Back");


	public WaveSpawnPanel() throws IOException
	{
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setBackground(null);


		img.setIcon(spawnImg);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.weighty = 1;
		c.insets = new Insets(5, 3, 5, 0);
		add(img, c);

		wave_number.setForeground(Color.white);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.weighty = 1;
		add(wave_number, c);

		wave_rotation.setForeground(Color.white);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.weighty = 1;
		add(wave_rotation, c);

		spawn1.setForeground(Color.white);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.weighty = 1;
		//add(spawn1, c);

		spawn2.setForeground(Color.white);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.weighty = 1;
		//add(spawn2, c);

		//JButton next = new JButton("Next");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.weighty = 0.2;
		next.setBackground(Color.green);
		next.setEnabled(false);
		next.addActionListener(e -> {
			if(Wave.number > 2 && Wave.number < 63)
			{
				try
				{
					Wave.increment();
				}
				catch (IOException ex)
				{
					throw new RuntimeException(ex);
				}
				updateWave();
			}
			if (Wave.spawn2_sorted.equals("S-CNW") && Wave.number == 4 && Wave.rotation == 0)
			{
				ButtonPanel.enableWave4();
				enableButtons(false);
			}

		});
		add(next, c);

		//JButton back = new JButton("Back");
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.weighty = 0.2;
		back.setBackground(Color.red);
		back.setEnabled(false);
		back.addActionListener(e -> {
			if(Wave.number > 3)
			{
				try
				{
					Wave.decrement();
				}
				catch (IOException ex)
				{
					throw new RuntimeException(ex);
				}
				updateWave();
			}

		});
		add(back, c);

		Wave.reset();
		updateWave();
	}

	public static void updateWave()
	{
		spawnImg = Wave.getImage();
		img.setIcon(spawnImg);
		wave_number.setText("Wave: " + Wave.getNumber());
		wave_rotation.setText("Rotation: " + Wave.getRotation());
		spawn1.setText("S1: " + Wave.getSpawn1());
		spawn2.setText("S2: " + Wave.getSpawn2());
	}
	public static void enableButtons(boolean state)
	{
		next.setEnabled(state);
		back.setEnabled(state);
	}

}


