package net.runelite.client.plugins.pluginhub.com.missingpetnotifier;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("missingpetnotifier")
public interface MissingPetNotifierConfig extends Config
{
	@ConfigItem(
		keyName = "timeMissingDelay",
		name = "Time Missing Delay",
		description = "The number of ticks your pet is missing before alerting"
	)
	default int timeMissingDelay()
	{
		return 5;
	}
}
