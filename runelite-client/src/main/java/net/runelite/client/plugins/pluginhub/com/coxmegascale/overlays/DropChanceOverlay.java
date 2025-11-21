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
import java.text.DecimalFormat;

@Slf4j
public class DropChanceOverlay extends OverlayPanel
{
    private final CoxMegaScaleConfig config;
    private final CoxMegaScalePlugin plugin;

    @Inject
    public DropChanceOverlay(CoxMegaScaleConfig config, CoxMegaScalePlugin plugin)
    {
        this.config = config;
        this.plugin = plugin;
        setPosition(OverlayPosition.TOP_LEFT);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (!config.enableDropChanceOverlay())
        {
            return null;
        }

        try {
            panelComponent.getChildren().clear();

            // Create and configure the TitleComponent
            TitleComponent title = TitleComponent.builder()
                    .text("Drop Chances")
                    .color(Color.WHITE)
                    .build();
            panelComponent.getChildren().add(title);

            //Fetch total Rolls from the plugin
            int roll = plugin.getDropRoll();
            LineComponent dropRoll = LineComponent.builder()
                    .left("Total Rolls:")
                    .right(String.valueOf(roll))
                    .rightColor(Color.WHITE)
                    .build();
            panelComponent.getChildren().add(dropRoll);

            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);

            if (roll >= 1) {

                // Example LineComponents with sample data
                LineComponent uniqueChanceLine = LineComponent.builder()
                        .left("1 Purple:")
                        .right(String.valueOf(df.format(plugin.getOnePurp())) +"%")
                        .rightColor(Color.MAGENTA)
                        .build();
                panelComponent.getChildren().add(uniqueChanceLine);

            }

            if (roll >= 2) {

                LineComponent sampleChanceLine = LineComponent.builder()
                        .left("2 Purples:")
                        .right(String.valueOf(df.format(plugin.getTwoPurp())) +"%")
                        .rightColor(Color.MAGENTA)
                        .build();
                panelComponent.getChildren().add(sampleChanceLine);
            }

            if (roll >= 3) {

                LineComponent sampleChanceLine = LineComponent.builder()
                        .left("3 Purples:")
                        .right(String.valueOf(df.format(plugin.getThreePurp())) +"%")
                        .rightColor(Color.MAGENTA)
                        .build();
                panelComponent.getChildren().add(sampleChanceLine);
            }

            if (roll >= 4) {

                LineComponent sampleChanceLine = LineComponent.builder()
                        .left("4 Purples:")
                        .right(String.valueOf(df.format(plugin.getFourPurp())) +"%")
                        .rightColor(Color.MAGENTA)
                        .build();
                panelComponent.getChildren().add(sampleChanceLine);
            }

            if (roll >= 5) {

                LineComponent sampleChanceLine = LineComponent.builder()
                        .left("5 Purples:")
                        .right(String.valueOf(df.format(plugin.getFivePurp())) +"%")
                        .rightColor(Color.MAGENTA)
                        .build();
                panelComponent.getChildren().add(sampleChanceLine);
            }

            LineComponent sampleChanceLine = LineComponent.builder()
                    .left("No Purples:")
                    .right(String.valueOf(df.format(plugin.getNoPurp())) +"%")
                    .rightColor(Color.MAGENTA)
                    .build();
            panelComponent.getChildren().add(sampleChanceLine);

            // Add more LineComponents as needed for dynamic data

            // Render the panel
            return super.render(graphics);
        } catch (Exception e) {
            log.error("Error rendering DropChanceOverlay: ", e);
            return null;
        }
    }
}

// File: PointsOverlay.java