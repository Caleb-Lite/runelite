package net.runelite.client.plugins.pluginhub.io.robrichardson.challengemodeindicator;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup(ChallengeModeIndicatorConfig.GROUP)
public interface ChallengeModeIndicatorConfig extends Config
{
	String GROUP = "ChallengeModeIndicator";

	@ConfigItem(
		keyName = "activeText",
		name = "Active Lever Text",
		description = "The text to show over the lever when challenge mode is active"
	)
	default String activeText() {
		return "Challenge Mode: Active";
	}

	@Alpha
	@ConfigItem(
			keyName = "activeColor",
			name = "Text Color",
			description = "Configures the lever text color"
	)
	default Color activeColor() {
		return Color.GREEN;
	}
}
