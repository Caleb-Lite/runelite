package net.runelite.client.plugins.pluginhub.com.zulrahhelper.options;

import java.awt.image.BufferedImage;
import lombok.Getter;
import net.runelite.client.util.ImageUtil;

@Getter
public enum Prayer
{
	MAGIC("protect-from-magic.png"),
	RANGE("protect-from-missiles.png");

	private final BufferedImage image;

	Prayer(String file)
	{
		this.image = ImageUtil.loadImageResource(Prayer.class, "/options/" + file);
	}
}
