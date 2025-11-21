package net.runelite.client.plugins.pluginhub.com.hpabovehead;

import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Player;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.api.Skill;

import javax.inject.Inject;
import java.awt.*;

public class HPAboveHeadOverlay extends Overlay
{
    private final Client client;
    private final HPAboveHeadConfig config;

    @Inject
    public HPAboveHeadOverlay(Client client, HPAboveHeadConfig config)
    {
        this.client = client;
        this.config = config;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ALWAYS_ON_TOP);
        setPriority(OverlayPriority.MED);
    }

    @Override
    public Dimension render(Graphics2D g)
    {
        Player localPlayer = client.getLocalPlayer();
        if (localPlayer == null || localPlayer.getHealthScale() <= 0)
        {
            return null;
        }

        int maxHp = client.getRealSkillLevel(Skill.HITPOINTS);
        int currentHp = client.getBoostedSkillLevel(Skill.HITPOINTS);
        String text = currentHp + "/" + maxHp;

        // set font
        g.setFont(new Font(config.font().name(), Font.PLAIN, config.fontSize()));
        FontMetrics metrics = g.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();

        // calc height (player's logical height + offset)
        int height = localPlayer.getLogicalHeight() + config.textHeightOffset();
        net.runelite.api.coords.LocalPoint localLocation = localPlayer.getLocalLocation();
        net.runelite.api.Point canvasPoint = Perspective.localToCanvas(client, localLocation, client.getPlane(), height);

        if (canvasPoint != null)
        {
            // centre the text and apply horizontal offset
            int x = canvasPoint.getX() - (textWidth / 2) + config.textXOffset();
            int y = canvasPoint.getY();

            // optional background box
            if (config.showBackground())
            {
                int padding = 2;
                int boxX = x - padding;
                int boxY = y - textHeight + 4;
                int boxWidth = textWidth + padding * 2;
                int boxHeight = textHeight;

                g.setColor(config.backgroundColor());
                g.fillRect(boxX, boxY, boxWidth, boxHeight);
            }

            // optional shadow
            g.setColor(Color.BLACK);
            g.drawString(text, x + 1, y + 1);

            // HP text
            g.setColor(config.color());
            g.drawString(text, x, y);
        }

        return null;
    }
}
