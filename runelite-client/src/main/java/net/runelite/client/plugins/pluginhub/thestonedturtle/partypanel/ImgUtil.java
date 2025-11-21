package net.runelite.client.plugins.pluginhub.thestonedturtle.partypanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImgUtil
{
	/**
	 * Combines the two images into one, from left to right using the first images specs.
	 * @param left image to center over background
	 * @param right image to overlap
	 * @return overlapped image
	 */
	public static BufferedImage combineImages(final BufferedImage left, final BufferedImage right)
	{
		BufferedImage combined = new BufferedImage(left.getWidth() + right.getWidth(), left.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = combined.createGraphics();
		g2d.drawImage(left, 0, 0, null);
		g2d.drawImage(right, left.getWidth(), 0, null);
		g2d.dispose();

		return combined;
	}

	/**
	 * Overlaps the foreground image centered over the background image.
	 * @param foreground image to center over background
	 * @param background image to overlap
	 * @return overlapped image
	 */
	public static BufferedImage overlapImages(final BufferedImage foreground, final BufferedImage background, boolean isEquipment)
	{
		final int centeredX = background.getWidth() / 2 - foreground.getWidth() / 2;
		final int centeredY = background.getHeight() / 2 - foreground.getHeight() / 2;

		BufferedImage combined = new BufferedImage(background.getWidth(), background.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = combined.createGraphics();
		g2d.drawImage(background, 0, 0, null);

		// For some reason equipment icons are offset and need to be adjusted
		if (isEquipment)
		{
			g2d.drawImage(foreground, centeredX + 2, centeredY, null);
		}
		else
		{
			g2d.drawImage(foreground, centeredX, centeredY, null);
		}

		g2d.dispose();

		return combined;
	}
}
