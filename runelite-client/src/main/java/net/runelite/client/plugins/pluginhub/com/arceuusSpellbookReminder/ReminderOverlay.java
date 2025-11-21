package net.runelite.client.plugins.pluginhub.com.arceuusSpellbookReminder;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

public class ReminderOverlay extends OverlayPanel
{
    private static final String LONG_TEXT = "SWITCH TO ARCEUUS SPELLBOOK!";
    private static final String SHORT_TEXT = "ARCEUUS!";

    private final ReminderPlugin plugin;
    private final ReminderConfig config;
    private final Client client;

    @Inject
    public ReminderOverlay(ReminderPlugin plugin, ReminderConfig config, Client client)
    {
        this.plugin = plugin;
        this.config = config;
        this.client = client;

        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (panelComponent == null)
        {
            return null;
        }

        // Clear existing text
        panelComponent.getChildren().clear();

        // Only show reminder if player has the Book of the Dead and is not on Arceuus
        if (plugin.hasBookOfTheDead() && !plugin.isOnArceuusSpellbook())
        {
            if (config.ignoreClues() && plugin.hasClueScroll()){
                return null;
            }
            panelComponent.setBackgroundColor(config.color());

            switch (config.reminderStyle())
            {
                case LONG_TEXT:
                    panelComponent.getChildren().add(LineComponent.builder()
                            .left(LONG_TEXT)
                            .build());
                    panelComponent.setPreferredSize(
                            new Dimension(graphics.getFontMetrics().stringWidth(LONG_TEXT) + 8, 0)
                    );
                    break;

                case SHORT_TEXT:
                    panelComponent.getChildren().add(LineComponent.builder()
                            .left(SHORT_TEXT)
                            .build());
                    panelComponent.setPreferredSize(
                            new Dimension(graphics.getFontMetrics().stringWidth(SHORT_TEXT) + 8, 0)
                    );
                    break;

                case CUSTOM_TEXT:
                    String custom = config.customText();
                    if (custom == null || custom.trim().isEmpty())
                    {
                        custom = LONG_TEXT; // fallback if blank
                    }
                    panelComponent.getChildren().add(LineComponent.builder()
                            .left(custom)
                            .build());
                    panelComponent.setPreferredSize(
                            new Dimension(graphics.getFontMetrics().stringWidth(custom) + 8, 0)
                    );
                    break;
            }

            return panelComponent.render(graphics);
        }

        return null;
    }
}
