package net.runelite.client.plugins.pluginhub.com.evansloan.collectionlog.ui;

import net.runelite.client.plugins.pluginhub.com.evansloan.collectionlog.CollectionLogPlugin;
import java.awt.image.BufferedImage;
import java.util.function.UnaryOperator;
import javax.annotation.Nonnull;
import javax.swing.ImageIcon;
import net.runelite.client.util.ImageUtil;

public enum Icon
{
	ACCOUNT("/account.png"),
	COLLECTION_LOG("/collection_log.png"),
	COLLECTION_LOG_TOOLBAR("/collection_log_toolbar.png"),
	DISCORD("/discord.png"),
	GITHUB("/github.png"),
	RANDOM("/random.png"),
	INFO("/info.png"),
	;

	private final String file;
	Icon(String file)
	{
		this.file = file;
	}

	/**
	 * Get the raw {@link BufferedImage} of this icon.
	 * @return {@link BufferedImage} of the icon
	 */
	public BufferedImage getImage()
	{
		return ImageUtil.loadImageResource(CollectionLogPlugin.class, file);
	}

	/**
	 * @return the {@link ImageIcon} with no modifications. Equivalent to {@code getIcon(UnaryOperator.identity())}
	 */
	public ImageIcon getIcon()
	{
		return getIcon(UnaryOperator.identity());
	}

	/**
	 * Return this icon.
	 * <br>
	 * The {@link UnaryOperator} is applied to the {@link BufferedImage}. The {@link ImageIcon}
	 * is then created using that modified image.
	 *
	 * @param func the {@link UnaryOperator} to apply to the image
	 * @return the modified {@link ImageIcon}
	 */
	public ImageIcon getIcon(@Nonnull UnaryOperator<BufferedImage> func)
	{
		BufferedImage img = func.apply(getImage());
		return new ImageIcon(img);
	}
}