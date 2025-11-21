package net.runelite.client.plugins.pluginhub.com.prestige;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("prestige")
public interface PrestigeConfig extends Config {
    @ConfigItem(
            keyName = "showRealLevels",
            name = "Show real levels",
            description = "When enabled, your real level will display if it's closer to levelling-up than your prestige level",
            position = 1
    )
    default boolean showRealLevels() {
        return false;
    }

    @ConfigItem(
            keyName = "showVirtualLevels",
            name = "Show virtual levels",
            description = "When enabled, this will show your virtual levels and count them in your total level.",
            position = 2
    )
    default boolean showVirtualLevels() {
        return false;
    }

    @Range(max = 126, min = 1)
    @ConfigItem(
            keyName = "goalLevel",
            name = "Goal Level",
            description = "Max level you're trying to achieve - Half of this will be the level at which you prestige",
            position = 3
    )
    default int goalLevel() {
        return 99;
    }

    @Range(max = 100, min = 2)
    @ConfigItem(
            keyName = "xpFactor",
            name = "XP Factor",
            description = "The rate at which xp is multiplied. Prestige when you have (1 / XP Factor) remaining. XP Factor of 2 means you prestige at half (1/2) xp remaining, for example.",
            position = 4
    )
    default int xpFactor() {
        return 2;
    }

    @ConfigItem(
            keyName = "enableHP",
            name = "Enable HP Prestige",
            description = "Enables prestige levels for HP NOTE: This may not work well with boosts or HP tracking",
            position = 5
    )
    default boolean enableHP() {
        return false;
    }

    @ConfigItem(
            keyName = "enablePrayer",
            name = "Enable Prayer Prestige",
            description = "Enables prestige levels for Prayer  NOTE: This may not work well with boosts or prayer tracking",
            position = 6
    )
    default boolean enablePrayer() {
        return false;
    }

    @ConfigItem(
            keyName = "enableCombat",
            name = "Enable Combat Prestige",
            description = "Enables prestige levels for combat skills (Attack, Strength, Defense, Ranged, Magic) NOTE: This may not work well with boosts",
            position = 7
    )
    default boolean enableCombat() {
        return true;
    }

    @ConfigItem(
            keyName = "enableNonCombat",
            name = "Enable Non-Combat Prestige",
            description = "Enables prestige levels for non-combat skills",
            position = 8
    )
    default boolean enableNonCombat() {
        return true;
    }
}

/*
 * Copyright (c) 2020, Jordan <nightfirecat@protonmail.com>
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
