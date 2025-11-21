package net.runelite.client.plugins.pluginhub.com.afkdimmer;

import net.runelite.api.Client;
import net.runelite.api.widgets.Widget;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;

import javax.inject.Inject;
import java.awt.*;

public class AfkDimmerOverlay extends Overlay {

    public final AfkDimmerConfig config;
    public final AfkDimmerPlugin plugin;
    public final Client client;

    @Inject
    public AfkDimmerOverlay(AfkDimmerPlugin plugin, AfkDimmerConfig config, Client client) {

        super(plugin);

        this.plugin = plugin;
        this.config = config;
        this.client = client;

        setPosition(OverlayPosition.DYNAMIC);
        setPriority(OverlayPriority.HIGH);
        setLayer(OverlayLayer.ALWAYS_ON_TOP);

    }

    private float currentAlpha = 0f;
    public Dimension render(Graphics2D graphics) {


        Widget welcome = client.getWidget(InterfaceID.WelcomeScreen.PLAY);
        if (welcome != null)
        {
            return null; // skip drawing if news screen is up
        }


        // Shorthand if-else statement, dim when true
        float targetAlpha = plugin.afkDimmerEnabled
                ? config.dimmerStrength()/100f
                : 0f;

        // Smoothly move currentAlpha towards targetAlpha
        float speed = 0.06f; // lower = slower fade
        if (Math.abs(currentAlpha - targetAlpha) > 0.01f)
        {
            currentAlpha += (targetAlpha - currentAlpha) * speed;
        }
        else
        {
            currentAlpha = targetAlpha;
        }

        // Draw the dimmer overlay
        graphics.setColor(new Color(0, 0, 0, currentAlpha));
        graphics.fill(client.getCanvas().getBounds());

        return null;
    }

}