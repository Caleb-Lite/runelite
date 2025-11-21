package net.runelite.client.plugins.pluginhub.com.slayertaghighlight;

import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;

import javax.inject.Inject;
import java.awt.*;

public class SlayerTagHighlightOverlay extends Overlay {

    @Inject
    private Client client;
    @Inject
    private SlayerTagHighlightPlugin plugin;
    @Inject
    private SlayerTagHighlightConfig config;
    private ModelOutlineRenderer modelOutlineRenderer;

    @Inject
    private SlayerTagHighlightOverlay(Client client, SlayerTagHighlightPlugin plugin, SlayerTagHighlightConfig config, ModelOutlineRenderer modelOutlineRenderer)
    {
        this.client = client;
        this.plugin = plugin;
        this.config = config;
        this.modelOutlineRenderer = modelOutlineRenderer;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        for (NPC npc : plugin.getHighlights())
        {
            Color configColor = config.highlightColor();
            renderNpcOverlay(graphics, npc, configColor);
        }
        return null;
    }

    private void renderNpcOverlay(Graphics2D graphics, NPC npc, Color color) {
        if (config.hullHighlight()) {
            Shape shape = npc.getConvexHull();
            if (shape != null) {
                graphics.setColor(color);
                graphics.setStroke(new BasicStroke((float) config.outlineWidth()));
                graphics.draw(shape);
            }
        }
        if (config.areaHighlight()) {
            Shape shape = npc.getConvexHull();
            if (shape != null) {
                graphics.setColor((new Color (color.getRed(), color.getGreen(), color.getBlue(), 50)));
                graphics.fill(shape);
            }
        }

        if (config.tileHighlight()) {
            Shape shape = npc.getCanvasTilePoly();
            if (shape != null) {
                graphics.setColor(color);
                graphics.setStroke(new BasicStroke((float) config.tileWidth()));
                graphics.draw(shape);
            }
        }
        if (config.outlineHighlight())
        {
            int outlineWidth = (int) config.outlineWidth();
            modelOutlineRenderer.drawOutline(npc, outlineWidth, color, config.outlineFeather());
        }
    }
}
