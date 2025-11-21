package net.runelite.client.plugins.pluginhub.com.fishingspot;

import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.ProgressPieComponent;

import javax.inject.Inject;
import java.awt.*;
import java.time.Duration;
import java.time.Instant;

public class FishingSpotTimerOverlay extends Overlay
{
    private final FishingSpotTimerPlugin plugin;
    private final FishingSpotTimerConfig config;
    private final Client client;

    @Inject
    private FishingSpotTimerOverlay(FishingSpotTimerPlugin plugin, FishingSpotTimerConfig config, Client client)
    {
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
        this.plugin = plugin;
        this.config = config;
        this.client = client;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        Color color;
        long maxMoveTime = Duration.ofSeconds(config.maxMoveTime()).toMillis();
        long minMoveTime = Duration.ofSeconds(config.minMoveTime()).toMillis();
        for (NPC npc : plugin.getFishingSpots()) {
            color = Color.GREEN;
            FishingSpotSpawn spawn = plugin.getFishingSpotSpawns().get(npc.getIndex());
            if (spawn != null) {
                long millisLeft = maxMoveTime - Duration.between(spawn.getTime(), Instant.now()).toMillis();
                if (millisLeft < 0){
                    millisLeft = 0;
                    color = Color.RED;
                }
                else if (millisLeft < minMoveTime){
                    color = Color.YELLOW;
                }

                LocalPoint localPoint = npc.getLocalLocation();
                Point location = Perspective.localToCanvas(client, localPoint, client.getPlane());

                if (location != null) {
                    ProgressPieComponent pie = new ProgressPieComponent();
                    pie.setFill(color);
                    pie.setBorderColor(color);
                    pie.setPosition(location);
                    pie.setProgress((float) millisLeft / maxMoveTime);
                    pie.render(graphics);
                }
            }
        }

        return null;
    }
}
