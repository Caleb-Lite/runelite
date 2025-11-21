package net.runelite.client.plugins.pluginhub.com.dandylyon.inventorynotifier;

import net.runelite.client.config.*;

@ConfigGroup("inventorynotifier")
public interface InventoryNotifierConfig extends Config
{
    @ConfigItem(
            keyName = "enableNotification",
            name = "Enable Notifications",
            description = "Toggles inventory full notifications"
    )
    default boolean enableNotification()
    {
        return true;
    }
}

