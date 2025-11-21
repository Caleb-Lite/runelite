package net.runelite.client.plugins.pluginhub.com.maxhitcalc;

import net.runelite.client.util.ImageUtil;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class PanelIcons {
    public static final ImageIcon AIR_RUNE_ICON;
    public static final ImageIcon WATER_RUNE_ICON;
    public static final ImageIcon EARTH_RUNE_ICON;
    public static final ImageIcon FIRE_RUNE_ICON;
    public static final ImageIcon EMPTY_RUNE_ICON;

    static {
        // Rune icons for weakness display
        final BufferedImage AIR_ICON_IMG = ImageUtil.loadImageResource(MaxHitCalcPlugin.class, "/elemental_icons/air.png");
        final BufferedImage WATER_ICON_IMG = ImageUtil.loadImageResource(MaxHitCalcPlugin.class, "/elemental_icons/water.png");
        final BufferedImage EARTH_ICON_IMG = ImageUtil.loadImageResource(MaxHitCalcPlugin.class, "/elemental_icons/earth.png");
        final BufferedImage FIRE_ICON_IMG = ImageUtil.loadImageResource(MaxHitCalcPlugin.class, "/elemental_icons/fire.png");
        final BufferedImage EMPTY_RUNE_ICON_IMG = ImageUtil.loadImageResource(MaxHitCalcPlugin.class, "/elemental_icons/no_type.png");

        AIR_RUNE_ICON = new ImageIcon(AIR_ICON_IMG);
        WATER_RUNE_ICON = new ImageIcon(WATER_ICON_IMG);
        EARTH_RUNE_ICON = new ImageIcon(EARTH_ICON_IMG);
        FIRE_RUNE_ICON = new ImageIcon(FIRE_ICON_IMG);
        EMPTY_RUNE_ICON = new ImageIcon(EMPTY_RUNE_ICON_IMG);
    }
}

/* CombatSpell.java
 * Separates spells by SpriteID. Useful for getting the selected auto-cast spell.
 *
 *
 * Copyright (c) 2023, Jacob Burton <https://github.com/j-cob44>
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
