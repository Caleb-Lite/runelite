package net.runelite.client.plugins.pluginhub.com.towncrier;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("towncrier")
public interface TownCrierConfig extends Config
{
	@ConfigItem(
		keyName = "cry1",
		name = "The First Cry",
		description = "One of the town crier messages"
	)
	default String cry1()
	{
		return "Have fun playing RuneScape!";
	}

	@ConfigItem(
			keyName = "cry2",
			name = "The Second Cry",
			description = "One of the town crier messages"
	)
	default String cry2()
	{
		return "";
	}

	@ConfigItem(
			keyName = "cry3",
			name = "The Third Cry",
			description = "One of the town crier messages"
	)
	default String cry3()
	{
		return "";
	}
}

