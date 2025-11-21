package net.runelite.client.plugins.pluginhub.com.coxmegascale.overlays;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.plugins.pluginhub.com.coxmegascale.CoxMegaScaleConfig;
import net.runelite.client.plugins.pluginhub.com.coxmegascale.CoxMegaScalePlugin;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;

@Slf4j
public class PointsOverlay extends OverlayPanel {
    private final CoxMegaScaleConfig config;
    private final CoxMegaScalePlugin plugin;

    @Inject
    public PointsOverlay(CoxMegaScaleConfig config, CoxMegaScalePlugin plugin) {
        this.config = config;
        this.plugin = plugin;
        setPosition(OverlayPosition.TOP_RIGHT);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (!config.enablePointsOverlay()) {
            return null;
        }

        try {
            panelComponent.getChildren().clear();

            // Create and configure the TitleComponent
            TitleComponent title = TitleComponent.builder()
                    .text("Raid Points")
                    .color(Color.WHITE)
                    .build();
            panelComponent.getChildren().add(title);

            // Fetch total points from the plugin
            int totalPoints = plugin.getTotalPoints();

            LineComponent totalPointsLine = LineComponent.builder()
                    .left("Total Points:")
                    .right(String.valueOf(totalPoints))
                    .rightColor(Color.GREEN)
                    .build();
            panelComponent.getChildren().add(totalPointsLine);

            // Fetch total Lost points from the plugin
            int totalLostPoints = plugin.getTotalLostPoints();

            if (totalLostPoints != 0) {

                LineComponent totalLostPointsLine = LineComponent.builder()
                        .left("Lost Points:")
                        .right(String.valueOf(totalLostPoints))
                        .rightColor(Color.RED)
                        .build();
                panelComponent.getChildren().add(totalLostPointsLine);
            }

            // Render the panel
            return super.render(graphics);
        } catch (Exception e) {
            log.error("Error rendering PointsOverlay: ", e);
            return null;
        }
    }
}
