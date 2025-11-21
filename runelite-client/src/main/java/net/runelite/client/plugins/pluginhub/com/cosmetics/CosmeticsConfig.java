package net.runelite.client.plugins.pluginhub.com.cosmetics;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("cosmetics")
public interface CosmeticsConfig extends Config
{
    @ConfigItem(
            keyName = "apiKey",
            name = "API Key",
            description = "If you are a ranked member of the RLCosmetics Chat Channel, fill this with your API Key.",
            position = 0
    )
    default String apiKey()
    {
        return "";
    }
}