package net.runelite.client.plugins.pluginhub.com.kittentracker;

import net.runelite.client.ui.overlay.infobox.Timer;
import net.runelite.client.plugins.Plugin;

import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.temporal.ChronoUnit;


public class KittenHungryTimer extends Timer {

    public KittenHungryTimer(BufferedImage infoImage, Plugin plugin, Duration seconds) {
        super(seconds.toMillis(), ChronoUnit.MILLIS, infoImage, plugin);
    }
}
