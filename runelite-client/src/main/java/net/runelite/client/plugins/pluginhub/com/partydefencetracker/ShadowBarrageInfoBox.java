package net.runelite.client.plugins.pluginhub.com.partydefencetracker;

import java.awt.Color;
import java.awt.image.BufferedImage;

import net.runelite.client.ui.overlay.infobox.InfoBox;

public class ShadowBarrageInfoBox extends InfoBox
{
    private DefenceTrackerPlugin plugin;

    ShadowBarrageInfoBox(BufferedImage image, DefenceTrackerPlugin plugin)
    {
        super(image, plugin);
        this.plugin = plugin;
    }

    public String getText()
    {
        return null;
    }

    public Color getTextColor()
    {
        return Color.WHITE;
    }
}
