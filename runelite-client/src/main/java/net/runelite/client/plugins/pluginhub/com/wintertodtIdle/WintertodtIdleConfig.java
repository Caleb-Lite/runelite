package net.runelite.client.plugins.pluginhub.com.wintertodtIdle;

import java.awt.Color;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("wintertodtIdle")
public interface WintertodtIdleConfig extends Config
{
	@Alpha
	@ConfigItem(
			keyName = "overlayColor",
			name = "Overlay Color",
			description = "Sets the color of the Idle Overlay",
			position = 1
	)
	default Color overlayColor()
	{
		return new Color(255, 77, 0, 70);
	}

	@ConfigItem(
			keyName = "idleDelay",
			name = "Idle Delay",
			description = "Amount of seconds before idle overlay appears",
			position = 2
	)
	default int idleDelay()
	{
		return 1;
	}
}
