package net.runelite.client.plugins.pluginhub.com.github.ldavid432.config;

import net.runelite.client.plugins.pluginhub.com.github.ldavid432.GauntletLootConfig;
import net.runelite.client.plugins.pluginhub.com.github.ldavid432.GauntletLootUtil;
import javax.annotation.Nonnull;

public enum GauntletTitle2
{
	GAUNTLET,
	HUNLLEF,
	CUSTOM;

	@Nonnull
	public String getText(GauntletLootConfig config, String hunllefText, String gauntletText)
	{
		switch (this)
		{
			case GAUNTLET:
				return gauntletText;
			case CUSTOM:
				return config.getChestCustomTitle();
			case HUNLLEF:
				// Fall-through
			default:
				return hunllefText;
		}
	}
}
