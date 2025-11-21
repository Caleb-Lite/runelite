package net.runelite.client.plugins.pluginhub.com.specialAttackOrbHider;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("specialAttackOrbHiderConfig")
public interface specialAttackOrbHiderConfig extends Config
{
	@ConfigItem(
			position = 0,
			keyName = "hideSpecifiedSpecOrb",
			name = "Specify weapon name",
			description = "Hides the special attack orb for the specified weapon/s"
	)
	default String hideSpecifiedSpecOrb() {
		return "";
	}

	@ConfigItem(
			position = 1,
			keyName = "hideSpecOrbAll",
			name = "Hide for All",
			description = "Hides the special attack orb for all weapons in the game"
	)
	default boolean hideSpecOrbAll() {
		return false;
	}
}
