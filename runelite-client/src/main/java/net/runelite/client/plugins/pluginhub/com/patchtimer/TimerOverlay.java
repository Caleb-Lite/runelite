package net.runelite.client.plugins.pluginhub.com.patchtimer;

import com.google.inject.Inject;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class TimerOverlay extends Overlay{
    private final PatchTimerPlugin plugin;
    private final PatchTimerConfig config;
    private final Client client;

    @Inject
    TimerOverlay(PatchTimerPlugin plugin, PatchTimerConfig config, Client client ){
    setPosition(OverlayPosition.DYNAMIC);
    setLayer(OverlayLayer.ABOVE_SCENE);
    this.plugin = plugin;
    this.config = config;
    this.client = client;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        final int backgroundSize = this.config.getBackgroundSize();
        final Color colorBackground = this.config.getBackgroundColor();
        final Color colorText = this.config.getTextColor();
        final Color tick_early_color = this.config.getTick_early_color();
        final Color tick_perfect_color = this.config.getTick_perfect_color();
        final Color tick_late_color = this.config.getTick_late_color();

        this.plugin.getTreeTimerList().forEach(TreeTimer -> {
            final String text = TreeTimer.getTicksLeftDisplay();
            final LocalPoint localPoint = LocalPoint.fromWorld(this.client, TreeTimer.getLocation());
            if (localPoint != null){
                final Color color;
                final int counter = TreeTimer.getTicksLeft();

                if (counter == 3)
                {
                    color = tick_early_color;
                }
                else if (counter == 2)
                {
                    color = tick_perfect_color;
                }
                else if (counter == 1)
                {
                    color = tick_late_color;
                }
                else {
                    color = colorText;
                }

                final Point point = Perspective.getCanvasTextLocation(this.client, graphics, localPoint, text, 0);
                Rectangle2D textBounds = graphics.getFontMetrics().getStringBounds(text, graphics);
                this.drawTextBackground(graphics, point, colorBackground, textBounds, backgroundSize);
                this.drawText(graphics, point, color, text);
            }

        });
        return null;
    }
    private void drawTextBackground(Graphics2D graphics, Point point, Color color, Rectangle2D textBounds, int size) {
        graphics.setColor(color);

        final int x = (int) (point.getX() - ((double) size / 2) + (textBounds.getWidth() / 2));
        final int y = (int) (point.getY() - ((double) size / 2) - (textBounds.getHeight() / 2));

        graphics.fillRect(x, y, size, size);
    }
    private void drawText(Graphics2D graphics, Point point, Color color, String text) {
        final int x = point.getX();
        final int y = point.getY();
        graphics.setColor(color);
        graphics.drawString(text, x, y);
    }
}