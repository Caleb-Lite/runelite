package net.runelite.client.plugins.pluginhub.io.robrichardson.challengemodeindicator;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

import java.awt.*;

@Slf4j
public class ChallengeModeIndicatorOverlay extends Overlay {
    private static final int MAX_DISTANCE = 2350;
    private final Client client;
    private final ChallengeModeIndicatorConfig config;
    private final ChallengeModeIndicatorPlugin plugin;

    @Inject
    ChallengeModeIndicatorOverlay(Client client, ChallengeModeIndicatorConfig config, ChallengeModeIndicatorPlugin plugin) {
        super(plugin);
        this.client = client;
        this.config = config;
        this.plugin = plugin;

        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        Player player = client.getLocalPlayer();
        if(plugin.isChallengeModeActivated() && player != null && plugin.getLeverObject() != null && plugin.getLeverTile() != null) {
            LocalPoint playerLocation = player.getLocalLocation();
            LocalPoint leverLocation = plugin.getLeverObject().getLocalLocation();
            if (plugin.getLeverTile().getPlane() == client.getPlane() && playerLocation.distanceTo(leverLocation) <= MAX_DISTANCE) {
                if(plugin.getLeverObject().getSceneMinLocation().equals(plugin.getLeverTile().getSceneLocation())) {
                    final Point textLocation = plugin.getLeverObject().getCanvasTextLocation(graphics, config.activeText(), 200);
                    if(textLocation != null) {
                        OverlayUtil.renderTextLocation(graphics, textLocation, config.activeText(), config.activeColor());
                    }
//                    OverlayUtil.renderTileOverlay(graphics, plugin.getLeverObject(), config.activeText(), config.activeColor());
                }
            }
        }

        return null;
    }
}
