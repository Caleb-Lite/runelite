package net.runelite.client.plugins.pluginhub.com.fightcavehelper;

import net.runelite.client.plugins.pluginhub.com.fightcavehelper.ui.ButtonPanel;
import net.runelite.client.plugins.pluginhub.com.fightcavehelper.ui.WaveSpawnPanel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import net.runelite.client.util.ImageUtil;

public class Wave extends JPanel
{
	public static int rotation;
	public static int number;
	private static String waveImgPath;
	private static String url;
	private static BufferedImage image;
	private static ImageIcon img;
	public static final String IMG_PATH = "/waves/%s-%s.jpg";
	//public static final String URL_PATH = "https://www.dropbox.com/home/fight-cave-waves?preview=%s-%s.jpg";
	public static String spawn1 = "-";
	public static String spawn2 = "-";
	public static String spawn4 = "-";
	public static String spawn2_sorted = "-";


	public Wave() //throws IOException
	{
		rotation = 0;
		number = 1;
		waveImgPath = String.format(IMG_PATH, rotation, number);
		image = ImageUtil.loadImageResource(getClass(), waveImgPath);
		img = new ImageIcon(image);
//		try
//		{
//		url = String.format(URL_PATH, rotation, number);
//		image = ImageIO.read(new URL(url));
//		img = new ImageIcon(image);
//		}
//		catch (IOException e){
//			e.printStackTrace();
//		}
	}

	public static void setImage() //throws IOException
	{
		waveImgPath = String.format(IMG_PATH, rotation, number);
		image = ImageUtil.loadImageResource(Wave.class, waveImgPath);
		img = new ImageIcon(image);
//		try
//		{
//		url = String.format(URL_PATH, rotation, number);
//		image = ImageIO.read(new URL(url));
//		img = new ImageIcon(image);
//		}
//		catch (IOException e){
//			e.printStackTrace();
//		}
	}

	public static ImageIcon getImage() { return img; }

	public static int getRotation()
	{
		return rotation;
	}
	public static int getNumber()
	{
		return number;
	}

	public static void setRotation(int rotation)
	{
		Wave.rotation = rotation;
	}

	public static void setNumber(int number)
	{
		Wave.number = number;
	}

	public static void reset() throws IOException
	{
		setRotation(0);
		setNumber(1);
		spawn1 = "-";
		spawn2 = "-";
		spawn4 = "-";
		setImage();
		WaveSpawnPanel.updateWave();
	}

	public static void addWaveSpawn() throws IOException
	{
		if (number == 1)
		{
			spawn1 = ButtonPanel.spawn1;
			increment();
		}

		else if (number == 2)
		{
			spawn2 = ButtonPanel.spawn2A + ButtonPanel.spawn2B;

			spawn2_sorted = spawn1 + "-" + sortString(spawn2);

			switch (spawn2_sorted)
			{
				case "C-NSWW":
					rotation = 1;
					updatePanel();
					break;
				case "C-ESS":
					rotation = 2;
					updatePanel();
					break;
				case "C-ESSW":
					rotation = 3;
					updatePanel();
					break;
				case "NW-CS":
					rotation = 4;
					updatePanel();
					break;
				case "NW-CES":
					rotation = 5;
					updatePanel();
					break;
				case "NW-ESSW":
					rotation = 6;
					updatePanel();
					break;
				case "S-CNW":
					//ButtonPanel.enableWave4();
					rotation = 0;
					updatePanel();
					//increment();
					break;
				case "S-ESSW":
					rotation = 9;
					updatePanel();
					break;
				case "SE-NSW":
					rotation = 10;
					updatePanel();
					break;
				case "SE-CSW":
					rotation = 11;
					updatePanel();
					break;
				case "SE-SSW":
					rotation = 12;
					updatePanel();
					break;
				case "SW-CNW":
					rotation = 13;
					updatePanel();
					break;
				case "SW-NSW":
					rotation = 14;
					updatePanel();
					break;
				case "SW-ESS":
					rotation = 15;
					updatePanel();
					break;
				default:
					ButtonPanel.resetBtns();
					Wave.reset();
					System.out.println("Invalid selection, try again");
					//updatePanel();
			}
		}
		else //if (number == 3)
		{
			spawn4 = ButtonPanel.spawn4A + ButtonPanel.spawn4B;
			String s = sortString(spawn4);
			WaveSpawnPanel.enableButtons(false);

			switch (s)
			{
				case "ESS":
					rotation = 7;
					WaveSpawnPanel.enableButtons(true);
					updatePanel();
					break;
				case "ESSW":
					rotation = 8;
					WaveSpawnPanel.enableButtons(true);
					updatePanel();
					break;
				default:
					ButtonPanel.resetBtns();
					Wave.reset();
					System.out.println("Invalid selection, try again");
					//updatePanel();
			}
		}
	}

	public static String getSpawn1()
	{
		return spawn1;
	}

	public static String getSpawn2()
	{
		return sortString(spawn2);
	}

	public static void increment() throws IOException
	{
		number++;
		setImage();
	}
	public static void decrement() throws IOException
	{
		number--;
		setImage();
	}

	public static String sortString(String input)
	{
		char[] tempArr = input.toCharArray();
		Arrays.sort(tempArr);
		return new String(tempArr);
	}
	public static void updatePanel() throws IOException
	{
		ButtonPanel.hidePanel();
		increment();
		WaveSpawnPanel.enableButtons(true);
	}

}
