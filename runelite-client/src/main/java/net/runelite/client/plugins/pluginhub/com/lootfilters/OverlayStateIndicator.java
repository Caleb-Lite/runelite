package net.runelite.client.plugins.pluginhub.com.lootfilters;

import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.ui.overlay.infobox.InfoBoxPriority;

import javax.inject.Inject;
import java.awt.Color;

public class OverlayStateIndicator extends InfoBox {
    private final LootFiltersPlugin plugin;
    private final LootFiltersConfig config;

    @Inject
    public OverlayStateIndicator(LootFiltersPlugin plugin, LootFiltersConfig config) {
        super(Icons.OVERLAY_DISABLED, plugin);
        this.plugin = plugin;
        this.config = config;
        setPriority(InfoBoxPriority.LOW);
    }

    @Override
    public boolean render() {
        return config.hotkeyStateIndicator() && !plugin.isOverlayEnabled();
    }

    @Override
    public String getTooltip() {
        return "[Loot Filters]: The text overlay is currently <col=ff0000>disabled</col>.<br>" +
                "Tap <col=00ffff>" + config.hotkey() + "</col> once to re-enable it.<br><br>" +
                "<col=a0a0a0>You can disable this indicator in plugin config:<br>" +
                "Loot Filters -> Hotkey -> Overlay state indicator</col>";
    }

    @Override
    public String getText() {
        return "";
    }

    @Override
    public Color getTextColor() {
        return null;
    }
}

/*
 * Copyright (c) 2021, Trevor <https://github.com/Trevor159>
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