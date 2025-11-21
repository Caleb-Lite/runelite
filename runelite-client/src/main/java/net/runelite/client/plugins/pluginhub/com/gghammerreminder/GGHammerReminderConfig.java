package net.runelite.client.plugins.pluginhub.com.gghammerreminder;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.Color;

@ConfigGroup("ghammerreminder")
public interface GGHammerReminderConfig extends Config {
	@ConfigItem(
			keyName = "showOverlay",
			name = "Show Overlay",
			description = "Toggle to show or hide the overlay",
			position = 1
	)
	default boolean showOverlay() {
		return true;
	}

	@Alpha
	@ConfigItem(
			keyName = "overlayColor",
			name = "Overlay Color",
			description = "The color of the overlay.",
			position = 2
	)
	default Color overlayColor() {
		return new Color(255, 0, 0, 150);
	}

	@ConfigItem(
			keyName = "removeRightClickMenu",
			name = "Remove Right-click Menu",
			description = "Toggle to remove or keep right-click menu options",
			position = 3
	)
	default boolean removeRightClickMenu() {
		return true;
	}
}
