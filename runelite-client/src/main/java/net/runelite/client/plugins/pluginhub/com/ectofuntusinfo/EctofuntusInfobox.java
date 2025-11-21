package net.runelite.client.plugins.pluginhub.com.ectofuntusinfo;

import java.awt.Color;
import java.awt.image.BufferedImage;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.util.QuantityFormatter;

public class EctofuntusInfobox extends InfoBox
{
	private final EctofuntusInfoPlugin plugin;
	private final EctofuntusInfoConfig config;

	public EctofuntusInfobox(BufferedImage image, EctofuntusInfoPlugin plugin, EctofuntusInfoConfig config)
	{
		super(image, plugin);
		this.plugin = plugin;
		this.config = config;
	}

	@Override
	public String getText()
	{
		int number = plugin.getStoredTokens();

		if (config.countRemaining())
		{
			number = EctofuntusInfoPlugin.MAX_TOKEN_AMOUNT - number;
		}

		if (config.counterType() == EctofuntusCounterType.BONEMEAL)
		{
			number /= EctofuntusInfoPlugin.TOKENS_PER_BONEMEAL;
		}

		return QuantityFormatter.formatNumber(number);
	}

	@Override
	public Color getTextColor()
	{
		int tokens = plugin.getStoredTokens();
		if (tokens >= EctofuntusInfoPlugin.MAX_TOKEN_AMOUNT)
		{
			return Color.RED;
		}
		else if (tokens >= EctofuntusInfoPlugin.HIGH_WARN_TOKEN_AMOUNT)
		{
			return Color.ORANGE;
		}
		else if (tokens >= EctofuntusInfoPlugin.LOW_WARN_TOKEN_AMOUNT)
		{
			return Color.YELLOW;
		}

		return null;
	}

	@Override
	public String getTooltip()
	{
		int tokens = plugin.getStoredTokens();
		StringBuilder sb = new StringBuilder();
		sb.append("You have ")
			.append(QuantityFormatter.formatNumber(tokens))
			.append(" Ecto-tokens to collect.");

		if (tokens >= EctofuntusInfoPlugin.MAX_TOKEN_AMOUNT)
		{
			sb.append("</br>You must collect your Ecto-tokens before you can continue using the Ectofuntus.");
		}
		else
		{
			sb.append("</br>You can still use ")
				.append(QuantityFormatter.formatNumber(
					(EctofuntusInfoPlugin.MAX_TOKEN_AMOUNT - tokens) / EctofuntusInfoPlugin.TOKENS_PER_BONEMEAL))
				.append(" more Bonemeal on the Ectofuntus.");
		}

		return sb.toString();
	}

	@Override
	public boolean render()
	{
		return plugin.isInEctofuntusRegion() && plugin.getStoredTokens() > 0;
	}
}

