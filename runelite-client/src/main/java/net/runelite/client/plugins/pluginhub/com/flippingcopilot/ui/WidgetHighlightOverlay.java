package net.runelite.client.plugins.pluginhub.com.flippingcopilot.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import net.runelite.api.widgets.Widget;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class WidgetHighlightOverlay extends Overlay
{
    private final Widget widget;
    private final Color color;
    private final Rectangle relativeBounds;

    public WidgetHighlightOverlay(final Widget widget, Color color, Rectangle relativeBounds)
    {
        this.widget = widget;
        this.color = color;
        this.relativeBounds = relativeBounds;

        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
        setPriority(PRIORITY_HIGH);
        setMovable(true);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (widget == null)
        {
            return null;
        }

        Rectangle highlightBounds = widget.getBounds();

        if (highlightBounds == null)
        {
            return null;
        }

        highlightBounds.x += relativeBounds.x;
        highlightBounds.y += relativeBounds.y;
        highlightBounds.width = relativeBounds.width;
        highlightBounds.height = relativeBounds.height;

        drawHighlight(graphics, highlightBounds);
        return null;
    }

    private void drawHighlight(Graphics2D graphics, Rectangle bounds)
    {
        graphics.setColor(color);
        graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}