package net.runelite.client.plugins.pluginhub.com.innerzoom;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("innerzoom")
public interface InnerZoomConfig extends Config
{
    @Range(min = 100, max = 1500) //idk, configure these to whatever lol
    @ConfigItem(
            name = "Inner Zoom Limit",
            keyName = "innerZoomLimit",
            description = "Set your inner zoom limit",
            position = 0
    )
    default int innerZoomLimit()
    {
        return 550; // default value
    }
}