package net.runelite.client.plugins.pluginhub.com.playerhighlighter;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Player;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;

import javax.inject.Inject;
import java.awt.*;

public class PlayerHighlighterOverlay extends Overlay {
    private final Client client;
    private final PlayerHighlighterPlugin plugin;
    private final PlayerHighlighterConfig config;
    private final ModelOutlineRenderer outlineRenderer;

    @Inject
    private PlayerHighlighterOverlay(Client client, PlayerHighlighterPlugin plugin, PlayerHighlighterConfig config, ModelOutlineRenderer outlineRenderer){
        this.client = client;
        this.plugin = plugin;
        this.config = config;
        this.outlineRenderer = outlineRenderer;

        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    public Dimension render(Graphics2D graphics){

        WorldPoint my_loc = client.getLocalPlayer().getWorldLocation();

        for(Player player : client.getPlayers()){

            WorldPoint player_loc = player.getWorldLocation();
            float distance = my_loc.distanceTo(player_loc);

            if(player != client.getLocalPlayer() && distance == 0){
                outlineRenderer.drawOutline(player, config.borderWidth(), config.underfootColor(), config.borderFeather());
                continue;
            }

            if(player != client.getLocalPlayer() && distance <= config.highlightRadius())
                outlineRenderer.drawOutline(player, config.borderWidth(), config.outlineColor(), config.borderFeather());
        }

        if(config.highlightSelf())
            outlineRenderer.drawOutline(client.getLocalPlayer(), config.borderWidth(), config.selfColor(), config.borderFeather());

        return null;
    }

}

