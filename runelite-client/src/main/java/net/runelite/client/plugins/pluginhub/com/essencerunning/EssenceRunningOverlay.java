package net.runelite.client.plugins.pluginhub.com.essencerunning;

import net.runelite.api.Client;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;

public class EssenceRunningOverlay extends Overlay {

    private static final String FREE_INVENTORY_SLOTS = " free inventory slots.";
    private final EssenceRunningPlugin plugin;
    private final Client client;
    private final EssenceRunningConfig config;

    @Inject
    private EssenceRunningOverlay(final EssenceRunningPlugin plugin, final Client client, final EssenceRunningConfig config) {

        super(plugin);
        this.plugin = plugin;
        this.client = client;
        this.config = config;

        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(final Graphics2D graphics) {

        renderBindingNecklace(graphics);

        if (config.enableRunnerMode()) {
            final Widget chatbox = client.getWidget(InterfaceID.Chatbox.CHAT_BACKGROUND);
            if (config.highlightTradeSent() && chatbox != null && !chatbox.isHidden()) {
                drawShape(graphics, chatbox.getBounds(), plugin.isTradeSent() ? Color.GREEN : Color.RED);
            }

            if (config.highlightRingOfDueling() && !plugin.isRingEquipped()) {
                drawWidgetChildren(graphics, client.getWidget(InterfaceID.Bankmain.ITEMS), ItemID.RING_OF_DUELING_8);
                drawWidgetChildren(graphics, client.getWidget(InterfaceID.Bankside.ITEMS), ItemID.RING_OF_DUELING_8);
            }
        }

        return null;
    }

    private void renderBindingNecklace(final Graphics2D graphics) {
        if (config.enableRunecrafterMode()) {
            if (config.highlightEquipBindingNecklace() == EssenceRunningItemDropdown.HighlightEquipBindingNecklace.EQUIP) {
                if (!plugin.isAmuletEquipped()) {
                    drawWidgetChildren(graphics, client.getWidget(InterfaceID.INVENTORY), ItemID.MAGIC_EMERALD_NECKLACE);
                    drawWidgetChildren(graphics, client.getWidget(InterfaceID.Bankmain.ITEMS), ItemID.MAGIC_EMERALD_NECKLACE);
                    drawWidgetChildren(graphics, client.getWidget(InterfaceID.Bankside.ITEMS), ItemID.MAGIC_EMERALD_NECKLACE);
                }
            }
        }
        if (config.enableRunnerMode()) {
            switch (config.highlightTradeBindingNecklace()) {
                case TWENTY_FOUR:
                case TWENTY_FIVE:
                case TWENTY_SIX:
                    if (matchFreeInventorySlots()) {
                        // Widget that contains the inventory while inside a trade transaction
                        drawWidgetChildren(graphics, client.getWidget(InterfaceID.Tradeside.SIDE_LAYER), ItemID.MAGIC_EMERALD_NECKLACE);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void drawWidgetChildren(final Graphics2D graphics, final Widget widget, final int itemId) {
        if (widget != null && widget.getChildren() != null) {
            for (final Widget item : widget.getChildren()) {
                if (!item.isHidden() && item.getItemId() == itemId) {
                    drawShape(graphics, item.getBounds(), Color.RED);
                }
            }
        }
    }

    private void drawShape(final Graphics2D graphics, final Shape shape, final Color color) {
        final Color previousColor = graphics.getColor();
        graphics.setColor(color);
        graphics.draw(shape);
        graphics.setColor(previousColor);
    }

    private boolean matchFreeInventorySlots() {
        // Widget that contains the trading partner's number of free inventory slots
        final Widget freeSlots = client.getWidget(InterfaceID.Trademain.FREE_SPACE_TEXT);
        return freeSlots != null && freeSlots.getText().endsWith(config.highlightTradeBindingNecklace().getOption().split(" ")[0] + FREE_INVENTORY_SLOTS);
    }
}

/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
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