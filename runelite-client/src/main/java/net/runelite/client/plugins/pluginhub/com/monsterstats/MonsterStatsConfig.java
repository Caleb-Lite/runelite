package net.runelite.client.plugins.pluginhub.com.monsterstats;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("monsterstats")
public interface MonsterStatsConfig extends Config
{
    @ConfigItem(
            keyName = "showStatsMenuOption",
            name = "Show Stats Menu Option",
            description = "Enable right-click 'Stats' option for NPCs, side panel must also be enabled."
    )
    default boolean showStatsMenuOption()
    {
        return true;
    }

    @ConfigItem(
            keyName = "showHoverTooltip",
            name = "Show Hover Tooltip",
            description = "Show a tooltip with elemental weakness and weakness percent when hovering over monsters."
    )
    default boolean showHoverTooltip()
    {
        return true;
    }

    @ConfigItem(
            keyName = "shiftForTooltip",
            name = "Shift for Tooltip",
            description = "Hover tooltip only appears when the Shift key is held."
    )
    default boolean shiftForTooltip()
    {
        return true;
    }

    @ConfigItem(
            keyName = "enableSidePanel",
            name = "Enable Side Panel",
            description = "Enables the searchable side panel to display more monster stats."
    )
    default boolean enableSidePanel() { return true; }
}

//MIT License

//Copyright (c) 2023 Rob Camick

//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:

//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.

//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.