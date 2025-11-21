package net.runelite.client.plugins.pluginhub.com.fightcavehelper.ui;

import net.runelite.client.plugins.pluginhub.com.fightcavehelper.Wave;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel
{
	private static final Insets INSETS_LEFT_BORDER = new Insets(1, 0, 1, 1);
	private static final Insets INSETS_RIGHT_BORDER = new Insets(1, 1, 1, 0);
	private static final Insets INSETS = new Insets(1, 1, 1, 1);
	public static String spawn1;
	public static String spawn2A;
	public static String spawn2B;
	public static String spawn4A;
	public static String spawn4B;
	private static int choices = 0;
	private static int choices4 = 0;
	static ArrayList<JButton> btns1 = new ArrayList<>();
	static ArrayList<JButton> btns2 = new ArrayList<>();
	static ArrayList<JButton> btns4 = new ArrayList<>();

	//private final FightCaveHelperPanel panel;
	private final GridBagConstraints c;
	public static JLabel wave1 = new JLabel("Wave 1:");
	public static JLabel wave2 = new JLabel("Wave 2:");
	public static JLabel wave4 = new JLabel("Wave 4:");


	public ButtonPanel()
	{
		super();

		//this.panel = panel;

		setLayout(new GridBagLayout());

		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy	= 0;

		//setBackground(null);

//		JLabel title = new JLabel("Wave 1: ");
//		title.setFont(new Font("Verdana", Font.PLAIN, 12));
//		title.setForeground(Color.white);
//		add(title);

		add(wave1);
		createBtns1();

		add(wave2);
		createBtns2();

		add(wave4);
		wave4.setVisible(false);
		createBtns4();
	}

	public void createBtns1()
	{


		btns1.add(new SpawnButton("C"));
		btns1.add(new SpawnButton("NW"));
		btns1.add(new SpawnButton("S"));
		btns1.add(new SpawnButton("SE"));
		btns1.add(new SpawnButton("SW"));

		addComp(wave1);

		for (JButton btn : btns1)
		{
			btn.addActionListener(e -> {
				disableBtns1();
				setSpawn1(btn);
				enableBtns2();
				try
				{
					Wave.addWaveSpawn();
				}
				catch (IOException ex)
				{
					throw new RuntimeException(ex);
				}
				WaveSpawnPanel.updateWave();

			});
			add(btn);
			addComp(btn);
		}
	}

	public void disableBtns1()
	{
		for (JButton btn : btns1)
		{
			btn.setEnabled(false);
		}
	}

	public void setSpawn1(JButton btn)
	{
		spawn1 = btn.getText();
		btn.setBackground(Color.green);
		//Debug();
	}

	public void createBtns2()
	{

		btns2.add(new SpawnButton("C"));
		btns2.add(new SpawnButton("NW"));
		btns2.add(new SpawnButton("S"));
		btns2.add(new SpawnButton("SE"));
		btns2.add(new SpawnButton("SW"));

		addComp(wave2);

		for (JButton btn : btns2)
		{
			btn.setEnabled(false);
			btn.addActionListener(e -> {
				btn.setEnabled(false);
				//choices++;
				disableBtns2(btn.getText());
				try
				{
					setSpawn2(btn);
				}
				catch (IOException ex)
				{
					throw new RuntimeException(ex);
				}
			});
			add(btn);
			addComp(btn);
		}
	}

	public void enableBtns2()
	{
		for (JButton btn : btns2)
		{
			if (!Objects.equals(btn.getText(), spawn1))
			{
				btn.setEnabled(true);
			}
		}
	}

	public void disableBtns2(String text)
	{
//		if (choices < 2)
//		{
//
//		}
		if (choices >= 2)
		{
			for (JButton btn : btns2)
			{
				if (!btn.getText().equals(text))
				{
					btn.setEnabled(false);
				}
			}
		}
	}

	public void setSpawn2(JButton btn) throws IOException
	{
		if (choices == 0)
		{
			choices++;
			spawn2A = btn.getText();
			btn.setBackground(Color.green);
		}
		else
		{
			choices++;
			spawn2B = btn.getText();
			btn.setBackground(Color.green);
			//Wave.increment();
			Wave.addWaveSpawn();
			WaveSpawnPanel.updateWave();
		}
		//Debug();

	}

	public void createBtns4()
	{

//		btns4.add(new SpawnButton("C"));
//		btns4.add(new SpawnButton("NW"));
		btns4.add(new SpawnButton("S"));
		btns4.add(new SpawnButton("SE"));
		btns4.add(new SpawnButton("SW"));

		addComp(wave4);

		for (JButton btn : btns4)
		{
			btn.setEnabled(false);
			btn.setVisible(false);
			btn.addActionListener(e -> {
				btn.setEnabled(false);
				//choices++;
				//disableBtns4();
				try
				{
					setSpawn4(btn);
				}
				catch (IOException ex)
				{
					throw new RuntimeException(ex);
				}
			});
			add(btn);
			addComp(btn);
		}
	}

	public void disableBtns4()
	{
		for (JButton btn : btns4)
		{
			btn.setEnabled(false);
		}
	}

	public void setSpawn4(JButton btn) throws IOException
	{
		if (choices4 == 0)
		{
			choices4++;
			spawn4A = btn.getText();
			btn.setBackground(Color.green);
		}
		else
		{
			spawn4B = btn.getText();
			btn.setBackground(Color.green);
			Wave.addWaveSpawn();
			WaveSpawnPanel.updateWave();
		}

	}

	public static void enableWave4()
	{
		for (JButton btn : btns2)
		{
			btn.setEnabled(false);
		}

		wave4.setVisible(true);

		for (JButton btn : btns4)
		{
			btn.setEnabled(true);
			btn.setVisible(true);
		}
	}


	public static void resetBtns()
	{
		choices = 0;
		choices4 = 0;
		spawn1 = "-";
		spawn2A = "-";
		spawn2B = "-";
		spawn4A = "-";
		spawn4B = "-";
		wave1.setVisible(true);
		wave2.setVisible(true);
		wave4.setVisible(false);
		WaveSpawnPanel.enableButtons(false);
		for (JButton btn : btns1)
		{
			btn.setForeground(Color.white);
			btn.setBackground(null);
			btn.setEnabled(true);
			btn.setVisible(true);
		}
		for (JButton btn : btns2)
		{
			btn.setForeground(Color.white);
			btn.setBackground(null);
			btn.setEnabled(false);
			btn.setVisible(true);
		}
		for (JButton btn : btns4)
		{
			btn.setForeground(Color.white);
			btn.setBackground(null);
			btn.setEnabled(false);
			btn.setVisible(false);
		}
		//Debug();
	}

	private void addComp(Component component)
	{
		switch (c.gridx)
		{
			case 0:
				c.insets = INSETS_LEFT_BORDER;
				break;
			case 6:
				c.insets = INSETS_RIGHT_BORDER;
				break;
			default:
				c.insets = INSETS;
		}
		if (c.gridwidth == 6)
		{
			c.insets = INSETS_RIGHT_BORDER;
		}
		add(component, c);
		c.gridx = ++c.gridx % 6;
		if (c.gridx == 0)
		{
			++c.gridy;
		}
	}

	public static void hidePanel()
	{
		wave1.setVisible(false);
		wave2.setVisible(false);
		wave4.setVisible(false);
		for (JButton btn : btns1)
		{
			btn.setVisible(false);
		}
		for (JButton btn : btns2)
		{
			btn.setVisible(false);
		}
		for (JButton btn : btns4)
		{
			btn.setVisible(false);
		}
	}

	public static void Debug()
	{
		System.out.println("-------------------");
		System.out.println("Spawn1 = " + spawn1);
		System.out.println("Spawn2A = " + spawn2A);
		System.out.println("Spawn2B = " + spawn2B);
		System.out.println("Choices = " + choices);
		System.out.println("Rotation = " + Wave.rotation);
		System.out.println("Wave Number = " + Wave.number);
	}

}
