package net.runelite.client.plugins.pluginhub.com.vorkathrunwarning;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.Color;

@ConfigGroup("vorkathrunwarning")
public interface VorkathRunWarningConfig extends Config
{
	@ConfigItem(
		keyName = "displayTintWarning",
		name = "Display tint warning",
		description = "Configures whether to warn by coloring the screen.",
		position = 1
	)
	default boolean displayTintWarning() { return true; }

	@Alpha
	@ConfigItem(
		keyName = "tintColor",
		name = "Tint color",
		description = "Color used for the tint warning.",
		position = 2
	)
	default Color tintColor()
	{
		return new Color(255, 127, 127, 50);
	}

	@ConfigItem(
		keyName = "displayTextWarning",
		name = "Display text warning",
		description = "Configures whether to warn with a text box.",
		position = 3
	)
	default boolean displayTextWarning() { return true; }

	@ConfigItem(
		keyName = "textColor",
		name = "Text color",
		description = "Color used for the text warning.",
		position = 4
	)
	default Color textColor() { return Color.white; }
}
