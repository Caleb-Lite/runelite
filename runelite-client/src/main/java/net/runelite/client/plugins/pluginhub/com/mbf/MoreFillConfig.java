package net.runelite.client.plugins.pluginhub.com.mbf;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("morebankfillers")
public interface MoreFillConfig extends Config
{
	@ConfigItem(
		keyName = "overlayType",
		name = "Type of Overlay",
		description = "The type of overlay should cover the bank filler",
			position = 0
	)
	default MoreFillOverlayTypes overlayType()
	{
		return MoreFillOverlayTypes.SQUARE;
	}

	@ConfigItem(
			keyName = "overlayTheme",
			name = "Overlay Theme",
			description = "Theme of the overlay",
			position = 2
	)
	default MoreFillOverlayThemes overlayTheme()
	{
		return MoreFillOverlayThemes.Gold;
	}

}

