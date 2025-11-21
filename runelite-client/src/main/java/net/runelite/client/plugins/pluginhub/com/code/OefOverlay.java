package net.runelite.client.plugins.pluginhub.com.code;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;

public class OefOverlay extends Overlay
{
    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    OefConfig config;

    @Override
    public Dimension render(Graphics2D graphics)
    {
        panelComponent.getChildren().clear();
        String overlayTitle = "Oofs:";

        // Build overlay title (Current task:)
        panelComponent.getChildren().add(TitleComponent.builder()
                .text(overlayTitle)
                .color(Color.GREEN)
                .build());

        // Set the size of the overlay (width)
        panelComponent.setPreferredSize(new Dimension(
                graphics.getFontMetrics().stringWidth(overlayTitle) + 60,
                50));

        panelComponent.getChildren().add(LineComponent.builder()
                .left(String.valueOf(OefPlugin.oofCount))
                .build());

        if(config.overlay())
        {
            return panelComponent.render(graphics);
        }
        else
        {
            return null;
        }


    }

    @Inject
    private OefOverlay()
    {
        setPosition(OverlayPosition.TOP_LEFT);
    }
}

