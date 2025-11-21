package net.runelite.client.plugins.pluginhub.com.yama;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

public class PhaseReminderOverlay extends OverlayPanel
{
    private final PhaseReminderConfig config;
    private final Client client;
    private int currentPhase = 0;

    @Inject
    private PhaseReminderOverlay(PhaseReminderConfig config, Client client)
    {
        this.config = config;
        this.client = client;
        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
    }

    public void renderPhase(int phase)
    {
        this.currentPhase = phase;
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (currentPhase == 0){
            return null;
        }

        final String phaseMessage = "Phase " + currentPhase;
        final int length = graphics.getFontMetrics().stringWidth(phaseMessage);

        panelComponent.getChildren().clear();

        panelComponent.getChildren().add((LineComponent.builder())
                .left(phaseMessage)
                .build());

        panelComponent.setPreferredSize(new Dimension(length + 10, 0));
        panelComponent.setBackgroundColor(config.overlayColor());

        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);

        return panelComponent.render(graphics);
    }
}