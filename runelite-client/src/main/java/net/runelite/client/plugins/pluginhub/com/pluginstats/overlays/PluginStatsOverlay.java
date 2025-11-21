package net.runelite.client.plugins.pluginhub.com.pluginstats.overlays;

import com.google.inject.Inject;
import java.awt.Dimension;
import java.awt.Graphics2D;

import net.runelite.client.plugins.pluginhub.com.pluginstats.PluginStatsConfig;
import net.runelite.client.plugins.pluginhub.com.pluginstats.services.PluginStatsService;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

public class PluginStatsOverlay extends OverlayPanel
{
    private final PluginStatsService service;
    private final PluginStatsConfig config;

    @Inject
    public PluginStatsOverlay(PluginStatsService service, PluginStatsConfig config)
    {
        this.service = service;
        this.config  = config;
        setPosition(OverlayPosition.TOP_RIGHT); // default; we sync to config below
    }

    @Override
    public Dimension render(Graphics2D g)
    {
        final boolean showInstalled = config.showInstalled();
        final boolean showEnabled   = config.showEnabled();

        // If both toggles are off, render nothing (overlay disappears)
        if (!showInstalled && !showEnabled)
        {
            panelComponent.getChildren().clear();
            return null;
        }

        panelComponent.getChildren().clear();

        // Pull counts once
        PluginStatsService.Counts counts = service.getCounts();

        panelComponent.getChildren().add(
                TitleComponent.builder().text("Plugins").build()
        );

        if (showInstalled)
        {
            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Installed:")
                    .right(Integer.toString(counts.total()))
                    .build());
        }

        if (showEnabled)
        {
            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Enabled:")
                    .right(Integer.toString(counts.enabled()))
                    .build());
        }

        return super.render(g);
    }
}