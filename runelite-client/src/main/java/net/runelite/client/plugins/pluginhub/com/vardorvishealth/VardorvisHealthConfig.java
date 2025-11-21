package net.runelite.client.plugins.pluginhub.com.vardorvishealth;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;




@ConfigGroup("Vardorvis Health Tracker")
public interface VardorvisHealthConfig extends Config
{
	@ConfigItem(
			keyName = "showOverlay",
			name = "Show Overlay",
			description = "Configures whether or not the overlay is displayed"
	)
	default boolean showOverlay() {
		return true;
	}



}
