package net.runelite.client.plugins.pluginhub.com.ashleythew.cookingtooltip;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("cookingtooltip")
public interface CookingTooltipConfig extends Config {
    @ConfigItem(keyName = "showCookingTooltips", name = "Show Cooking Tooltips", description = "Show cooking success chances in tooltips", position = 1)
    default boolean showCookingTooltips() {
        return true;
    }
}
