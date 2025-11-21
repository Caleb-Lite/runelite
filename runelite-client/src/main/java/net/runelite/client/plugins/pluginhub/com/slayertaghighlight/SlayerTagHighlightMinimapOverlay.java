package net.runelite.client.plugins.pluginhub.com.slayertaghighlight;

import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Point;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.*;

public class SlayerTagHighlightMinimapOverlay extends Overlay {

    private Client client;
    private SlayerTagHighlightPlugin plugin;
    private SlayerTagHighlightConfig config;

    @Inject
    private SlayerTagHighlightMinimapOverlay(Client client, SlayerTagHighlightPlugin plugin, SlayerTagHighlightConfig config) {
        this.client = client;
        this.plugin = plugin;
        this.config = config;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (!config.minimapHighlight())
        {
            return null;
        }
        for (NPC npc : plugin.getHighlights())
        {
            Point minimapLocation = npc.getMinimapLocation();
            if (minimapLocation != null)
            {
                OverlayUtil.renderMinimapLocation(graphics, minimapLocation, config.highlightColor());
            }
        }
        return null;
    }
}