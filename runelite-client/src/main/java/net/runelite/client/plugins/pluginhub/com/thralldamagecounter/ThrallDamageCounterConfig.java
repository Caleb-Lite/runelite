package net.runelite.client.plugins.pluginhub.com.thralldamagecounter;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("thralldamagecounter")
public interface ThrallDamageCounterConfig extends Config
{
	@ConfigItem(
			keyName = "showDamage",
			name = "Show damage",
			description = "Show total damage instead of DPS"
	)
	default boolean showDamage()
	{
		return true;
	}

	@ConfigItem(
			keyName = "overlayAutoHide",
			name = "Automatically hide overlay",
			description = "Automatically hide the overlay when the boss dies"
	)
	default boolean overlayAutoHide()
	{
		return true;
	}

	@ConfigItem(
			keyName = "overlayHide",
			name = "Always hide overlay",
			description = "Always hide the overlay"
	)
	default boolean overlayHide() {	return false; }
}
