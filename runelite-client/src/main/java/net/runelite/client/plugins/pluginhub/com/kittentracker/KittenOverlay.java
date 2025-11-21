package net.runelite.client.plugins.pluginhub.com.kittentracker;

import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.OverlayMenuEntry;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import org.apache.commons.lang3.time.DurationFormatUtils;

import javax.inject.Inject;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

public class KittenOverlay extends OverlayPanel {
    private final Client client;
    private final KittenPlugin kittenPlugin;
    private final KittenConfig kittenConfig;
    private Instant blinkHunger = null;
    private Instant blinkAttention = null;

    private final static int blinkPeriod = 1000;

    @Inject
    private KittenOverlay(Client client, KittenPlugin kittenPlugin, KittenConfig kittenConfig) {
        super(kittenPlugin);
        setPosition(OverlayPosition.BOTTOM_LEFT);
        this.client = client;
        this.kittenPlugin = kittenPlugin;
        this.kittenConfig = kittenConfig;
        getMenuEntries().add(new OverlayMenuEntry(MenuAction.RUNELITE_OVERLAY_CONFIG, OverlayManager.OPTION_CONFIGURE, "Kitten Tracker Overlay"));
        setPreferredSize(new Dimension(162, 88));
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (kittenPlugin.playerHasFollower() && (kittenPlugin.isKitten() || kittenPlugin.isCat())) {
            if ((kittenPlugin.isKitten() && (kittenConfig.kittenOverlay() || kittenConfig.kittenHungryOverlay()
                    || kittenConfig.kittenAttentionOverlay())
                    || (kittenPlugin.isCat()) && kittenConfig.catOverlay())) {
                panelComponent.getChildren().add(LineComponent.builder()
                        .leftFont(graphics.getFont().deriveFont(
                                Collections.singletonMap(
                                        TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD)))
                        .left(kittenPlugin.isKitten() ? "Kitten status" : "Cat status")
                        .build());
            }

            if (kittenPlugin.isKitten()) {
                if (kittenConfig.kittenOverlay()) {
                    LineComponent lineComponent = LineComponent.builder()
                            .left("Grown up in: ")
                            .right(DurationFormatUtils.formatDuration(kittenPlugin.getTimeUntilFullyGrown(), getFormatForTime(), true))
                            .build();
                    panelComponent.getChildren().add(lineComponent);
                }

                if (kittenConfig.kittenHungryOverlay()) {
                    Color color = Color.WHITE;
                    Long timeUntilHungryMs = kittenPlugin.getTimeBeforeHungry();
                    if (timeUntilHungryMs < KittenPlugin.HUNGRY_TIME_ONE_MINUTE_WARNING_MS) { //
                        if (blinkHunger == null) {
                            blinkHunger = Instant.now();
                        } else {
                            Duration timeSinceLastBlink = Duration.between(blinkHunger, Instant.now());

                            if (timeSinceLastBlink.toMillis() > 2 * blinkPeriod) {
                                blinkHunger = Instant.now();
                                color = Color.ORANGE;
                            } else if (timeSinceLastBlink.toMillis() > blinkPeriod) {
                                color = Color.ORANGE;
                            } else {
                                color = Color.RED;
                            }
                        }
                    } else if (timeUntilHungryMs < KittenPlugin.HUNGRY_FINAL_WARNING_TIME_LEFT_IN_SECONDS * 1000) {
                        color = Color.RED;
                    } else if (timeUntilHungryMs < KittenPlugin.HUNGRY_FIRST_WARNING_TIME_LEFT_IN_SECONDS * 1000) {
                        color = Color.ORANGE;
                    }

                    panelComponent.getChildren().add(LineComponent.builder()
                            .left("Hungry in: ")
                            .rightColor(color)
                            .right(DurationFormatUtils.formatDuration(timeUntilHungryMs, getFormatForTime(), true))
                            .build()
                    );
                }

                if (kittenConfig.kittenAttentionOverlay()) {
                    Color color = Color.WHITE;
                    Long timeBeforeNeedingAttention = kittenPlugin.getTimeBeforeNeedingAttention();
                    if (timeBeforeNeedingAttention < KittenPlugin.ATTENTION_TIME_ONE_MINUTE_WARNING_MS) { //
                        if (blinkAttention == null) {
                            blinkAttention = Instant.now();
                        } else {
                            Duration timeSinceLastBlink = Duration.between(blinkAttention, Instant.now());

                            if (timeSinceLastBlink.toMillis() > 2 * blinkPeriod) {
                                blinkAttention = Instant.now();
                                color = Color.ORANGE;
                            } else if (timeSinceLastBlink.toMillis() > blinkPeriod) {
                                color = Color.ORANGE;
                            } else {
                                color = Color.RED;
                            }
                        }
                    } else if (timeBeforeNeedingAttention < KittenPlugin.ATTENTION_FINAL_WARNING_TIME_LEFT_IN_SECONDS * 1000) {
                        color = Color.RED;
                    } else if (timeBeforeNeedingAttention < KittenPlugin.ATTENTION_FIRST_WARNING_TIME_LEFT_IN_SECONDS * 1000) {
                        color = Color.ORANGE;
                    }

                    panelComponent.getChildren().add(LineComponent.builder()
                            .left("Needs attention in: ")
                            .rightColor(color)
                            .right(DurationFormatUtils.formatDuration(timeBeforeNeedingAttention, getFormatForTime(), true))
                            .build()
                    );
                }
            } else {
                if (kittenPlugin.isOverGrown()) {
                    if (kittenConfig.catOverlay()) {
                        panelComponent.getChildren().add(LineComponent.builder()
                                .left("You have an overgrown cat.")
                                .build()
                        );
                    }
                } else {
                    if (kittenConfig.catOverlay()) {
                        panelComponent.getChildren().add(LineComponent.builder()
                                .left("Overgrown in: ")
                                .right(DurationFormatUtils.formatDuration(kittenPlugin.getTimeUntilOvergrown(), getFormatForTime(), true))
                                .build()
                        );
                    }
                }
            }
        }

        return super.render(graphics);
    }

    private String getFormatForTime() {
        if (kittenConfig.displaySeconds()) {
            return "H:mm:ss";
        } else {
            return "H:mm";
        }
    }

}

/*
 * Copyright (c) 2018, Nachtmerrie <https://github.com/Nachtmerrie>
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