package net.runelite.client.plugins.pluginhub.com.fishingspot;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Units;

@ConfigGroup("fishingspottimer")
public interface FishingSpotTimerConfig extends Config
{
    @ConfigItem(
            position = 1,
            keyName = "maxMoveTime",
            name = "Max move time",
            description = "The maximum expected time after which a fishing spot will move in seconds."
    )
    @Units(Units.SECONDS)
    default int maxMoveTime()
    {
        return 300;
    }

    @ConfigItem(
            position = 2,
            keyName = "minMoveTime",
            name = "Min move time",
            description = "The minimum expected time after which a fishing spot will move in seconds."
    )
    @Units(Units.SECONDS)
    default int minMoveTime()
    {
        return 150;
    }
}
