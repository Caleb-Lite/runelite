package net.runelite.client.plugins.pluginhub.com.wintertodtIdle;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.components.LineComponent;

public class WintertodtIdleOverlay extends OverlayPanel
{
	private final WintertodtIdleConfig config;

	private final Client client;

	@Inject
    private WintertodtIdleOverlay(WintertodtIdleConfig config, Client client)
    {
        this.config = config;
        this.client = client;
    }

	@Override
    public Dimension render(Graphics2D graphics)
    {
        panelComponent.getChildren().clear();
        panelComponent.setPreferredSize(new Dimension(client.getCanvasWidth(), client.getCanvasHeight()));
       
		for(int i = 0; i < 100; ++i)
        {
            panelComponent.getChildren().add((LineComponent.builder())
                    .left(" ")
                    .build());
        }
        
		panelComponent.setBackgroundColor(config.overlayColor());
       
        return panelComponent.render(graphics);
    }
}
