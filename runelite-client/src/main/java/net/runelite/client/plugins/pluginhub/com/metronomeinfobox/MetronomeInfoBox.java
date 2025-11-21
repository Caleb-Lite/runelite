package net.runelite.client.plugins.pluginhub.com.metronomeinfobox;

import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MetronomeInfoBox extends InfoBox {
    private int ticks;
    private int tickState = 0;

    private int imageState = 0;

    private BufferedImage[] images = {
            ImageUtil.loadImageResource(getClass(), "metronome-0.png"),
            ImageUtil.loadImageResource(getClass(), "metronome-1.png"),
            ImageUtil.loadImageResource(getClass(), "metronome-2.png"),
            ImageUtil.loadImageResource(getClass(), "metronome-3.png"),
    };

    MetronomeInfoBox(BufferedImage image, MetronomeInfoBoxPlugin plugin, MetronomeInfoBoxConfig config) {
        super(image, plugin);
        this.ticks = config.ticks();
    }

    public String getText() {
        return String.valueOf(tickState + 1);
    }

    public Color getTextColor() {
        return Color.WHITE;
    }

    public void onConfigChanged(MetronomeInfoBoxConfig config) {
        ticks = config.ticks();
        tickState = 0;
    }

    public void onGameTick() {
        tickState = (tickState + 1) % ticks;
        imageState = (imageState + 1) % images.length;
        this.setImage(images[imageState]);
    }
}
