package net.runelite.client.plugins.pluginhub.org.RaidSpellChecker;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;

@Singleton
public class MissingSpellOverlay extends Overlay
{
    private final Client client;
    private final RaidSpellCheckerConfig config;

    private String message;
    private long startTime;
    private int timeoutMillis;

    @Inject
    public MissingSpellOverlay(Client client, RaidSpellCheckerConfig config)
    {
        this.client = client;
        this.config = config;
        this.setPosition(OverlayPosition.DYNAMIC);
        this.message = null;
        this.startTime = 0;
        this.timeoutMillis = 0;
    }

    public void show(String message, int milliseconds)
    {
        this.message = message;
        this.startTime = System.currentTimeMillis();
        this.timeoutMillis = milliseconds;
    }

    @Override
    public Dimension render(Graphics2D g)
    {
        if (message == null || System.currentTimeMillis() - startTime > timeoutMillis)
        {
            return null;
        }

        g.setFont(new Font("Arial", Font.BOLD, config.popupSize()));
        FontMetrics metrics = g.getFontMetrics();
        int textWidth = metrics.stringWidth(message);
        int textHeight = metrics.getHeight();

        int padding = 10;
        int boxWidth = textWidth + padding * 2;
        int boxHeight = textHeight + padding * 2;

        int screenWidth = client.getCanvas().getWidth();
        int screenHeight = client.getCanvas().getHeight();
        int x = (screenWidth - boxWidth) / 2;
        int y = (screenHeight - boxHeight) / 2;

        g.setColor(config.popupBackgroundColor());
        g.fillRoundRect(x, y, boxWidth, boxHeight, config.popupSize(), config.popupSize());

        g.setColor(Color.BLACK);
        g.drawRoundRect(x, y, boxWidth, boxHeight, config.popupSize(), config.popupSize());

        g.setColor(config.popupTextColor());
        int textX = x + padding;
        int textY = y + padding + metrics.getAscent();
        g.drawString(message, textX, textY);

        return new Dimension(boxWidth, boxHeight);
    }
}