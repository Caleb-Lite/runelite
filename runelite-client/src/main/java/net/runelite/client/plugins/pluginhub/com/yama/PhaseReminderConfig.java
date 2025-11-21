package net.runelite.client.plugins.pluginhub.com.yama;


import java.awt.Color;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("PhaseReminder")
public interface PhaseReminderConfig extends Config
{
	@Alpha
	@ConfigItem(
			keyName = "overlayColor",
			name = "Overlay Color",
			description = "Overlay Background Color"
	)
	default Color overlayColor() {
		return new Color(255, 0, 0, 150);
	}
}