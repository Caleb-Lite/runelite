package net.runelite.client.plugins.pluginhub.com.hotcoldhelper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

public class HotColdHelperOverlay extends OverlayPanel {
    private final HotColdHelperPlugin plugin;
    private final HotColdHelperConfig config;
    private static final int PANEL_WIDTH = 250;

    @Inject
    private HotColdHelperOverlay(HotColdHelperPlugin plugin, HotColdHelperConfig config) {
        super(plugin);
		setPriority(PRIORITY_HIGHEST);
		setLayer(OverlayLayer.UNDER_WIDGETS);
		this.plugin = plugin;
        this.config = config;
		panelComponent.setPreferredSize(new Dimension(PANEL_WIDTH, 0));
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (!plugin.isHotColdClueActive()) {
            return null;
        }

        int possibleLocations = plugin.getPossibleLocationsCount();

        if (possibleLocations == 1) {
            return null;
        }

        if (possibleLocations >= 0) {
            panelComponent.getChildren().add(TitleComponent.builder()
                    .text("Hot/Cold Helper - " + possibleLocations + " Possible")
                    .color(Color.WHITE)
                    .build());

            if (possibleLocations > 1) {
                Map<String, Integer> cardinalInfo = plugin.getCardinalInfoGain();
                if (cardinalInfo != null && !cardinalInfo.isEmpty()) {
                    String bestDirection = "None";
                    int highestScore = 0;

                    for (Map.Entry<String, Integer> entry : cardinalInfo.entrySet()) {
                        if (entry.getValue() > highestScore) {
                            highestScore = entry.getValue();
                            bestDirection = entry.getKey();
                        }
                    }

                    if (!bestDirection.equals("None") && highestScore > 1000 && plugin.isNearLastMessageLocation() ) {
                        Color directionColor;
                        if (highestScore > 3000) {
                            directionColor = Color.GREEN;
                        } else if (highestScore >= 2000) {
                            directionColor = new Color(255, 191, 0);
                        } else {
                            directionColor = Color.RED;
                        }
                        panelComponent.getChildren().add(LineComponent.builder()
                                .left("Move " + bestDirection)
                                .leftColor(directionColor)
                                .build());
                    }
                }

                Map<HotColdTeleports, Double> entropyValues = plugin.getEntropyValues();

                List<Map.Entry<HotColdTeleports, Double>> sortedTeleports = entropyValues.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue())
                        .limit(config.maxTeleportEntries())
                        .collect(Collectors.toList());

				for (int i = 0; i < sortedTeleports.size(); i++) {
					Map.Entry<HotColdTeleports, Double> entry = sortedTeleports.get(i);
					HotColdTeleports teleport = entry.getKey();
					String name = teleport.getName();
					String type = config.useShorthandNames() ? teleport.getShortType() : teleport.getTeleportType();
					String teleportName = name + " (" + type + ")";
					Color textColor = teleport.isWildernessTeleport() ? new Color(255, 102, 102) : Color.WHITE;
					String prefix = (config.maxTeleportEntries() > 1) ? (i+1) + ". " : "";
					panelComponent.getChildren().add(LineComponent.builder()
						.left(prefix + teleportName)
						.leftColor(textColor)
						.build());
				}
			}
        }
        return super.render(graphics);
    }
}
/*
 * Copyright (c) 2025, Lalochazia
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
