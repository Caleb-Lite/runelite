package net.runelite.client.plugins.pluginhub.com.specimencleaningextended;

import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.ComponentOrientation;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;

public class SpecimenCleaningExtendedOverlay extends OverlayPanel
{
    private final SpecimenCleaningExtendedPlugin plugin;
    @Inject
    private SpecimenCleaningExtendedConfig config;

    @Inject
    SpecimenCleaningExtendedOverlay(SpecimenCleaningExtendedPlugin plugin)
    {
        setPosition(OverlayPosition.TOP_RIGHT);
        this.plugin = plugin;
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if(config.displayInfoPanel()) {
            final String predictedXpPerHour = plugin.getPredictedXpPerHourFormatted();
            final String artefactsPerHour = plugin.getArtefactsPerHourFormatted();
            final String specimensPerHour = plugin.getSpecimensPerHourFormatted();



            panelComponent.setOrientation(ComponentOrientation.VERTICAL);
            panelComponent.getChildren().add(TitleComponent.builder().text("Cleaning Rates").build());
            if (config.findSpecimensPerHour()) {
                panelComponent.getChildren().add(LineComponent.builder()
                        .left("Specimens/hr:")
                        .right(String.valueOf(specimensPerHour))
                        .build());
            }
            if (config.findArtefactsPerHour()) {
                panelComponent.getChildren().add(LineComponent.builder()
                        .left("Artefacts/hr:")
                        .right(String.valueOf(artefactsPerHour))
                        .build());
            }
            if (config.predictXpPerHour()) {
                panelComponent.getChildren().add(LineComponent.builder()
                        .left("Experience/hr:")
                        .right(String.valueOf(predictedXpPerHour))
                        .build());
            }
        }

        return super.render(graphics);
    }
}
