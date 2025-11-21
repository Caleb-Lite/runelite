package net.runelite.client.plugins.pluginhub.com.soulwars;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Player;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.*;
import java.util.ArrayList;

@Slf4j
public class CaptureAreaOverlay extends Overlay {

    @Inject
    private final SoulWarsManager soulWarsManager;
    @Inject
    private final Client client;
    @Inject
    private final SoulWarsConfig config;

    @Inject
    public CaptureAreaOverlay(SoulWarsManager soulWarsManager, Client client, SoulWarsConfig config) {
        this.soulWarsManager = soulWarsManager;
        this.client = client;
        this.config = config;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics2D) {
        if (soulWarsManager.regionToCaptureAreaTiles.isEmpty() || !config.highlightCaptureAreas()) {
            return null;
        }

        Stroke stroke = new BasicStroke((float) 0);
        for (final ArrayList<CaptureAreaTile> captureAreaTiles : soulWarsManager.regionToCaptureAreaTiles.values()) {
            for (final CaptureAreaTile tile: captureAreaTiles) {
                drawTile(graphics2D, tile, stroke);
            }
        }

        return null;
    }

    private void drawTile(Graphics2D graphics2D, CaptureAreaTile captureAreaTile, Stroke borderStroke) {
        Player player = client.getLocalPlayer();
        if (player == null) {
            return;
        }

        WorldPoint playerLocation = WorldPoint.fromLocalInstance(client, player.getLocalLocation());

        if (captureAreaTile.worldPoint.distanceTo(playerLocation) >= config.maxDrawDistance()) {
            return;
        }

        for (LocalPoint lp: captureAreaTile.localPoint) {
            if (lp == null) {
                return;
            }

            Polygon poly = Perspective.getCanvasTilePoly(client, lp);
            if (poly != null) {
                Color color = captureAreaTile.color;
                Color fillColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), config.fillOpacity());
                OverlayUtil.renderPolygon(graphics2D, poly, color, fillColor, borderStroke);
            }
        }
    }
}
