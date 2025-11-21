package net.runelite.client.plugins.pluginhub.io.github.mmagicala.gnomeRestaurant;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("gnomerestaurant")
public interface GnomeRestaurantConfig extends Config {

    @ConfigItem(
            keyName = GnomeRestaurantPlugin.SHOW_OVERLAY,
            name = "Show Overlay",
            description = "Configures whether to show the overlay window"
    )
    default boolean showOverlay() {
        return true;
    }

    @ConfigItem(
            keyName = GnomeRestaurantPlugin.SHOW_ORDER_TIMER,
            name = "Show Order Timer",
            description = "Configures whether to show the order timer"
    )
    default boolean showOrderTimer() {
        return true;
    }

    @ConfigItem(
            keyName = GnomeRestaurantPlugin.SHOW_DELAY_TIMER,
            name = "Show Delay Timer",
            description = "Configures whether to show the order delay timer"
    )
    default boolean showDelayTimer() {
        return true;
    }

    @ConfigItem(
            keyName = GnomeRestaurantPlugin.SHOW_HINT_ARROW,
            name = "Show Hint Arrow",
            description = "Configures whether to show the hint arrow toward the order recipient"
    )
    default boolean showHintArrow() {
        return true;
    }

    @ConfigItem(
            keyName = GnomeRestaurantPlugin.SHOW_WORLD_MAP_POINT,
            name = "Show World Map Point",
            description = "Configures whether to show the recipient's location in the world map"
    )
    default boolean showWorldMapPoint() {
        return true;
    }
}
