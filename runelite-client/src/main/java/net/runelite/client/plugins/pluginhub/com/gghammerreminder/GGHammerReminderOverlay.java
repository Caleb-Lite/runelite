package net.runelite.client.plugins.pluginhub.com.gghammerreminder;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

public class GGHammerReminderOverlay extends OverlayPanel {
    private final GGHammerReminderConfig config;
    private final Client client;

    @Inject
    private GGHammerReminderOverlay(GGHammerReminderConfig config, Client client) {
        this.config = config;
        this.client = client;
        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        panelComponent.getChildren().clear();

        if (config.showOverlay() && GGHammerReminderUtils.isInTargetRegion(client) && !GGHammerReminderUtils.hasHammerInInventory(client)) {
            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Reminder: Bring a rock hammer!")
                    .build());
            panelComponent.setBackgroundColor(config.overlayColor());
        }

        return panelComponent.render(graphics);
    }
}