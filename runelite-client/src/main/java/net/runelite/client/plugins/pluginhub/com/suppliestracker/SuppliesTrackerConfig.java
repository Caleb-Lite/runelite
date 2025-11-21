package net.runelite.client.plugins.pluginhub.com.suppliestracker;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import static com.suppliestracker.SuppliesTrackerConfig.GROUP_NAME;

@ConfigGroup(GROUP_NAME)
public interface SuppliesTrackerConfig extends Config
{
	String GROUP_NAME = "suppliestracker";

	@ConfigItem(
			keyName = "chargesBox",
			name = "Show weapons charges used box?",
			description = "Separates items with charges to show how many of those charges you used."
	)
	default boolean chargesBox()
	{
		return true;
	}

	@ConfigItem(
		keyName = "vorkathsHead",
		name = "Attached Vorkath's head?",
		description = "Whether or not you attached a Vorkath's head to your Ranging cape for Assembler effect."
	)
	default boolean vorkathsHead()
	{
		return false;
	}
}
