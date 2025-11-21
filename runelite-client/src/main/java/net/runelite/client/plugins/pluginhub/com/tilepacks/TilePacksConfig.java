package net.runelite.client.plugins.pluginhub.com.tilepacks;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup(TilePacksConfig.GROUP)
public interface TilePacksConfig extends Config {
    String GROUP = "tilepacks";

    @ConfigItem(
            keyName = "hidePlugin",
            name = "Hide on toolbar",
            description = "When checked, the plugin will not appear in the tool bar"
    )
    default boolean hidePlugin() {
        return false;
    }

    @ConfigItem(
            keyName = "showLabels",
            name = "Show Labels",
            description = "When checked, labels will render as defined in each pack"
    )
    default boolean showLabels() {
        return true;
    }

    @ConfigItem(
            keyName = "overrideColorActive",
            name = "Override Color Active",
            description = "When checked, all tiles will render as the selected color"
    )
    default boolean overrideColorActive() {
        return false;
    }

    @Alpha
    @ConfigItem(
            keyName = "overrideColor",
            name = "Override Color",
            description = "If Use Override Color is checked, all tiles will be this color."
    )
    default Color overrideColor() {
        return Color.YELLOW;
    }

    @ConfigItem(
            keyName = "drawTilesOnMinimmap",
            name = "Draw tiles on minimap",
            description = "Configures whether tile packs tiles should be drawn on minimap"
    )
    default boolean drawTilesOnMinimmap() {
        return false;
    }

    @ConfigItem(
            keyName = "borderWidth",
            name = "Border Width",
            description = "Width of the marked tile border"
    )
    default double borderWidth() {
        return 2;
    }

    @ConfigItem(
            keyName = "fillOpacity",
            name = "Fill Opacity",
            description = "Opacity of the tile fill color"
    )
    default int fillOpacity() {
        return 50;
    }
}
/*
 * Copyright (c) 2019, Jordan Atwood <nightfirecat@protonmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */