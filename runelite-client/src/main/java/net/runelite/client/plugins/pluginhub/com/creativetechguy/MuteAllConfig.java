package net.runelite.client.plugins.pluginhub.com.creativetechguy;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(MuteAllConfig.GROUP_NAME)
public interface MuteAllConfig extends Config {
    String GROUP_NAME = "mute-all";

    @ConfigItem(
            keyName = "seenAlert",
            name = "",
            description = "",
            hidden = true
    )
    default boolean seenAlert() {
        return false;
    }

    @ConfigItem(
            keyName = "seenAlert",
            name = "",
            description = "",
            hidden = true
    )
    void seenAlert(boolean seen);
}
