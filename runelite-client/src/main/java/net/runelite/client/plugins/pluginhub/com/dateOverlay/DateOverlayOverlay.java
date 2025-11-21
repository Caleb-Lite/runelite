package net.runelite.client.plugins.pluginhub.com.dateOverlay;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;

public class DateOverlayOverlay extends Overlay
{
    private  final DateOverlayConfig config;
    private final PanelComponent panelComponent = new PanelComponent();
    private final DateOverlayPlugin dateOverlayPlugin;

    @Inject
    public DateOverlayOverlay(DateOverlayConfig config, DateOverlayPlugin dateOverlayPlugin)
    {
        setPosition(OverlayPosition.TOP_CENTER);
        this.config = config;
        this.dateOverlayPlugin = dateOverlayPlugin;
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        panelComponent.getChildren().clear();

        // Build overlay title
        panelComponent.getChildren().add(TitleComponent.builder()
                .text(dateOverlayPlugin.currentDateTime)
                .color(Color.white)
                .build());

        //Set panel size
        Dimension dim = new Dimension();
        float charMultiplier = dateOverlayPlugin.currentDateTime.length() > 10 ? 7.5f : 9;
        dim.setSize(dateOverlayPlugin.currentDateTime.length() * charMultiplier,2);
        panelComponent.setPreferredSize(dim);

        return panelComponent.render((graphics));
    }
}
