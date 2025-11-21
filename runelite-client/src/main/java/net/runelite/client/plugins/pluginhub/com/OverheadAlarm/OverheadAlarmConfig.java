package net.runelite.client.plugins.pluginhub.com.OverheadAlarm;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("Overhead Alarm")
public interface OverheadAlarmConfig extends Config
{
    @ConfigItem(
            keyName = "melee",
            name = "Protect from Melee",
            description = "Treat Protect from Melee as valid",
            position = 1
    )
    default boolean enableMelee()
    {
        return true; // default ON
    }

    @ConfigItem(
            keyName = "range",
            name = "Protect from Missiles",
            description = "Treat Protect from Missiles as valid",
            position = 2
    )
    default boolean enableRange()
    {
        return true; // default ON
    }

    @ConfigItem(
            keyName = "magic",
            name = "Protect from Magic",
            description = "Treat Protect from Magic as valid",
            position = 3
    )
    default boolean enableMagic()
    {
        return true; // default ON
    }
}
